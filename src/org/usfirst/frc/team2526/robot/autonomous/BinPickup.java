package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BinPickup extends CommandGroup {

	public BinPickup() {
		
		
		addParallel(new StartAlign());
		addSequential(new SetElevatorPosition(Elevator.BIN));
		addSequential(new LoadTote());
		
		
	}
}
