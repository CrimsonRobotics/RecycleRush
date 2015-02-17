package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LoadTote extends CommandGroup {
    
    public  LoadTote() {
    	super("Load Tote");
    	//addParallel(new StartAlign());
    	addParallel(new OpenArm());
    	addSequential(new WaitCommand(0.5));
        addSequential(new SetElevatorPosition(Elevator.GRAB));
        addSequential(new WaitCommand(0.5));
    	addSequential(new StabilizeTote());
    	addParallel(new StopAlign());
    	//addSequential(new WaitCommand(0.5));
    	addSequential(new SetElevatorPosition(Elevator.SCORING));
    	
//    	addSequential(new SingleLineCommand() {
//    		public void runCode() {
//    			
//    		}
//    	});
    }
    
    
}
