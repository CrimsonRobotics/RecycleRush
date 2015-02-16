package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LoadTote extends CommandGroup {
    
    public  LoadTote() {
        addSequential(new SetElevatorPosition(Robot.elevator.getPosition()+10));
    	addSequential(new StabilizeTote());
    	addSequential(new SetElevatorPosition(Elevator.CARRY));
    	
//    	addSequential(new SingleLineCommand() {
//    		public void runCode() {
//    			
//    		}
//    	});
    }
}
