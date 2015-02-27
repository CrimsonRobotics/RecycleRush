package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class SetElevatorPosition extends SimpleCommand {
	double position;

	public SetElevatorPosition(double position) {
		// Use requires() here to declare subsystem dependencies
		super(Robot.elevator);
		
		this.position = position;
	}

	protected void initialize() {
		Robot.elevator.moveToPositionTicks(position);
	}

	protected boolean isFinished() {
		return Robot.elevator.isAtTarget();
	}
}
