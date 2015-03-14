package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class StabilizeTote extends SimpleCommand {

    public StabilizeTote() {
    	super(Robot.elevator, 0.2);
    }

    protected void initialize() {
    	Robot.elevator.stabilizeTote();
    }
    
    protected boolean isFinished() {
    	return isTimedOut();
    }
}
