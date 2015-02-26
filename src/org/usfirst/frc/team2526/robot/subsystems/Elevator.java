package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.RobotValues;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public CANTalon winch;
	public DoubleSolenoid stabilizeSolenoid;
	double goal;

	public Elevator() {
		super("Elevator");

		stabilizeSolenoid = new DoubleSolenoid(RobotMap.PCM_MAIN, RobotMap.STABLE_ELEVATOR_A, RobotMap.STABLE_ELEVATOR_B);

		winch = new CANTalon(RobotMap.WINCH_TALON);
		
		winch.changeControlMode(CANTalon.ControlMode.Position);
		winch.changeControlMode(CANTalon.ControlMode.Current);
		winch.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		winch.reverseOutput(true);
		
		SmartDashboard.putNumber("P", 1);
		SmartDashboard.putNumber("I", 0);
		SmartDashboard.putNumber("D", 0);
		SmartDashboard.putNumber("F", 0);
		SmartDashboard.putNumber("RAMP", 1);
		
		updatePID();
	}

	public void updatePID() {
		winch.setPID(SmartDashboard.getNumber("P", 1),
				SmartDashboard.getNumber("I", 0), 
				SmartDashboard.getNumber("D", 0),
				SmartDashboard.getNumber("F", 0), 360,
				SmartDashboard.getNumber("RAMP", 1), 0);
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
	
	public void shiftGoalUp() {
		goal += 50;
		updateGoal();
	}
	
	public void shiftGoalDown() {
		goal -= 50;
		updateGoal();
	}
	
	private void updateGoal() {
		winch.set(goal);
	}

	public void stabilizeTote() {
		stabilizeSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void releaseTote() {
		stabilizeSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void initDefaultCommand() {}
}
