package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.vision.VisionDetectToteRemoval;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SmartBinPickup extends CommandGroup {

	public SmartBinPickup() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		addParallel(new StartAlign());
		addSequential(new LoadTote());
		addSequential(new SetElevatorPosition(Elevator.TOTE));
		addSequential(new VisionDetectToteRemoval(), 2);
		
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
