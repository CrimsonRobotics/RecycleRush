package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LoadTote extends CommandGroup {

	public LoadTote() {

		addParallel(new StartAlign(false));
		addParallel(new ShiftElevator(RobotValues.TOTE_ONE_GRAB));
		addSequential(new WaitCommand(0.2));
		
		addSequential(new SetElevatorPosition(RobotValues.SCORING));
		//addSequential(new WaitCommand(0.5));
		addParallel(new StopAlign());
		addSequential(new WaitCommand(0.1));
	}

}
