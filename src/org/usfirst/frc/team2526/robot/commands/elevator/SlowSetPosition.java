package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class SlowSetPosition extends SimpleCommand {
	double position;
    public SlowSetPosition(double position) {
    	
        super(Robot.elevator);
        
        this.position = position;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.elevator.getPosition() > position) {
    		Robot.elevator.shiftGoalDown(true);
    	} else {
    		Robot.elevator.shiftGoalUp(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.elevator.getPosition()) - Math.abs(position) < 100;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
