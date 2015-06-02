package org.usfirst.frc.team2526.robot.faults;

public interface IFaultChecker {
	public boolean isHardwareFault();
	public boolean isSoftwareFault();
	
	public void resetSoftwareFault();
	public void resetHardwareFault();
}
