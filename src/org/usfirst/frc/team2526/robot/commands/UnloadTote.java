package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class UnloadTote extends CommandGroup {
    
    public  UnloadTote() {
    	addParallel(new ShiftElevator(-500));
    	addSequential(new WaitCommand(0.3));
//    	addParallel(new StopAlign());
//    	addSequential(new WaitCommand(0.3));
    	addParallel(new ReverseAlign());
    	addSequential(new TimedDrive(0.7, false));
    	addParallel(new StopAlign());
    	addSequential(new WaitCommand(0.1));
    }
}
