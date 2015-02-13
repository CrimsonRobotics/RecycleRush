package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentWheels extends Subsystem {
	
	CANTalon leftMotor, rightMotor;
	
	public AlignmentWheels() {
		leftMotor = new CANTalon(RobotMap.LEFT_ALIGN_TALON);
		rightMotor = new CANTalon(RobotMap.RIGHT_ALIGN_TALON);
	}

	protected void initDefaultCommand() {}
	
	public void startAlign() {
		leftMotor.set(-1);
		rightMotor.set(1);
	}
	
	public void stopAlign() {
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	public void reverseAlign() {
		leftMotor.set(1);
		rightMotor.set(-1);
	}
}

