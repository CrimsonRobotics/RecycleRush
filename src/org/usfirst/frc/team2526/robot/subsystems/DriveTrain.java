package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.Drive;
import org.usfirst.frc.team2526.robot.commands.PIDDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private CANTalon fLMotor,
						fRMotor,
						rLMotor,
						rRMotor;
	
	SendableChooser driveChooser;
	
	public DriveTrain() {
		super("Drive Train");
		
		fLMotor = new CANTalon(RobotMap.FRONT_LEFT_TALON);
		fRMotor = new CANTalon(RobotMap.FRONT_RIGHT_TALON);
		rLMotor = new CANTalon(RobotMap.REAR_LEFT_TALON);
		rRMotor = new CANTalon(RobotMap.REAR_RIGHT_TALON);
		
		fLMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		fRMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		rLMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		rRMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
        
		fLMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		fRMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rLMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rRMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        
        double p = 0.1; 
        double i = 0.001; 
        double d = 1; 
        double f = 0.0001; 
        int izone = 250; //Encoder Ticks
        double ramprate = 36; //Volts per second
        int profile = 0; //0 or 1
        
        fLMotor.setPID(p, i, d, f, izone, ramprate, profile);
        fRMotor.setPID(p, i, d, f, izone, ramprate, profile);
        rLMotor.setPID(p, i, d, f, izone, ramprate, profile);
        rRMotor.setPID(p, i, d, f, izone, ramprate, profile);
        
        driveChooser = new SendableChooser();
		driveChooser.addDefault("Normal Drive", new Drive());
		driveChooser.addObject("PID Drive", new PIDDrive());
		
		SmartDashboard.putData("Drive Type", driveChooser);
	}
	
    
    protected void initDefaultCommand() {
    	setDefaultCommand((Command)driveChooser.getSelected());
    }
    
    public void enablePID(boolean enable) {
    	if (enable) {
    		fLMotor.enableControl();
    		fRMotor.enableControl();
    		rLMotor.enableControl();
    		rRMotor.enableControl();
    	} else {
    		fLMotor.disableControl();
    		fRMotor.disableControl();
    		rLMotor.disableControl();
    		rRMotor.disableControl();
    	}
    		
    }
    
    /**
     * Drive with Mecanum. with PID
     * @param yDistance the forward and back value. -1 full back, 1 full forward
     * @param xDistance the left and right value (Strafing) -1 left, 1 right
     * @param zDistance the rotational value -1 counter-clockwise 1 clockwise
     */
    public void driveWithPIDMech(double velocityY, double velocityX, double rotation){
        
        double desiredFL = -velocityY + rotation + velocityX;      //calculates velocity for front left motor
        double desiredRL = -velocityY + rotation - velocityX;     //for rear left motor
        double desiredFR = -velocityY - rotation - velocityX;     //for front right motor
        double desiredRR = -velocityY - rotation + velocityX;     //for rear right motor
        
        fLMotor.set(desiredFL);
        rLMotor.set(desiredRL);
        fRMotor.set(desiredFR);
        rRMotor.set(desiredRR);
    }
}

