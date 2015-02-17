package org.usfirst.frc.team2526.robot.subsystems;

import org.usfirst.frc.team2526.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flipper extends Subsystem {
    
    Solenoid leftFlipper,
    		rightFlipper;
    
    public Flipper() {
    	leftFlipper = new Solenoid(RobotMap.PCM_MAIN, RobotMap.FLIPPER_LEFT);
    	rightFlipper = new Solenoid(RobotMap.PCM_MAIN, RobotMap.FLIPPER_RIGHT);
    }

    public void initDefaultCommand() {
//    	this.setDefaultCommand(new RetractFlipper());
    }
    
    public void extendLeft() {
    	rightFlipper.set(true);
    }
    
    public void extendRight() {
    	leftFlipper.set(true);
    }
    
    public void retractLeft() {
    	rightFlipper.set(false);
    }
    
    public void retractRight() {
    	leftFlipper.set(false);
    }
}

