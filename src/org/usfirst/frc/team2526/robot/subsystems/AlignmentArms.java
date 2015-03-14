package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentArms extends Subsystem {
	
	Solenoid arms;
	
	public AlignmentArms() {
		arms = new Solenoid(RobotMap.PCM_MAIN, RobotMap.ALIGNMENT_ARM);
	}

	protected void initDefaultCommand() {
		this.setDefaultCommand(new CloseArm());
	}

	
	public void openArms() {
		arms.set(true);
	}
	
	public void closeArms() {
		arms.set(false);
	}
}
