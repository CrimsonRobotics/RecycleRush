package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.HoldElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.LimitSwitch;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends  PIDSubsystem {
	
	public static double SKIM = 0.5,
				SCORING = 1,
				TOTE = 2; //NOT CALIBRATED TODO
	
	public CANTalon winchA,
					winchB;
	
	public Solenoid brakeSolenoid, stabilizeSolenoid;
	
	public LimitSwitch upperLimitSwitch,
						lowerLimitSwitch;
	
	private double maxPosition;
	
    public Elevator() {
    	super("Elevator", 1, 1, 1);
    	
    	brakeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
    	stabilizeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.STABLE_ELEVATOR);
    	
    	winchA = new CANTalon(RobotMap.WINCH_A_TALON);
    	winchB = new CANTalon(RobotMap.WINCH_B_TALON);
    	
    	winchA.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winchA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	winchB.changeControlMode(CANTalon.ControlMode.Follower);
    	winchB.set(RobotMap.WINCH_A_TALON);
    	
    	upperLimitSwitch = new LimitSwitch(RobotMap.UPPER_LIMIT_SWITCH);
    	lowerLimitSwitch = new LimitSwitch(RobotMap.LOWER_LIMIT_SWITCH);
    }
    
    public boolean isAtTop() {
    	return upperLimitSwitch.isPressed();
    }
    
    public boolean isAtBottom() {
    	return lowerLimitSwitch.isPressed();
    }
    
    private void calibrateMin() {
    	winchA.setPosition(0);
    }
    
    private void calibrateMax() {
    	maxPosition = getPosition();
    	this.setInputRange(0, maxPosition);
    }
    
    public void pidBrake() {
    	this.setSetpoint(this.getPosition());
    }
    
    public boolean moveToPosition(double position) {
    	if (position < maxPosition && position > 0) {
    		this.setSetpoint(position);
    		return true;
    	}
    	return false;
    }
    
    public void moveUp() {
    	winchA.set(-0.5);
    }
    
    public void moveDown() {
    	winchA.set(0.5);
    }
    
    public void stopElevator() {
    	winchA.set(0);
    }
    
    public void applyBreak() {
    	//brakeSolenoid.set(true);
    	System.out.println("Brake not installed yet");
    }
    
    public void releaseBreak() {
    	//brakeSolenoid.set(false);
    	System.out.println("Brake not installed yet");
    }
    
    public void stabilizeTote() {
    	//stabilizeSolenoid.set(true);
    	System.out.println("Tote Pneu not installed yet");
    }
    
    public void releaseTote() {
    	//stabilizeSolenoid.set(false);
    	System.out.println("Totematics not installed yet");
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new HoldElevator());
    }
    
    protected double returnPIDInput() {
    	return winchA.getEncPosition();
    }
    
    protected void usePIDOutput(double output) {
    	SmartDashboard.putNumber("Elevator PID", output);
       // winchA.set(output);
    }
}
