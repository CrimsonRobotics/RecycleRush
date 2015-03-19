package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;

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
		this.setDefaultCommand(new OpenArm());
	}

	
	public void openArms() {
		
		arms.set(false);
	}
	
	public void closeArms() {
		if (Robot.elevator.getPosition() > 300)
			arms.set(true);
	}
}

