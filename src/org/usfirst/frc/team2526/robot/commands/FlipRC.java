package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FlipRC extends CommandGroup {

    public FlipRC() {
    	addParallel(new StartAlign());
    	addParallel(new StabilizeTote());
    	addSequential(new SetElevatorPosition(RobotValues.RC_HUG));
    	addSequential(new OpenArm());
    	// If arms close soon after, check subsystem default command
    	addSequential(new AutoPilotDrive(-400, 2, 0.1));
    	addParallel(new ReleaseTote());
    	
    	addParallel(new StartAlign());
    	addParallel(new CloseArm());
    	addSequential(new WaitCommand(0.5));
    }
    
}
