package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

public class RotateAlignment extends SimpleCommand {
	
	private boolean clockwise;

	public RotateAlignment(boolean clockwise) {
		super(Robot.alignmentWheels);
		
		this.clockwise = clockwise;
	}

	protected void initialize() {
		Robot.alignmentWheels.rotate(clockwise);
	}

	protected void end() {
		Robot.alignmentWheels.stopAlign();
	}

}
