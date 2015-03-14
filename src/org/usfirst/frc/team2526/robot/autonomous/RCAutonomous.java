package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.DropAndLoadTote;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.UnloadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RCAutonomous extends CommandGroup {
    
	
    public  RCAutonomous() {
    	
    	addParallel(new StartAlign(false));
    	addParallel(new SetElevatorPosition(RobotValues.AUTO_RC));
    	addSequential(new WaitCommand(1));
    	addSequential(new TimedRotate(1.55, false));
    	addSequential(new TimedDrive(4.2, true));
    	addSequential(new StopAlign());
    	
//        addSequential(new SetElevatorPosition(RobotValues.CHUTE));
//        addSequential(new StabilizeTote());
//        addSequential(new TimedDrive(1, true));
//        addSequential(new UnloadTote());
//        addSequential(new TimedDrive(0.4, false));
//        addParallel(new StartAlign(false));
//        addSequential(new SetElevatorPosition(RobotValues.FLOOR));
//        addSequential(new TimedDrive(0.45, true));
//        addSequential(new LoadTote());
//        addSequential(new TimedRotate(1.55, false));
//        
//        addParallel(new StartAlign(false));
//        addSequential(new TimedDrive(1, true));
//        addSequential(new DropAndLoadTote(false));
//        addSequential(new TimedRotate(0.3, false));
//        addSequential(new TimedDrive(1, true));
    }
}
