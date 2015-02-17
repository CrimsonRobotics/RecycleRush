
package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.autonomous.ThreeTotesOnBin;
import org.usfirst.frc.team2526.robot.commands.LoadTote;
import org.usfirst.frc.team2526.robot.commands.StackTote;
import org.usfirst.frc.team2526.robot.commands.UnloadTote;
import org.usfirst.frc.team2526.robot.commands.vision.VisionCommunications;
import org.usfirst.frc.team2526.robot.subsystems.AlignmentArms;
import org.usfirst.frc.team2526.robot.subsystems.AlignmentWheels;
import org.usfirst.frc.team2526.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2526.robot.subsystems.Elevator;
import org.usfirst.frc.team2526.robot.subsystems.Flipper;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	
	public static DriveTrain driveTrain;
	public static AlignmentWheels alignmentWheels;
	public static AlignmentArms alignmentArms;
	public static Elevator elevator;
	public static Flipper flipper;
	public static OI oi;
	
	Compressor compressor;
	
	public static VisionCommunications vision;


    public void robotInit() {
    	vision = new VisionCommunications();
    	
		compressor = new Compressor(RobotMap.PCM_MAIN);
		compressor.start();
	
		driveTrain = new DriveTrain();
		alignmentWheels = new AlignmentWheels();
		alignmentArms = new AlignmentArms();
		elevator = new Elevator();
		flipper = new Flipper();
		
		oi = new OI();
		
		autonomousCommand = new ThreeTotesOnBin();
		
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(alignmentWheels);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(flipper);
		
		SmartDashboard.putData(new LoadTote());
		SmartDashboard.putData(new UnloadTote());
		SmartDashboard.putData(new StackTote());
		
		
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
//        while (true) {
//        	SmartDashboard.putNumber("Current Position", elevator.getPosition());
//        }
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Current Position", elevator.getPosition());
        Robot.elevator.updatePID();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
