package org.usfirst.frc.team2526.robot.commands.calibrations;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class CalibrateElevatorMax extends SimpleCommand {

    public CalibrateElevatorMax() {
        super(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.setGoalToCurrent();
    }

    protected void execute() {
    	Robot.elevator.shiftGoalUp(true);
    }

    protected boolean isFinished() {
        return Robot.elevator.isAtTop();
    }

    protected void end() {
    	Robot.elevator.calibrateMax();
    }
}
