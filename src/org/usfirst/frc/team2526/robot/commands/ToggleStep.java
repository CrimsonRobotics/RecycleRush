package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

/**
 *
 */
public class ToggleStep extends SimpleCommand {

    public ToggleStep() {
        super(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	boolean useStep = Robot.elevator.getOnStep();
    	Robot.elevator.setOnStep(!useStep);
    }
}
