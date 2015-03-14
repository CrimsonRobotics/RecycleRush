package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.commands.ActivateFlipper;
import org.usfirst.frc.team2526.robot.commands.DropAndLoadTote;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.UnloadTote;
import org.usfirst.frc.team2526.robot.commands.alignment.OpenArm;
import org.usfirst.frc.team2526.robot.commands.alignment.ReverseAlign;
import org.usfirst.frc.team2526.robot.commands.alignment.RotateAlignment;
import org.usfirst.frc.team2526.robot.commands.alignment.StartAlign;
import org.usfirst.frc.team2526.robot.commands.calibrations.CalibrateElevator;
import org.usfirst.frc.team2526.robot.commands.elevator.ElevatorDown;
import org.usfirst.frc.team2526.robot.commands.elevator.ElevatorUp;
import org.usfirst.frc.team2526.robot.commands.elevator.OneTote;
import org.usfirst.frc.team2526.robot.commands.elevator.ReleaseTote;
import org.usfirst.frc.team2526.robot.commands.elevator.SetElevatorPosition;
import org.usfirst.frc.team2526.robot.commands.elevator.SlowSetPosition;
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
	Joystick controlPanel = new Joystick(2);
	
	public Joystick getPrimaryStick() {
		return primaryStick;
	}
	
	public Joystick getSecondaryStick() {
		return secondaryStick;
	}
	
	public Joystick getControlPanel() {
		return controlPanel;
	}
	
	Button primaryTriggerStick = new JoystickButton(primaryStick, 1);
	Button primaryStickTwo = new JoystickButton(primaryStick,2);
	Button primaryStickThree = new JoystickButton(primaryStick,3);
	Button primaryStickFour = new JoystickButton(primaryStick,4);
	Button primaryStickFive = new JoystickButton(primaryStick,5);
	Button primaryStickSix = new JoystickButton(primaryStick,6);
	Button primaryStickSeven = new JoystickButton(primaryStick,7);
	Button primaryStickEight = new JoystickButton(primaryStick,8);
	Button primaryStickNine = new JoystickButton(primaryStick,9);
	Button primaryStickTen = new JoystickButton(primaryStick,10);
	Button primaryStickEleven = new JoystickButton(primaryStick,11);
	//creates buttons on the primary stick (buttons 1-5)
	
	Button secondaryTriggerStick = new JoystickButton(secondaryStick,1);
	Button secondaryStickTwo = new JoystickButton(secondaryStick,2);
	Button secondaryStickThree = new JoystickButton(secondaryStick,3);
	Button secondaryStickFour = new JoystickButton(secondaryStick,4);
	Button secondaryStickFive = new JoystickButton(secondaryStick,5);
	
	Button secondaryStickSix = new JoystickButton(secondaryStick,6);
	Button secondaryStickSeven = new JoystickButton(secondaryStick,7);
	Button secondaryStickEight = new JoystickButton(secondaryStick,8);
	Button secondaryStickNine = new JoystickButton(secondaryStick,9);
	
	
	Button secondaryStickTen = new JoystickButton(secondaryStick, 10);
	Button secondaryStickEleven = new JoystickButton(secondaryStick, 11);
	//creates buttons on the secondary stick (buttons 1-5)
	
	Button controlTopLeft = new JoystickButton(controlPanel, 5);
	Button controlTopMiddle = new JoystickButton(controlPanel, 2);
	Button controlTopRight = new JoystickButton(controlPanel, 1);
	Button controlMiddleLeft = new JoystickButton(controlPanel, 7);
	Button controlMiddleMiddle = new JoystickButton(controlPanel, 4);
	Button controlMiddleRight = new JoystickButton(controlPanel, 8);
	Button controlBottomLeft = new JoystickButton(controlPanel, 6);
	Button controlBottomMiddle = new JoystickButton(controlPanel, 3);
	Button controlBottomRight = new JoystickButton(controlPanel, 9);
	
	public OI() {
		primaryTriggerStick.whileHeld(new StartAlign(true));
		
		secondaryTriggerStick.whileHeld(new OpenArm());
		primaryStickTwo.whileHeld(new ReverseAlign());
		primaryStickEight.whileHeld(new RotateAlignment(true));
		primaryStickNine.whileHeld(new RotateAlignment(false));
		
		secondaryStickFive.whenPressed(new StabilizeTote());
		
		secondaryStickThree.whileHeld(new ElevatorUp());
		secondaryStickTwo.whileHeld(new ElevatorDown());
		
		primaryStickSix.whenPressed(new CalibrateElevator());
		primaryStickSeven.whenPressed(new ReleaseTote());
		
		controlTopLeft.whenPressed(new LoadTote());
		controlTopRight.whenPressed(new UnloadTote());
		controlTopMiddle.whenPressed(new DropAndLoadTote(false));
		controlBottomLeft.whenPressed(new SetElevatorPosition(RobotValues.SCORING));
		
		controlMiddleRight.whenPressed(new SetElevatorPosition(RobotValues.CHUTE));
		controlMiddleMiddle.whenPressed(new SetElevatorPosition(RobotValues.STEP));
		controlMiddleLeft.whenPressed(new SetElevatorPosition(RobotValues.FLOOR));
		
		controlBottomRight.whenPressed(new OneTote());
		controlBottomMiddle.whenPressed(new DropAndLoadTote(true));
		
		primaryStickThree.whileHeld(new ActivateFlipper(true, true));
		primaryStickFour.whileHeld(new ActivateFlipper(true, false));
		primaryStickFive.whileHeld(new ActivateFlipper(false, true));
		
		primaryStickSix.whenPressed(new SlowSetPosition(RobotValues.CHUTE_STACK));
	}
}

