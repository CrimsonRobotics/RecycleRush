package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoPilotDrive extends Command {

	double distance;
	double maxSpeed;
	
    public AutoPilotDrive(double distance, double timeout) {
    	super(timeout);
    	
    	requires(Robot.driveTrain);
        this.distance = distance;
        this.maxSpeed = RobotValues.AUTO_PILOT_MAX_SPEED;
    }
    
    public AutoPilotDrive(double distance, double timeout, double maxSpeed) {
    	super(timeout);
    	
    	requires(Robot.driveTrain);
        this.distance = distance;
        this.maxSpeed = maxSpeed;
    }

    protected void initialize() {
    	Robot.driveTrain.resetCurrentPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double speedP = (Math.abs(distance)-Robot.driveTrain.getCurrentPosition()/Math.abs(distance));
    	double actualSpeed = 0.9 * maxSpeed * speedP + 0.1;
    	
    	if (distance > 0)
    		Robot.driveTrain.driveForward(actualSpeed);
    	else
    		Robot.driveTrain.driveBackward(actualSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double error = Math.abs(distance) - Robot.driveTrain.getCurrentPosition();
    	SmartDashboard.putNumber("Drive Error ", error);
        return error < 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
