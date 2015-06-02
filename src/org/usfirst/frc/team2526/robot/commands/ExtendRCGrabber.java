package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

public class ExtendRCGrabber extends SimpleCommand {

	public ExtendRCGrabber() {
		super(Robot.flipper);
	}

	protected void initialize() {
		Robot.flipper.extendRight();
	}
}
