package org.usfirst.frc.team2526.robot.commands;

import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.alignment.StopAlign;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LoadTote extends CommandGroup {

	public LoadTote() {
		/*addParallel(new CloseArm());
		addParallel(new ReverseAlign());
		addSequential(new WaitCommand(0.2));
		addParallel(new StartAlign(false));
		addSequential(new WaitCommand(0.3));
		*/
		
		//addParallel(new OpenArm());
		
		addSequential(new SetElevatorPosition(RobotValues.FLOOR));
		addParallel(new StopAlign());
		addSequential(new SetElevatorPosition(RobotValues.TOTE_TWO*RobotValues.MAX_POSITION));
		addSequential(new WaitCommand(0.3));
	}

}
