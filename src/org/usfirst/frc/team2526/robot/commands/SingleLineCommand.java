package org.usfirst.frc.team2526.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SingleLineCommand extends Command {
	
	private boolean finished;

	public SingleLineCommand(Subsystem system) {
		requires(system);
	}

	protected void initialize() {
		runCode();
	}

	protected void execute() {}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {}

	protected void interrupted() {}
	
	protected void runCode() {
		
	}

}
