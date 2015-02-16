package org.usfirst.frc.team2526.robot.commands.calibrations;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateElevator extends CommandGroup {

	public CalibrateElevator() {
		
		addSequential(new CalibrateElevatorMin());
		addSequential(new CalibrateElevatorMax());
		
	}
}
