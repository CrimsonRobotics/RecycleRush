package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.elevator.ElevatorUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ToteAutonomous extends CommandGroup {
    
    public  ToteAutonomous() {
    	
    	addParallel(new StartAlign(false));
    	addParallel(new ElevatorUp());
    	addSequential(new WaitCommand(1));
    	addSequential(new TimedRotate(1.55, true)); //1.55
    	addSequential(new TimedDrive(4.5, true)); //4.2
    	addSequential(new StopAlign());
    }
}
