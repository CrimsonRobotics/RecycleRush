package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class StopAlign extends SimpleCommand {

    public StopAlign() {
        super(Robot.alignmentWheels);
    }

    protected void initialize() {
    	Robot.alignmentWheels.stopAlign();
    }
    
    protected boolean isFinished() {
    	return true;
    }
}
