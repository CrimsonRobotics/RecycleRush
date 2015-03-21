package org.usfirst.frc.team2526.robot.commands.vision;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionDetectBin extends Command {

    public VisionDetectBin() {
    	requires(Robot.alignmentWheels);
    }

    protected void initialize() {
    	Robot.alignmentWheels.rotate(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // return Robot.vision.doesBinExist();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.alignmentWheels.stopAlign();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
