package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.commands.ActivateFlipper;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.RotateAlignment;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.calibrations.CalibrateElevatorMax;
import org.usfirst.frc.team2526.robot.commands.calibrations.CalibrateElevatorMin;
import org.usfirst.frc.team2526.robot.commands.elevator.ElevatorDown;
import org.usfirst.frc.team2526.robot.commands.elevator.ElevatorUp;
import org.usfirst.frc.team2526.robot.commands.elevator.ManualSetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.StabilizeTote;

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
	Button primaryStickEight = new JoystickButton(primaryStick,8);
	Button primaryStickNine = new JoystickButton(primaryStick,9);
	//creates buttons on the primary stick (buttons 1-5)
	
	Button secondaryTriggerStick = new JoystickButton(secondaryStick,1);
	Button secondaryStickTwo = new JoystickButton(secondaryStick,2);
	Button secondaryStickThree = new JoystickButton(secondaryStick,3);
	Button secondaryStickFour = new JoystickButton(secondaryStick,4);
	Button secondaryStickFive = new JoystickButton(secondaryStick,5);
	
	Button secondaryStickSix = new JoystickButton(secondaryStick,6);
	Button secondaryStickSeven = new JoystickButton(secondaryStick,7);
	Button secondaryStickEight = new JoystickButton(secondaryStick,8);
	//creates buttons on the secondary stick (buttons 1-5)
	
	public OI() {
		primaryTriggerStick.whileHeld(new StartAlign());
		
		secondaryTriggerStick.whileHeld(new OpenArm());
		primaryStickTwo.whileHeld(new ReverseAlign());
		primaryStickEight.whileHeld(new RotateAlignment(true));
		primaryStickNine.whileHeld(new RotateAlignment(false));
		
		secondaryStickFour.whenPressed(new StabilizeTote());
		secondaryStickFive.whenPressed(new ReleaseTote());
		
		secondaryStickThree.whileHeld(new ElevatorUp());
		secondaryStickTwo.whileHeld(new ElevatorDown());
		
		secondaryStickSix.whenPressed(new CalibrateElevatorMax());
		secondaryStickSeven.whenPressed(new CalibrateElevatorMin());
		
		secondaryStickEight.whenPressed(new ManualSetElevatorPosition());
		
		
		primaryStickThree.whileHeld(new ActivateFlipper(true, true));
		primaryStickFour.whileHeld(new ActivateFlipper(true, false));
		primaryStickFive.whileHeld(new ActivateFlipper(false, true));
	}
}

