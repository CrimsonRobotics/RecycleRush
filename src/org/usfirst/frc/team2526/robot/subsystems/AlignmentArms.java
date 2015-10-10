package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentArms extends Subsystem {
	
	DoubleSolenoid arms;
	
	public AlignmentArms() {
		arms = new DoubleSolenoid(RobotMap.PCM_MAIN, RobotMap.ARMS_A, RobotMap.ARMS_B);
	}

	protected void initDefaultCommand() {
		this.setDefaultCommand(new OpenArm());
	}

	
	public void openArms() {
		arms.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closeArms() {
		if (Robot.elevator.getPosition() > (RobotValues.ARM_SAFETY*RobotValues.MAX_POSITION))
			arms.set(DoubleSolenoid.Value.kReverse);
		else
			arms.set(DoubleSolenoid.Value.kForward);
	}
}

