package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.SlowSetPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LoadTote extends CommandGroup {

	public LoadTote() {
		addParallel(new CloseArm());
		addParallel(new ReverseAlign());
		addSequential(new WaitCommand(0.45));
		addParallel(new StartAlign(false));
		addSequential(new WaitCommand(0.6));
		
		addParallel(new OpenArm());
		addSequential(new WaitCommand(0.2));
		addParallel(new StopAlign());
		//addSequential(new WaitCommand(0.1));
		
		addSequential(new SlowSetPosition(RobotValues.FLOOR));
		addSequential(new SetElevatorPosition(RobotValues.TOTE_ONE_GRAB));
		
	}

}
