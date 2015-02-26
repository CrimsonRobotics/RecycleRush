package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.FrictionRelease;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LoadTote extends CommandGroup {

	public LoadTote() {
		super("Load Tote");

		addParallel(new FrictionRelease());
		addSequential(new ShiftElevator(RobotValues.TOTE_ONE_GRAB));
		
		// addSequential(new WaitCommand(.5));
		addSequential(new StabilizeTote());

	}

}
