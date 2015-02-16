package org.usfirst.frc.team2526.robot.commands.elevator;

import org.usfirst.frc.team2526.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualSetElevatorPosition extends Command {
	double position;

	public ManualSetElevatorPosition() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.elevator);
		
		this.position = SmartDashboard.getNumber("setPosition");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.setToSmartValue();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Current Position", Robot.elevator.getPosition());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.elevator.isAtTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.applyBreak();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
