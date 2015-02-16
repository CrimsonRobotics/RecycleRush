package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackTote extends CommandGroup {
    
    public  StackTote() {
        addSequential(new UnloadTote());
        addSequential(new AutoPilotDrive(-100));
        addSequential(new SetElevatorPosition(Elevator.FLOOR));
        addSequential(new AutoPilotDrive(100));
        addSequential(new LoadTote());
    }
}
