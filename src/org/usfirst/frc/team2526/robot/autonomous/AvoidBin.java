package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;
import org.usfirst.frc.team2526.robot.commands.drive.Rotate;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AvoidBin extends CommandGroup {

	public AvoidBin() {
		addParallel(new ReverseAlign());
		addSequential(new Rotate(250));
		addSequential(new AutoPilotDrive(500));
		addSequential(new Rotate(-500));
		addSequential(new AutoPilotDrive(500));
		addSequential(new Rotate(250));
		
	}
}
