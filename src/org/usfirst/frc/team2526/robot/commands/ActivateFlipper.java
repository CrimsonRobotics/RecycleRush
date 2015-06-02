package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

/**
 *
 */
public class ActivateFlipper extends SimpleCommand {
	
    public ActivateFlipper() {
    	super(Robot.flipper);
        
    }

    protected void initialize() {
    	Robot.flipper.extendLeft();
    }

    protected void end() {
    	Robot.flipper.retractLeft();
    }
}
