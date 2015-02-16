package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPilotDrive extends Command {

	double initialPosition, distance;
    public AutoPilotDrive(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.distance = distance;
        this.initialPosition = Robot.driveTrain.getCurrentPosition();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.driveForward(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return initialPosition + distance >= Robot.driveTrain.getCurrentPosition();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
