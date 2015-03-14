package org.usfirst.frc.team2526.robot.commands.calibrations;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateOnRelease extends Command {

    public CalibrateOnRelease() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.shiftGoalUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.elevator.isAtBottom();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.calibrateMin();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
