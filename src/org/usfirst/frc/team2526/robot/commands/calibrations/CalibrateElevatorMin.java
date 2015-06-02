package org.usfirst.frc.team2526.robot.commands.calibrations;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class CalibrateElevatorMin extends SimpleCommand {

    public CalibrateElevatorMin() {
        super(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.setGoalToCurrent();
    }

    protected void execute() {
    	Robot.elevator.shiftGoalDown(true);
    }

    protected boolean isFinished() {
        return Robot.elevator.isAtBottom();
    }

    protected void end() {
    	Robot.elevator.calibrateMin();
    }
}
