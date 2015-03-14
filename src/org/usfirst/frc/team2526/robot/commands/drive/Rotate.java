package org.usfirst.frc.team2526.robot.commands.drive;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class Rotate extends SimpleCommand {
	boolean half;
	int direction;
    public Rotate(boolean half, int direction) {
    	super(Robot.driveTrain);
    	
        this.half = half;
        this.direction = direction;
    }

    protected void initialize() {
    	Robot.driveTrain.rotateFrame(half, direction);
    }
}
