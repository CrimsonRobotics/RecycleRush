package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class ReverseAlign extends SimpleCommand {

    public ReverseAlign() {
        super(Robot.alignmentWheels);
    }

    protected void initialize() {
    	Robot.alignmentWheels.reverseAlign();
    }

    protected void end() {
    	Robot.alignmentWheels.stopAlign();
    }

}
