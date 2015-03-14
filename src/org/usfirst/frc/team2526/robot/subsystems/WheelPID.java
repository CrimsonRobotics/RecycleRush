package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Based off of Team 1160's PID Drivetrain
 * 
 * Modifications by Team 2526
 */
public class WheelPID extends PIDSubsystem implements RobotMap {

	private CANTalon motor;
	
	
    /******************************************************************
     * Constructor for the PID Subsystem, used with each wheel
     * -super() is required to initialize the subsystem with the given
     *  values
     * -this.variable = variable; allows the entire class to use the
     *  variables passed into PID() as arguments
     ******************************************************************/
    public WheelPID(String name, CANTalon motor) {
    	super(name, P, I, D);
    	this.motor = motor;
    	
    	motor.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	this.getPIDController().setContinuous();
    	this.getPIDController().setAbsoluteTolerance(ABS_TOL);
    }
    
    
    /******************************************************************
     * @see DriveTrain#reset() 
     * for information
     ******************************************************************/
    public void reInit(){
    	getPIDController().reset();
    	getPIDController().setPID(SmartDashboard.getNumber("kP"), I, SmartDashboard.getNumber("kD"));
    	this.motor.setPosition(0);
    }
    
    
    /******************************************************************
     * returnPIDInput() is a method that is required by PIDSubsystem
     * -Allows monitoring of the PID values in SmartDashboard
     * -enc.pidGet() passes the value of the encoder into the
     *  PIDSubystem, allowing for motor feedback
     ******************************************************************/
    protected double returnPIDInput() {
    	return motor.getEncPosition();
    }
    
    protected void logEncoder(){
    	SmartDashboard.putNumber(this.getName() + ": PID: ", motor.getEncVelocity());
    }
    
    
    /******************************************************************
     * @see DriveTrain#itDone()
     * for information
     ******************************************************************/
    public boolean finished(){
    	SmartDashboard.putNumber(this.getName() + ": error: ", this.getPIDController().getError());
    	return this.getPIDController().onTarget();
    }
    
    
    /******************************************************************
     * usePIDOutput() is required by PIDSubsystem
     * -takes the calculations done by the system and passes them
     *  into the motor
     *  -output is reduced by half so motors don't run at full speed
     ******************************************************************/
    protected void usePIDOutput(double output) {
    	SmartDashboard.putNumber(this.getName() + ": MOTOR: ", output*(.6));
    	this.logEncoder();
    	if(this.getName().equalsIgnoreCase("FrontRight"));
    	motor.pidWrite(output*(.6));
    }
    
    public void initDefaultCommand() {}
}
