package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateAlignment extends Command {
	
	private boolean clockwise;

	public RotateAlignment(boolean clockwise) {
		requires(Robot.alignment);
		
		this.clockwise = clockwise;
	}

	protected void initialize() {
		Robot.alignment.rotate(clockwise);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.alignment.stopAlign();
	}

	protected void interrupted() {
		end();
	}

}
