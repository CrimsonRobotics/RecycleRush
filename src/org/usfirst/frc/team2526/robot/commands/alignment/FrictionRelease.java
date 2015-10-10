package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class FrictionRelease extends SimpleCommand {

    public FrictionRelease() {
        // Use requires() here to declare subsystem dependencies
        super(Robot.alignmentWheels, 1);
    }
    
    protected void execute() {
    	if (Robot.elevator.isAt(RobotValues.STEP*RobotValues.MAX_POSITION)) {
    		Robot.alignmentWheels.startSlowAlign();
    	}
    }
    
    protected void end() {
    	Robot.alignmentWheels.stopAlign();
    }

    protected boolean isFinished() {
        return Robot.elevator.isAtTarget() || isTimedOut();
    }
}
