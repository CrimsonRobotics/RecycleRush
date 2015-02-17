package org.usfirst.frc.team2526.robot.commands.calibrations;

import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CalibrateElevator extends CommandGroup {

	public CalibrateElevator() {
		
		addSequential(new CalibrateElevatorMin());
		addSequential(new CalibrateElevatorMax());
		addSequential(new SetElevatorPosition(SmartDashboard.getNumber("maxPosition")/2));
		
	}
}
