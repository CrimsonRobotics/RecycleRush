package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.RobotValues;
import org.usfirst.frc.team2526.robot.commands.drive.Drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem implements RobotMap {

	private CANTalon fLMotor, fRMotor, rLMotor, rRMotor;

	private WheelPID fLPID, fRPID, rLPID, rRPID;

	RobotDrive drive;

	public DriveTrain() {
		super("Drive Train");

		fLMotor = new CANTalon(FRONT_LEFT_TALON);
		fRMotor = new CANTalon(FRONT_RIGHT_TALON);
		rLMotor = new CANTalon(REAR_LEFT_TALON);
		rRMotor = new CANTalon(REAR_RIGHT_TALON);

		fLMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		fRMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		rLMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		rRMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);

		fLMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		fRMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rLMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rRMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		fLPID = new WheelPID("FrontLeft", fLMotor);
		fRPID = new WheelPID("FrontRight", fRMotor);
		rLPID = new WheelPID("BackLeft", rLMotor);
		rRPID = new WheelPID("BackRight", rRMotor);
		
		LiveWindow.addActuator("Drive Train", "Front Left", fLPID.getPIDController());
		LiveWindow.addActuator("Drive Train", "Front Right", fRPID.getPIDController());
		LiveWindow.addActuator("Drive Train", "Back Left", rLPID.getPIDController());
		LiveWindow.addActuator("Drive Train", "Back Right", rRPID.getPIDController());
		
		
		fLPID.setOutputRange(-0.4, 0.4);
		fRPID.setOutputRange(-0.4, 0.4);
		rLPID.setOutputRange(-0.4, 0.4);
		rRPID.setOutputRange(-0.4, 0.4);

		drive = new RobotDrive(rLMotor, fLMotor, fRMotor, rRMotor);

	}
	
	public void enable() {
		fLPID.enable();
		fRPID.enable();
		rLPID.enable();
		rRPID.enable();
	}
	
	public void disable() {
		fLPID.disable();
		fRPID.disable();
		rLPID.disable();
		rRPID.disable();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public double getCurrentPosition() {
		return (Math.abs(fLMotor.getPosition())
				+ Math.abs(rLMotor.getPosition())
				+ Math.abs(fRMotor.getPosition()) + Math.abs(rRMotor
				.getPosition())) / 4.0d;
	}

	public void resetCurrentPosition() {
		fLMotor.setPosition(0);
		rLMotor.setPosition(0);
		fRMotor.setPosition(0);
		rRMotor.setPosition(0);
	}

	public void driveForwardDistance(double distance) {
		fLPID.setSetpoint(distance);
		fRPID.setSetpoint(-distance);
		rLPID.setSetpoint(distance);
		rRPID.setSetpoint(-distance);
	}
	
	public void driveForwardConstant(double speed) {
		fLMotor.set(speed);
		rLMotor.set(speed);
		fRMotor.set(-speed);
		rRMotor.set(-speed);
	}
	
	public void driveRightConstant(double speed) {
		fLMotor.set(speed);
		rLMotor.set(speed);
		fRMotor.set(speed);
		rRMotor.set(speed);
	}

	public void stopDriving() {
		fLMotor.set(0);
		rLMotor.set(0);
		fRMotor.set(0);
		rRMotor.set(0);
	}
	public void rotateFrame(boolean half, int direction){
    	if(half){
    		fLPID.setSetpoint(RobotValues.FL_180 * direction);
    		fRPID.setSetpoint(RobotValues.FR_180 * direction);
    		rLPID.setSetpoint(RobotValues.BL_180 * direction);
    		rRPID.setSetpoint(RobotValues.BR_180 * direction);
    	}else{
    		fLPID.setSetpoint((RobotValues.FL_180/2) * direction);
    		fRPID.setSetpoint((RobotValues.FR_180/2) * direction);
    		rLPID.setSetpoint((RobotValues.BL_180/2) * direction);
    		rRPID.setSetpoint((RobotValues.BR_180/2) * direction);
    	}
    }
    
	public void driveWithMech(double velocityY, double velocityX,
			double rotation) {

		double power = 2;

		int signY = velocityY > 0 ? 1 : -1;
		velocityY = Math.pow(Math.abs(velocityY), power) * signY;

		int signX = velocityX > 0 ? 1 : -1;
		velocityX = Math.pow(Math.abs(velocityX), power) * signX;

		int signR = rotation > 0 ? 1 : -1;
		rotation = Math.pow(Math.abs(rotation), power) * signR;

		SmartDashboard.putNumber("Y", velocityY);
		SmartDashboard.putNumber("X", velocityX);
		SmartDashboard.putNumber("R", rotation);

		drive.mecanumDrive_Cartesian(velocityX, velocityY, rotation, 0);

	}

	public boolean isDone() {
		return (fLPID.finished() && fRPID.finished() && rLPID.finished() && rRPID.finished());
	}

	public void update() {
		SmartDashboard.putNumber("Encoders: ", getCurrentPosition());
		SmartDashboard.putNumber("Encoder velocity: ", rLMotor.getEncVelocity());
	}
}
