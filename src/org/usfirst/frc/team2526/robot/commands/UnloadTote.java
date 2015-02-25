package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.CloseArm;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ShiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UnloadTote extends CommandGroup {
    
    public  UnloadTote() {
    	addSequential(new ShiftElevator(-450));
    	addParallel(new ReverseAlign());
    	addSequential(new AutoPilotDrive(-700, 2));
    	addSequential(new StopAlign());
    	addSequential(new OpenArm());
    	addSequential(new SetElevatorPosition(RobotValues.FLOOR));
    	addSequential(new CloseArm());
    	addSequential(new ReleaseTote());
//    }
    }
}
