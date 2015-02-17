package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnloadTote extends CommandGroup {
    
    public  UnloadTote() {
    	addSequential(new ReleaseTote());
    	addSequential(new SetElevatorPosition(Robot.elevator.getPosition()-300));
    }
}
