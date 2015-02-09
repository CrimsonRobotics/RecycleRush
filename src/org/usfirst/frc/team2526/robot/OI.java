package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.commands.StartAlign;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick primaryStick = new Joystick(0);
	Joystick secondaryStick = new Joystick(1);
	
	public Joystick getPrimaryStick() {
		return primaryStick;
	}
	
	public Joystick getSecondaryStick() {
		return secondaryStick;
	}
	
	Button primaryTriggerStick = new JoystickButton(primaryStick, 1);
	
	public OI() {
		primaryTriggerStick.whileHeld(new StartAlign());
	}
}

