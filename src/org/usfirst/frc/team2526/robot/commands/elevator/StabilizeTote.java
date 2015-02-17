package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class StabilizeTote extends SimpleCommand {

    public StabilizeTote() {
    	super(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.stabilizeTote();
    }
}
