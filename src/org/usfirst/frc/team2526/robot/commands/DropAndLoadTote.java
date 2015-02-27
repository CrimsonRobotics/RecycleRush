package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.DriveForward;
import org.usfirst.frc.team2526.robot.commands.drive.PIDAutoPilotDrive;
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

//addSequential(new UnloadTote());
        
		addParallel(new ShiftElevator(-450));
        addSequential(new WaitCommand(0.2));
    	addParallel(new ReverseAlign());
    	addParallel(new ReleaseTote());
    	addSequential(new WaitCommand(0.5));
    	addParallel(new StopAlign());
        addSequential(new DriveForward(1, -350));
        addSequential(new WaitCommand(1.1));
        
		if (wall)
			addSequential(new SetElevatorPosition(RobotValues.TOTE_TWO));
		else
			addSequential(new SetElevatorPosition(RobotValues.FLOOR));
		
        addSequential(new WaitCommand(0.2));
        addSequential(new DriveForward(1, 400));
        addSequential(new WaitCommand(1.1));
        addParallel(new StartAlign(false));
		addParallel(new ShiftElevator(RobotValues.TOTE_ONE_GRAB));
		addSequential(new WaitCommand(0.5));
		addSequential(new WaitCommand(0.2));
		addParallel(new StopAlign());
		addParallel(new StabilizeTote());
		addSequential(new WaitCommand(0.5));
        
        if (wall)
			addSequential(new SetElevatorPosition(RobotValues.CHUTE));
		else
			addSequential(new SetElevatorPosition(RobotValues.SCORING));
        
		
//		addSequential(new ShiftElevator(-450));
//		addParallel(new ReverseAlign());
//		addParallel(new ReleaseTote());
//		addSequential(new DriveForward(2, ));
//
//		addSequential(new StopAlign());
////		addSequential(new WaitCommand(0.5));
////		
//		if (wall)
//			addSequential(new SetElevatorPosition(RobotValues.TOTE_ONE));
//		else
//			addSequential(new SetElevatorPosition(RobotValues.FLOOR));
//
//		addParallel(new StartAlign(false));
//		addSequential(new PIDAutoPilotDrive(450, 1));
//		addSequential(new ShiftElevator(450));
//		
//		addParallel(new StabilizeTote());
//		addSequential(new StopAlign());
//		
		if (wall)
			addSequential(new SetElevatorPosition(RobotValues.CHUTE));
		else
			addSequential(new SetElevatorPosition(RobotValues.SCORING));
	}
}
