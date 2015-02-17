package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActivateFlipper extends Command {
	
	boolean activateLeft, activateRight;

    public ActivateFlipper(boolean left, boolean right) {
        this.activateLeft = left;
        this.activateRight = right;
        
        requires(Robot.flipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (activateLeft) Robot.flipper.extendLeft();
    	if (activateRight) Robot.flipper.extendRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flipper.retractLeft();
    	Robot.flipper.retractRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
