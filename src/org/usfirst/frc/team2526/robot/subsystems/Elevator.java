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
    
    public void pidBrake() {
    	winchMain.enableBrakeMode(true);
    }
    
    public double getPosition() {
    	return winchMain.getEncPosition();
    }
    
    public boolean moveToPosition(double inches) {
    	double maxPosition = SmartDashboard.getNumber("maxPosition");
    	double position = inches*maxPosition / 56;
    	if (position < maxPosition && position > 0) {
    		winchMain.set(position);
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
    	return winchMain.getEncPosition();
    }
    
    protected void usePIDOutput(double output) {
    	SmartDashboard.putNumber("Elevator PID", output);
       // winchA.set(output);
    }
}
