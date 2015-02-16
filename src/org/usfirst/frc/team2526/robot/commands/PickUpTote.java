package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpTote extends CommandGroup {
    
    public  PickUpTote() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//addSequential(new SetElevatorPosition(Elevator.FLOOR));
    	//addSequential(new AutoPilotDrive(250));
    	addSequential(new SetElevatorPosition(Robot.elevator.getPosition()+10));
    	addSequential(new StabilizeTote());
    	addSequential(new SetElevatorPosition(Elevator.CARRY));

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
