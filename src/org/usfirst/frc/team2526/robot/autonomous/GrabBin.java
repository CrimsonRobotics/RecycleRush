package org.usfirst.frc.team2526.robot.autonomous;

import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.drive.PIDAutoPilotDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabBin extends CommandGroup {

	public GrabBin() {
		addSequential(new StartAlign(false));
		addSequential(new PIDAutoPilotDrive(500, 2));
	}
}
