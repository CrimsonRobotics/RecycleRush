package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;
import org.usfirst.frc.team2526.robot.commands.drive.Drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private CANTalon fLMotor, fRMotor, rLMotor, rRMotor;

	RobotDrive drive;

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
		int izone = 250; // Encoder Ticks
		double ramprate = 36; // Volts per second
		int profile = 0; // 0 or 1

		fLMotor.setPID(p, i, d, f, izone, ramprate, profile);
		fRMotor.setPID(p, i, d, f, izone, ramprate, profile);
		rLMotor.setPID(p, i, d, f, izone, ramprate, profile);
		rRMotor.setPID(p, i, d, f, izone, ramprate, profile);

		drive = new RobotDrive(rLMotor, fLMotor, fRMotor, rRMotor);
		SmartDashboard.putNumber("Power", 1);

	}

	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public double getCurrentPosition() {
		return (Math.abs(fLMotor.getPosition()) + Math.abs(rLMotor.getPosition())
				+ Math.abs(fRMotor.getPosition()) + Math.abs(rRMotor.getPosition())) / 4.0d;
	}

	public void driveForward(double distance) {
		fLMotor.changeControlMode(CANTalon.ControlMode.Position);
		fRMotor.changeControlMode(CANTalon.ControlMode.Position);
		rLMotor.changeControlMode(CANTalon.ControlMode.Position);
		rRMotor.changeControlMode(CANTalon.ControlMode.Position);

		fLMotor.set(fLMotor.getSetpoint() + distance);
		fRMotor.set(fRMotor.getSetpoint() + distance);
		rLMotor.set(rLMotor.getSetpoint() + distance);
		rRMotor.set(rRMotor.getSetpoint() + distance);
	}

	// 1000 encoder ticks is about 60 degrees on robot
	public void rotate(double distance) {
		fLMotor.changeControlMode(CANTalon.ControlMode.Position);
		fRMotor.changeControlMode(CANTalon.ControlMode.Position);
		rLMotor.changeControlMode(CANTalon.ControlMode.Position);
		rRMotor.changeControlMode(CANTalon.ControlMode.Position);

		fLMotor.set(fLMotor.getSetpoint() + distance);
		fRMotor.set(fRMotor.getSetpoint() - distance);
		rLMotor.set(rLMotor.getSetpoint() + distance);
		rRMotor.set(rRMotor.getSetpoint() - distance);
	}

	public void driveWithMech(double velocityY, double velocityX,
			double rotation) {
		drive.mecanumDrive_Cartesian(velocityX,
				velocityY,
				rotation, 0);

	}

	public void driveWithExponentialMech(double velocityY, double velocityX,
			double rotation) {
		
		double power = SmartDashboard.getNumber("power");
		
		velocityY = Math.pow(Math.abs(velocityY), power) * velocityY > 0 ? 1 : -1;
		velocityX = Math.pow(Math.abs(velocityX), power) * velocityX > 0 ? 1 : -1;
		rotation = Math.pow(Math.abs(rotation), power) * rotation > 0 ? 1 : -1;
		

		double desiredFL = -velocityY + rotation + velocityX;
		double desiredRL = -velocityY + rotation - velocityX; 
		double desiredFR = -velocityY - rotation - velocityX; 
		double desiredRR = -velocityY - rotation + velocityX; 

		fLMotor.set(desiredFL);
		rLMotor.set(desiredRL);
		fRMotor.set(desiredFR);
		rRMotor.set(desiredRR);
	}

	public void update() {
		SmartDashboard.putNumber("Encoders: ", getCurrentPosition());
		SmartDashboard
				.putNumber("Encoder velocity: ", rLMotor.getEncVelocity());
	}
}
