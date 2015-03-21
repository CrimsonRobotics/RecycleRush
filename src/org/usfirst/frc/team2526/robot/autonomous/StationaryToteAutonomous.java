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
public class StationaryToteAutonomous extends CommandGroup {
    
    public  StationaryToteAutonomous() {
    	
    	addParallel(new StartAlign(false));
    	addParallel(new LoadTote());
    	addSequential(new WaitCommand(1));
    	addParallel(new SetElevatorPosition(RobotValues.TOTE_TWO));
    }
}
