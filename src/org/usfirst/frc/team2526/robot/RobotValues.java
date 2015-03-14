package org.usfirst.frc.team2526.robot;

public class RobotValues {
	public final static int WINCH_TOLERANCE = 16;

	public static final double FLOOR = 0, // Calibrated
			TOTE_ONE_GRAB = 400,
//			TOTE_ONE = 500,
			TOTE_TWO_GRAB = 450, 
			TOTE_TWO = 1800, // good
			AUTO_RC = 2300,
			SECOND_THIRD = 2371,
			SCORING = 600, 
			RC_TOP_TOTE = 2500,
			RC_HUG = 1080, // good
			CHUTE = 4179, // good
			CHUTE_STACK = 2360,
			STEP = 600; // good

	public static double MAX_POSITION = 5827;
	
	public static final int RC_TO_TOTE_DISTANCE = 1000;
	public static final int TOTE_TO_OP_TOTE_DISTANCE = 1500;
	public static final int TOTE_TO_AUTOZONE_DISTANCE = 1500;
	
	// 1142 WALL Stack
	
	
	public static final double DRIVE_P = 0.4;
	public static final double DRIVE_I = 0.01;
	public static final double DRIVE_D = 11;
	
	public static final double BL_180 = 3.9625;
	public static final double FR_180 = 2.65;
	public static final double BR_180 = 3.485;
	public static final double FL_180 = 2.84;
}
