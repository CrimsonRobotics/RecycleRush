package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

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

	}

	protected void initDefaultCommand() {
		setDefaultCommand((Command) Robot.driveChooser.getSelected());
	}

	/**
	 * Drive with Mecanum. with PID
	 * 
	 * @param yDistance
	 *            the forward and back value. -1 full back, 1 full forward
	 * @param xDistance
	 *            the left and right value (Strafing) -1 left, 1 right
	 * @param zDistance
	 *            the rotational value -1 counter-clockwise 1 clockwise
	 */
	public void driveWithPIDMech(double velocityY, double velocityX,
			double rotation) {

		// calculates desired percentVbus, the -1 through 1 number
		double desiredFL = -velocityY + rotation + velocityX;
		double desiredRL = -velocityY + rotation - velocityX;
		double desiredFR = -velocityY - rotation - velocityX;
		double desiredRR = -velocityY - rotation + velocityX;

		// Translate -1 through 1 to position change per 10ms
		double onefLMotorRev = fLMotor.getIZone() * 4;
		double onefRMotorRev = fRMotor.getIZone() * 4;
		double onerLMotorRev = rLMotor.getIZone() * 4;
		double onerRMotorRev = rRMotor.getIZone() * 4;

		// double speed = oneRev/100; // per second

		fLMotor.set(desiredFL);
		rLMotor.set(desiredRL);
		fRMotor.set(desiredFR);
		rRMotor.set(desiredRR);
	}

	public double getCurrentPosition() {
		return (fLMotor.getPosition() +
				rLMotor.getPosition() +  
				fRMotor.getPosition() + 
				rRMotor.getPosition()) / 4.0d;
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

	public void driveWithMech(double velocityY, double velocityX,
			double rotation) {
		drive.mecanumDrive_Cartesian(velocityX, velocityY, rotation, 0);
	}
}
