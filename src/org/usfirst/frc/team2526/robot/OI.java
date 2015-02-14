package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.commands.ActivateFlipper;
import org.usfirst.frc.team2526.robot.commands.ElevatorDown;
import org.usfirst.frc.team2526.robot.commands.ElevatorUp;
import org.usfirst.frc.team2526.robot.commands.ReverseAlign;
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
	Button primaryStickTwo = new JoystickButton(primaryStick,2);
	Button primaryStickThree = new JoystickButton(primaryStick,3);
	Button primaryStickFour = new JoystickButton(primaryStick,4);
	Button primaryStickFive = new JoystickButton(primaryStick,5);
	//creates buttons on the primary stick (buttons 1-5)
	
	Button secondaryTriggerStick = new JoystickButton(secondaryStick,1);
	Button secondaryStickTwo = new JoystickButton(secondaryStick,2);
	Button secondaryStickThree = new JoystickButton(secondaryStick,3);
	Button secondaryStickFour = new JoystickButton(secondaryStick,4);
	Button secondaryStickFive = new JoystickButton(secondaryStick,5);
	//creates buttons on the secondary stick (buttons 1-5)
	
	public OI() {
		primaryTriggerStick.whileHeld(new StartAlign());
		//secondaryTriggerStick.whileHeld(new OpenArm());
		primaryStickTwo.whileHeld(new ReverseAlign());
		
		primaryStickThree.whileHeld(new ElevatorUp());
		primaryStickTwo.whileHeld(new ElevatorDown());
		
		secondaryStickThree.whileHeld(new ActivateFlipper(true, true));
		secondaryStickFour.whileHeld(new ActivateFlipper(true, false));
		secondaryStickFive.whileHeld(new ActivateFlipper(false, true));
	}
}

