package org.usfirst.frc.team2526.robot.autonomous;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SmartAutonomous extends CommandGroup {
    
    public  SmartAutonomous(AutonomousScheme scheme) {
    	this.setInterruptible(false);
    	
    	ArrayList<Command> commands = scheme.getListOfCommands();
    	
    	for (Command command : commands) {
    		addSequential(command);
    	}
    }
    
    @Override
    protected void initialize() {
    	// Prepare the vision server
    }
    
    @Override
    protected void end() {
    	// End the vision server
    }
}
