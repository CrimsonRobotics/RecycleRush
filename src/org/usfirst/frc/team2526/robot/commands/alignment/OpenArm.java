package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class OpenArm extends SimpleCommand {

    public OpenArm() {
        super(Robot.alignmentArms);
    }
    
    public void initialize() {
    	Robot.alignmentArms.openArms();
    	
    }

    public boolean isFinished() {
    	return true;
    }
}