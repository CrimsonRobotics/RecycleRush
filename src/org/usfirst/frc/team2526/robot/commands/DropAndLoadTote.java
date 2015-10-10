package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.SlowSetPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *	Runs a sequence of commands that will drop a tote on top of one below it, then move down and pick up the entire stack
 */
public class DropAndLoadTote extends CommandGroup {

	public DropAndLoadTote(boolean wall) {
		addParallel(new StartAlign(false));
		if (wall)
			addSequential(new SlowSetPosition((RobotValues.CHUTE_STACK+0.075)*RobotValues.MAX_POSITION));
		else
			addParallel(new ShiftElevator(-450));
		
        addSequential(new WaitCommand(0.2));
    	//addParallel(new ReverseAlign());
    	//addSequential(new WaitCommand(0.5));
    	//addParallel(new StopAlign());
        //addSequential(new TimedDrive(0.4, false));
        //addSequential(new WaitCommand(1.1));
        
		if (wall)
			addParallel(new SetElevatorPosition((RobotValues.TOTE_TWO-0.2)*RobotValues.MAX_POSITION));
		else
			addParallel(new SetElevatorPosition(RobotValues.FLOOR));
		
        //addSequential(new WaitCommand(0.5));
        //addSequential(new TimedDrive(0.6, true));
        
		addParallel(new ShiftElevator(700));
		addSequential(new WaitCommand(0.2));
        
        if (wall)
        	addSequential(new SetElevatorPosition(RobotValues.CHUTE*RobotValues.MAX_POSITION));
		else
			addSequential(new SetElevatorPosition(RobotValues.SCORING*RobotValues.MAX_POSITION));
        addParallel(new StopAlign());
        addSequential(new WaitCommand(0.1));
	}
}
