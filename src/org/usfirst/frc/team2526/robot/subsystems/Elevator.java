package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	
	public static double SKIM = 0.5,
				SCORING = 1,
				TOTE = 2; //NOT CALIBRATED TODO
	
	public CANTalon winchA,
					winchB;
	
	public Solenoid solenoidBrake;
	
    public Elevator() {
    	super("Elevator", 1, 1, 1);
    	solenoidBrake = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
    	winchA = new CANTalon(RobotMap.WINCH_A_TALON);
    	winchB = new CANTalon(RobotMap.WINCH_B_TALON);
    	
    	winchA.changeControlMode(CANTalon.ControlMode.Position);
    	winchA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	winchB.changeControlMode(CANTalon.ControlMode.Follower);
    	winchB.set(RobotMap.WINCH_A_TALON);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return winchA.getEncPosition();
    }
    
    protected void usePIDOutput(double output) {
        winchA.set(output);
    }
}
