package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class StartAlign extends SimpleCommand {
	boolean shouldEnd;
    public StartAlign(boolean shouldEnd) {
        super(Robot.alignmentWheels);
        this.shouldEnd = shouldEnd;
    }
    
    protected void initialize() {
    	Robot.alignmentWheels.startAlign();
    }
    
    protected boolean isFinished() {
    	return !shouldEnd;
    }
    
    protected void execute() {
    	Robot.alignmentWheels.startAlign();
    }
    
    protected void end() {
    	if (shouldEnd) Robot.alignmentWheels.stopAlign();
    }

}
