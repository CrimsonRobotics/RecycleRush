package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorAfterLoad;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LoadTote extends CommandGroup {

	public LoadTote() {

		addParallel(new StartAlign(false));
		addParallel(new ShiftElevator(RobotValues.TOTE_ONE_GRAB));
		addSequential(new WaitCommand(0.5));
		addSequential(new WaitCommand(0.2));
		addParallel(new StopAlign());
		addParallel(new StabilizeTote());
		addSequential(new WaitCommand(0.5));
		addSequential(new SetElevatorAfterLoad());
	}

}
