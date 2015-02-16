package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToTote extends Command {

    public RotateToTote() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveTrain.rotate(16.6 * Robot.vision.getToteAngle());
    }

    protected boolean isFinished() {
        return Math.abs(Robot.vision.getToteAngle()) < 5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
