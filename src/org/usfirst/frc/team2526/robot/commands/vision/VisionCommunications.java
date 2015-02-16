package org.usfirst.frc.team2526.robot.commands.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionCommunications {
	
	NetworkTable visionTable;

	public VisionCommunications() {
		visionTable = NetworkTable.getTable("vision");
		
	}
	
	public boolean isConnected() {
		return visionTable.isConnected();
	}
	
	public boolean doesToteExist() {
		if (isConnected()) {
			return visionTable.getBoolean("toteFound", false);
		}
		
		return false;
	}
	
	public double getToteAngle() {
		if (isConnected()) {
			return visionTable.getNumber("toteAngle", 0);
		}
		
		return 0;
	}
	
	public boolean doesBinExist() {
		if (isConnected()) {
			return visionTable.getBoolean("binFound", false);
		}
		
		return false;
	}
	
}
