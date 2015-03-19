package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.DropAndLoadTote;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.TimedDrive;
import org.usfirst.frc.team2526.robot.commands.drive.TimedRotate;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.SlowSetPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
	
    public  Autonomous() {
        addSequential(new SetElevatorPosition(RobotValues.CHUTE));
        addParallel(new OpenArm());
        addSequential(new TimedDrive(0.5, true));
        addParallel(new StartAlign(false));
        addParallel(new CloseArm());
        addSequential(new WaitCommand(0.3));
        
        addSequential(new SlowSetPosition(RobotValues.FLOOR));
        addSequential(new LoadTote());
        addSequential(new TimedRotate(3.1, true));
        
        addParallel(new StartAlign(false));
        addSequential(new TimedDrive(3, true));
        addSequential(new DropAndLoadTote(false));
        addSequential(new TimedRotate(1.55, false));
        addSequential(new StopAlign());
        addSequential(new TimedDrive(4.2, true));
    }
}
