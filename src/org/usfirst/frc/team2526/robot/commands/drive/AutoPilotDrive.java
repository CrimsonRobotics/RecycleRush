package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class AutoPilotDrive extends SimpleCommand {

	double distance;
	
	/**
	 * 
	 * @param distance 25.12 inches per 1000 ticks
	 * 	1 inch = 39.8 ticks
	 * @param timeout
	 */
    public AutoPilotDrive(double distance, double timeout) {
    	super(Robot.driveTrain, timeout);
    	
        this.distance = distance;
    }

    protected void initialize() {
    	Robot.driveTrain.resetCurrentPosition();
    	Robot.driveTrain.enable();
    	Robot.driveTrain.driveForward(distance);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveTrain.isDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.resetCurrentPosition();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.disable();
    }
}
