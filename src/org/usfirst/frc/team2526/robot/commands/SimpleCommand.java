package org.usfirst.frc.team2526.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SimpleCommand extends Command {

	public SimpleCommand(Subsystem system) {
		requires(system);
	}

	protected void initialize() {}

	protected void execute() {}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {}

	protected void interrupted() {
		end();
	}

}
