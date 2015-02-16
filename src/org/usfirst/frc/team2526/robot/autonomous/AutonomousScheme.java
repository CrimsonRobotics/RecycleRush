package org.usfirst.frc.team2526.robot.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team2526.robot.commands.drive.AutoPilotDrive;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousScheme {
	
	boolean[] targets; 
	int startingTote;
	boolean toteSide;

	public AutonomousScheme(int startingTote, boolean toteSide, boolean[] targets) {
		this.targets = targets;
	}
	
	public ArrayList<Command> getListOfCommands() {
		ArrayList<Command> commands = new ArrayList<Command>();
		
		if (toteSide) {
			commands.add(new SmartTotePickup());
		} else {
			commands.add(new SmartBinPickup());
			commands.add(new AutoPilotDrive(1500));
		}
		
		return commands;
	}

}

