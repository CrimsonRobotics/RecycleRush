package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends  PIDSubsystem {
	
	public static double SKIM = 0.5,
				SCORING = 1,
				TOTE = 2; //NOT CALIBRATED TODO
	
	public CANTalon winchA,
					winchB;
	
	public Solenoid solenoidBrake;
	public DigitalInput upperLimitSwitch,
						lowerLimitSwitch;
	
    public Elevator() {
    	super("Elevator", 1, 1, 1);
    	
    	solenoidBrake = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
    	
    	winchA = new CANTalon(RobotMap.WINCH_A_TALON);
    	winchB = new CANTalon(RobotMap.WINCH_B_TALON);
    	
    	winchA.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winchA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	winchB.changeControlMode(CANTalon.ControlMode.Follower);
    	winchB.set(RobotMap.WINCH_A_TALON);
    	
    	upperLimitSwitch = new DigitalInput();
    	
    	this.setOutputRange(0, 0);
    }
    
    private void safeSetMotorSpeed(double speed) {
    	if (speed > 1 || speed < -1) {
    		return;
    	} else if (upperLimitSwitch )
    }
    
    public void moveUp() {
    	winchA.set(0.5);
    }
    
    public void moveDown() {
    	winchA.set(-0.5);
    }
    
    public void stopElevator() {
    	winchA.set(0);
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
