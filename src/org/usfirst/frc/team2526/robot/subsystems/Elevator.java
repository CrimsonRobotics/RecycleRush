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
	
	public CANTalon winchMain,
					winchSlave;
	
	public Solenoid brakeSolenoid, stabilizeSolenoid;
	
    public Elevator() {
    	super("Elevator");
    	
    	brakeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
    	stabilizeSolenoid = new Solenoid(RobotMap.PCM_MAIN, RobotMap.STABLE_ELEVATOR);
    	
    	winchMain = new CANTalon(RobotMap.WINCH_A_TALON);
    	winchSlave = new CANTalon(RobotMap.WINCH_B_TALON);
    	
    	winchMain.changeControlMode(CANTalon.ControlMode.Position);
    	winchMain.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	winchMain.enableLimitSwitch(true, true);
    	double p = 0.1; 
        double i = 0.001; 
        double d = 1; 
        double f = 0.0001; 
        int izone = 360; //Encoder Ticks
        double ramprate = 36; //Volts per second
        int profile = 0; //0 or 1
        
        winchMain.setPID(p, i, d, f, izone, ramprate, profile);
    	
    	winchSlave.changeControlMode(CANTalon.ControlMode.Follower);
    	winchSlave.set(winchMain.getDeviceID());
    	
    	SmartDashboard.putNumber("maxPosition", 1000);
    	SmartDashboard.putNumber("setInches", 12);
    }
    
    public boolean isAtTop() {
    	return winchSlave.isRevLimitSwitchClosed();
    }
    
    public boolean isAtBottom() {
    	return winchMain.isFwdLimitSwitchClosed();
    }
    
    public boolean isAtTarget() {
    	return winchMain.getEncPosition() == winchMain.getSetpoint();
    }
    
    public void calibrateMin() {
    	winchMain.setPosition(0);
    }
    
    public void calibrateMax() {
    	SmartDashboard.putNumber("maxPosition" , winchMain.getEncPosition());
    }
    
    
    public double getPosition() {
    	return winchMain.getEncPosition();
    }
    
    public boolean moveToPositionInches(double inches) {
    	double position = inchesToPosition(inches);
    	return moveToPositionTicks(position);
    }
    
    public double inchesToPosition(double inches) {
    	double maxPosition = SmartDashboard.getNumber("maxPosition");
    	return inches*maxPosition / 56.0;
    }
    
    public boolean moveToPositionTicks(double ticks) {
    	double maxPosition = SmartDashboard.getNumber("maxPosition");
    	if (ticks < maxPosition && ticks > 0) {
    		winchMain.set(ticks);
    		return true;
    	}
    	return false;
    }
    
    public void moveUp() {
    	winchMain.set(-0.5);
    }
    
    public void moveDown() {
    	winchMain.set(0.5);
    }
    
    public void stopElevator() {
    	winchMain.set(0);
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
