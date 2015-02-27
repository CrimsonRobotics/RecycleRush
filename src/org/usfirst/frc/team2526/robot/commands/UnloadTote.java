package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.DriveForward;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class UnloadTote extends CommandGroup {
    
    public  UnloadTote() {
    	addSequential(new ShiftElevator(-450));
    	//addParallel(new ReverseAlign());
    	addParallel(new StopAlign());
    	addParallel(new ReleaseTote());
    	addSequential(new WaitCommand(0.3));
    	addParallel(new ReverseAlign());
    	addSequential(new DriveForward(2, -1000));
    	addParallel(new StopAlign());
    	addSequential(new WaitCommand(0.1));
//    }
    }
}
