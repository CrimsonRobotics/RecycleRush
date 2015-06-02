package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.ExtendRCGrabber;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RCFastAutonomous extends CommandGroup {
    
	
    public  RCFastAutonomous() {
    	
    	addParallel(new ExtendRCGrabber());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new TimedDrive(0.5, false));
    	
    	addSequential(new TimedDrive(1.2, true, 0.7));
    }
}
