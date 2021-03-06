package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
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
		leftMotor.set(-0.8);
		rightMotor.set(0.8);
	}
	
	public void startSlowAlign() {
		leftMotor.set(-0.4);
		rightMotor.set(0.4);
	}
	
	public void rotate(boolean clockwise) {
		leftMotor.set(clockwise ? 0.8 : -0.8);
		rightMotor.set(clockwise ? 0.8 : -0.8);
	}
	
	public void stopAlign() {
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	public void fullReverseAlign() {
		leftMotor.set(0.8);
		rightMotor.set(-0.8);
	}
	
	public void reverseAlign() {
		leftMotor.set(0.4);
		rightMotor.set(-0.4);
	}
}

