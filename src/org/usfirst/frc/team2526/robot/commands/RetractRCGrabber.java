package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;

public class RetractRCGrabber extends SimpleCommand {

	public RetractRCGrabber() {
		super(Robot.flipper);
	}

	protected void initialize() {
		Robot.flipper.retractRight();
	}
}
