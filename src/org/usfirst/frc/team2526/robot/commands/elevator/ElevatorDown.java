package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class ElevatorDown extends SimpleCommand {

	public ElevatorDown() {
		super(Robot.elevator);
	}

	protected void initialize() {
		Robot.elevator.setGoalToCurrent();
	}

    protected void execute() {
    	Robot.elevator.shiftGoalDown(true);
    }

}
