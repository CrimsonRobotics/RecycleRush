package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class SetElevatorAfterLoad extends SimpleCommand {

    public SetElevatorAfterLoad() {
		// Use requires() here to declare subsystem dependencies
		super(Robot.elevator);
	}

	protected void initialize() {
		if (Robot.elevator.getOnStep())
			Robot.elevator.moveToPositionTicks(RobotValues.STEP*RobotValues.MAX_POSITION);
		else
			Robot.elevator.moveToPositionTicks(RobotValues.SCORING*RobotValues.MAX_POSITION);
	}

	protected boolean isFinished() {
		return Robot.elevator.isAtTarget();
	}
}
