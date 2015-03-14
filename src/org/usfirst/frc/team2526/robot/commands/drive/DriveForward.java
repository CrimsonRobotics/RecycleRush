package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class DriveForward extends SimpleCommand {
	double distance;
    public DriveForward(double time, double distance) {
        // Use requires() here to declare subsystem dependencies
        super(Robot.driveTrain, time);
        
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetCurrentPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (distance > 0)
    		Robot.driveTrain.driveForwardConstant(0.3);
    	else
    		Robot.driveTrain.driveForwardConstant(-0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(distance) - Robot.driveTrain.getCurrentPosition()  < 30 || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
