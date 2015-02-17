package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

/**
 *
 */
public class ActivateFlipper extends SimpleCommand {
	
	boolean activateLeft, activateRight;

    public ActivateFlipper(boolean left, boolean right) {
    	super(Robot.flipper);
    	this.activateLeft = left;
        this.activateRight = right;
        
    }

    protected void initialize() {
    	if (activateLeft) Robot.flipper.extendLeft();
    	if (activateRight) Robot.flipper.extendRight();
    }

    protected void end() {
    	Robot.flipper.retractLeft();
    	Robot.flipper.retractRight();
    }
}
