package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartAlign extends Command {

    public StartAlign() {
        requires(Robot.alignment);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.alignment.startAlign();
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Set to false because we never want it to stop running until the user end the command by letting go of the button
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.alignment.stopAlign();
    }

    protected void interrupted() {
    	end(); //End the command if we are interrupted
    }
}
