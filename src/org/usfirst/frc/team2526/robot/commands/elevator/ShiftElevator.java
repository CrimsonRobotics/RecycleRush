package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class ShiftElevator extends SimpleCommand {
	double shiftDistance;
	
    public ShiftElevator(double shift) {
    	super(Robot.elevator);
    	this.shiftDistance = shift;
    }
    
    protected void initialize() {
    	Robot.elevator.moveToPositionTicks(Robot.elevator.getPosition()+shiftDistance);
    }
    
    protected boolean isFinished() {
    	return Robot.elevator.isAtTarget() || Robot.elevator.isAtBottom() || Robot.elevator.isAtTop();
    }
}
