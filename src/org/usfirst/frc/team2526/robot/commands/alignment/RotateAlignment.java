package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateAlignment extends Command {
	
	private boolean clockwise;

	public RotateAlignment(boolean clockwise) {
		requires(Robot.alignmentWheels);
		
		this.clockwise = clockwise;
	}

	protected void initialize() {
		Robot.alignmentWheels.rotate(clockwise);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.alignmentWheels.stopAlign();
	}

	protected void interrupted() {
		end();
	}

}
