package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *	Runs a sequence of commands that will drop a tote on top of one below it, then move down and pick up the entire stack
 */
public class DropAndLoadTote extends CommandGroup {

	public DropAndLoadTote(boolean wall) {

		addSequential(new ShiftElevator(-450));
		addParallel(new ReverseAlign());
		addParallel(new ReleaseTote());
		addSequential(new AutoPilotDrive(-400, 2));

		addSequential(new StopAlign());
		addSequential(new WaitCommand(0.5));
		
		if (wall)
			addSequential(new SetElevatorPosition(RobotValues.TOTE));
		else
			addSequential(new SetElevatorPosition(RobotValues.FLOOR));

		addParallel(new StartAlign());
		addSequential(new AutoPilotDrive(450, 1));
		addSequential(new ShiftElevator(450));
		
		addParallel(new StabilizeTote());
		addSequential(new StopAlign());
		
		if (wall)
			addSequential(new SetElevatorPosition(RobotValues.CHUTE));
		else
			addSequential(new SetElevatorPosition(RobotValues.SCORING));
	}
}
