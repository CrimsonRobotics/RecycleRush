package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
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
public class RCAutonomous extends CommandGroup {
    
	
    public  RCAutonomous() {
    	
    	addParallel(new StartAlign(false));
    	addParallel(new SetElevatorPosition(RobotValues.AUTO_RC));
    	addSequential(new WaitCommand(1));
    	addSequential(new TimedRotate(1.55, false));
    	addSequential(new TimedDrive(4.2, true));
    	addSequential(new StopAlign());
    }
}
