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
		
		
		winch.reverseOutput(true);
		//winch.reverseSensor(true);
		
		
	}

	

	public boolean isAtTop() {
		return winch.isRevLimitSwitchClosed();
	}

	public boolean isAtBottom() {
		return winch.isFwdLimitSwitchClosed();
	}
	
	public void moveUp() {
		winch.set(0.2);
	}
	
	public void moveDown() {
		winch.set(-0.2);
	}

	

	
	
	
	
	

	
	public void initDefaultCommand() {
		//setDefaultCommand(new PIDInPlace());
	}
	
	public void updateArms() {
		
	}
	
	
}
