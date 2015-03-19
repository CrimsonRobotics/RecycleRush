package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.RobotValues;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public CANTalon winch;
	double goal;
	
	boolean onStep = false;

	public Elevator() {
		super("Elevator");


		winch = new CANTalon(RobotMap.WINCH_TALON);
		
		winch.changeControlMode(CANTalon.ControlMode.Position);
		winch.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
		//winch.reverseOutput(true);
		winch.reverseSensor(true);
		
		SmartDashboard.putNumber("P", 1);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0);
		SmartDashboard.putNumber("F", 0);
		SmartDashboard.putNumber("RAMP", 1);
		
		updatePID();
	}

	public void updatePID() {
		winch.setPID(RobotValues.WINCH_P,
				RobotValues.WINCH_I, 
				RobotValues.WINCH_D,
				0, 
				360,
				RobotValues.WINCH_RAMP, 
				0);
	}

	public boolean isAtTop() {
		return winch.isRevLimitSwitchClosed();
	}

	public boolean isAtBottom() {
		return winch.isFwdLimitSwitchClosed();
	}
	
	public boolean isAt(double position) {
		return Math.abs(getPosition() - position) < RobotValues.WINCH_TOLERANCE;
	}

	public boolean isAtTarget() {
		return Math.abs(getPosition() - winch.getSetpoint()) < RobotValues.WINCH_TOLERANCE;
	}

	public void calibrateMin() {
		winch.setPosition(0);
	}

	public void calibrateMax() {
		RobotValues.MAX_POSITION = winch.getEncPosition();
		SmartDashboard.putNumber("maxPosition", winch.getEncPosition());
	}

	public double getPosition() {
		return winch.getEncPosition();
	}

	public void moveToPositionTicks(double position) {
		goal = position;
		updateGoal();
	}
	
	public void moveTop() {
		goal = RobotValues.MAX_POSITION;
		updateGoal();
	}
	
	public void moveBottom() {
		goal = 0;
		updateGoal();
	}
	
	public void setGoalToCurrent() {
		goal = winch.getEncPosition();
	}
	
	public void shiftGoalUp(boolean highSpeed) {
		if (highSpeed)
			goal += 100;
		else
			goal += 50;
		updateGoal();
	}
	
	public void shiftGoalDown(boolean highSpeed) {
		if (highSpeed)
			goal -= 100;
		else
			goal -= 50;
		updateGoal();
	}
	
	public void updateGoal() {
		winch.set(goal);
	}

	public boolean getOnStep() {
		return onStep;
	}
	
	public void setOnStep(boolean step) {
		this.onStep = step; 
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new PIDInPlace());
	}
	
	public void updateArms() {
		
	}
	
	public void update() {
		updatePID();
		updateArms();
	}
}
