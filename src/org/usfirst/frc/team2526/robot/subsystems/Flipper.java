package org.usfirst.frc.team2526.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flipper extends Subsystem {
    
    Solenoid leftFlipper,
    		rightFlipper;

    public void initDefaultCommand() {}
    
    public void extendLeft() {
    	rightFlipper.set(true);
    }
    
    public void extendRight() {
    	leftFlipper.set(true);
    }
    
    public void retractRight() {
    	rightFlipper.set(false);
    }
    
    public void retractLeft() {
    	rightFlipper.set(false);
    }
}

