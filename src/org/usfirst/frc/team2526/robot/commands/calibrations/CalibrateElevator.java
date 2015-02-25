package org.usfirst.frc.team2526.robot.commands.calibrations;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateElevator extends CommandGroup {

	public CalibrateElevator() {
		
		addSequential(new CalibrateElevatorMin());
		addSequential(new CalibrateElevatorMax());
		addSequential(new SetElevatorPosition(RobotValues.MAX_POSITION/2));
		
	}
}
