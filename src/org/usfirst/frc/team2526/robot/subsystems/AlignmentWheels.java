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
	Solenoid arms;
	
	public AlignmentWheels() {
		leftMotor = new CANTalon(RobotMap.LEFT_ALIGN_TALON);
		rightMotor = new CANTalon(RobotMap.RIGHT_ALIGN_TALON);
		
		arms = new Solenoid(RobotMap.PCM_MAIN, RobotMap.ALIGNMENT_ARM);
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
	
	public void openArms() {
		//arms.set(true);
		System.out.println("Pneumatics not installed yet");
	}
	
	public void closeArms() {
		//arms.set(false);
		System.out.println("Pneumatics not installed yet");
	}
}

