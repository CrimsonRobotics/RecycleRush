package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {
	double clicks;
	
    public Rotate(double clicks) {
    	super(0.1);
        requires(Robot.driveTrain);
        this.clicks = clicks;
    }

    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (clicks > 0)
    		Robot.driveTrain.rotate(0.1);
    	else
    		Robot.driveTrain.rotate(-0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
