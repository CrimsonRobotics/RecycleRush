package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.drive.Rotate;
import org.usfirst.frc.team2526.robot.commands.drive.RotateToTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotesOnBin extends CommandGroup {
    
    public  ThreeTotesOnBin() {
    	addParallel(new StartAlign());
		addSequential(new LoadTote());
        addSequential(new AvoidBin());
        addSequential(new RotateToTote());
        //addSequential(new AutoPilotDrive(2000));
        
        addSequential(new SetElevatorPosition(RobotValues.TOTE_TWO));
        addSequential(new TotePickup());
        //addSequential(new SetElevatorPosition(Elevator.BIN+22));
        addSequential(new AvoidBin());
        addSequential(new RotateToTote());
        //addSequential(new AutoPilotDrive(2000));
        
        //addSequential(new SetElevatorPosition(RobotValues.TOTE));
        addSequential(new TotePickup());
        //addSequential(new SetElevatorPosition(RobotValues.BIN+34));
        addSequential(new GrabBin());
        
        addSequential(new Rotate(1500));
        //addSequential(new AutoPilotDrive(2000));
    }
}
