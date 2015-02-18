package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DropAndLoadTote extends CommandGroup {
    
    public  DropAndLoadTote() {
    	//addSequential(new ReleaseTote());
    	
    	addSequential(new ShiftElevator(-450));
    	addParallel(new ReverseAlign());
    	addParallel(new ReleaseTote());
    	addSequential(new AutoPilotDrive(-200));
    	
    	addSequential(new StopAlign());
    	addSequential(new WaitCommand(0.5));
    	
    	addSequential(new SetElevatorPosition(Robot.elevator.FLOOR));
    	
    	addParallel(new StartAlign());
    	addSequential(new AutoPilotDrive(300));
    	addSequential(new StopAlign());
    	addSequential(new LoadTote());
    	
    	
//    }
    }
}
