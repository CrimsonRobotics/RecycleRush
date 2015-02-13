package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Subsystem;
=======
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
>>>>>>> branch 'master' of git@github.com:CrimsonRobotics/RecycleRush.git

/**
 *
 */
public class Elevator extends Subsystem/*extends PIDSubsystem*/ {
	
	public static double SKIM = 0.5,
				SCORING = 1,
				TOTE = 2; //NOT CALIBRATED TODO
	
	public CANTalon winchA,
					winchB;
	
	public Solenoid solenoidBrake;
	
    public Elevator() {
<<<<<<< HEAD
    	//super("Elevator", 1, 1, 1);
    	super("Elevator");
    	
=======
    	super("Elevator", 1, 1, 1);
    	solenoidBrake = new Solenoid(RobotMap.PCM_MAIN, RobotMap.WINCH_BRAKE);
>>>>>>> branch 'master' of git@github.com:CrimsonRobotics/RecycleRush.git
    	winchA = new CANTalon(RobotMap.WINCH_A_TALON);
    	winchB = new CANTalon(RobotMap.WINCH_B_TALON);
    	
    	winchA.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	winchA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	winchB.changeControlMode(CANTalon.ControlMode.Follower);
    	winchB.set(RobotMap.WINCH_A_TALON);
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
