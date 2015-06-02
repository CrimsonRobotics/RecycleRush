package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedDrive extends Command {

	boolean forward;
	double speed;
	
    public TimedDrive(double time, boolean direction) {
    	this(time, direction, 0.3);
    }
    
    public TimedDrive(double time, boolean direction, double speed) {
    	super(time);
        requires(Robot.driveTrain);
        
        this.forward = direction;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (forward)
    		Robot.driveTrain.driveForwardConstant(speed);
    	else
    		Robot.driveTrain.driveForwardConstant(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stopDriving();
    }
}
