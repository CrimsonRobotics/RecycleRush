package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.DropAndLoadTote;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.UnloadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
	
    public  Autonomous() {
    	addSequential(new ShiftElevator(400));
    	addSequential(new StabilizeTote());
        addSequential(new SetElevatorPosition(RobotValues.RC_TOP_TOTE));
        addSequential(new TimedDrive(0.2, true));
        addSequential(new UnloadTote());
        addSequential(new SetElevatorPosition(RobotValues.FLOOR));
        addSequential(new LoadTote());
        addSequential(new TimedRotate(1, true));
        
        addParallel(new StartAlign(false));
        addSequential(new TimedDrive(1, true));
        addSequential(new DropAndLoadTote(false));
        addSequential(new TimedRotate(0.3, false));
        addSequential(new TimedDrive(1, true));
    }
}
