package org.usfirst.frc.team2526.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //CAN Device IDs
	
	public static final int WINCH_A_TALON = 1;
	public static final int WINCH_B_TALON = 2;
	
	public static final int LEFT_ALIGN_TALON = 3;
	public static final int RIGHT_ALIGN_TALON = 4;
	
	public static final int REAR_RIGHT_TALON = 5;
	public static final int REAR_LEFT_TALON = 6;
	public static final int FRONT_RIGHT_TALON = 7;
	public static final int FRONT_LEFT_TALON = 8;
	
	public static final int PCM_MAIN = 9;
	public static final int PCM_SECONDARY = 10;
	
}
