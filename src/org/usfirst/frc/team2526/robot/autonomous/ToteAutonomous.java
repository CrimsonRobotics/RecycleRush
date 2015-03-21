package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ToteAutonomous extends CommandGroup {
    
    public  ToteAutonomous() {
    	
    	addParallel(new StartAlign(false));
    	addParallel(new LoadTote());
    	addSequential(new WaitCommand(1));
    	addParallel(new SetElevatorPosition(RobotValues.TOTE_TWO));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedRotate(1.8, true)); //1.55
    	addSequential(new TimedDrive(4.5, true)); //4.2
    	addSequential(new StopAlign());
    }
}
