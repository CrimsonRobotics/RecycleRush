package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.drive.TimedStrafe;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.SlowSetPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
	
    public  Autonomous() {
    	addParallel(new SetElevatorPosition(RobotValues.TOTE_TWO*RobotValues.MAX_POSITION));
        addSequential(new TimedStrafe(1, false));

        addParallel(new CloseArm());
        addParallel(new StartAlign(false));
        addSequential(new TimedDrive(2, true));
        
        addParallel(new LoadTote());
        addSequential(new WaitCommand(3));
        addSequential(new TimedRotate(3.6, true)); //180 degrees
        
        addParallel(new SetElevatorPosition(RobotValues.TOTE_TWO*RobotValues.MAX_POSITION));
        addSequential(new TimedDrive(3, true));
        
        addParallel(new LoadTote());
        addSequential(new WaitCommand(3));

        addSequential(new TimedRotate(1.8, false)); // 1.55
        addSequential(new TimedDrive(5, true));
        addSequential(new SetElevatorPosition(RobotValues.FLOOR));
    }
}
