package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.elevator.HoldElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public static double FLOOR = 0,
				BIN = 25,
				TOP = 56,
				CARRY = 2.5,
				TOTE = 13;
	
	public CANTalon winch;
	
	public Solenoid brakeSolenoid, stabilizeSolenoid;
	
    public Elevator() {
    	super("Elevator");
    	
    	brakeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
    	stabilizeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.STABLE_ELEVATOR);
    	
    	winch = new CANTalon(RobotMap.WINCH_TALON);
    	
    	winch.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winch.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	//winch.enableLimitSwitch(true, true);
    	double p = 0.1; 
        double i = 0.001; 
        double d = 1; 
        double f = 0.0001; 
        int izone = 360; //Encoder Ticks
        double ramprate = 36; //Volts per second
        int profile = 0; //0 or 1
        
        winch.setPID(p, i, d, f, izone, ramprate, profile);
        
    	SmartDashboard.putNumber("maxPosition", 6000);
    	SmartDashboard.putNumber("setPosition", 1000);
    }
    
    public boolean isAtTop() {
    	return winch.isRevLimitSwitchClosed();
    }
    
    public boolean isAtBottom() {
    	return winch.isFwdLimitSwitchClosed();
    }
    
    public boolean isAtTarget() {
    	return winch.getEncPosition() == winch.getSetpoint();
    }
    
    public void calibrateMin() {
    	winch.setPosition(0);
    }
    
    public void calibrateMax() {
    	SmartDashboard.putNumber("maxPosition" , winch.getEncPosition());
    }
    
    
    public double getPosition() {
    	return winch.getEncPosition();
    }
    
    public boolean moveToPositionInches(double inches) {
    	double position = inchesToPosition(inches);
    	return moveToPositionTicks(position);
    }
    
    public double inchesToPosition(double inches) {
    	double maxPosition = SmartDashboard.getNumber("maxPosition");
    	return inches*maxPosition / 55.0;
    }
    
    public boolean moveToPositionTicks(double position) {
    	double maxPosition = SmartDashboard.getNumber("maxPosition");
    	if (position < maxPosition && position > 0) {
    		winch.changeControlMode(CANTalon.ControlMode.Position);
    		winch.set(position);
    		return true;
    	}
    	return false;
    }
    
    public void setToSmartValue() {
    	moveToPositionTicks(SmartDashboard.getNumber("setPosition"));
    }
    
    public void moveUp() {
    	winch.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winch.set(-0.5);
    }
    
    public void moveDown() {
    	winch.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winch.set(0.5);
    }
    
    public void stopElevator() {
    	winch.set(0);
    }
    
    public void applyBreak() {
    	brakeSolenoid.set(true);
    }
    
    public void releaseBreak() {
    	brakeSolenoid.set(false);
    }
    
    public void stabilizeTote() {
    	stabilizeSolenoid.set(true);
    }
    
    public void releaseTote() {
    	stabilizeSolenoid.set(false);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new HoldElevator());
    }
    
}
