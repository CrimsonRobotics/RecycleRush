package org.usfirst.frc.team2526.robot.commands.alignment;

import org.usfirst.frc.team2526.robot.Robot;
import org.usfirst.frc.team2526.robot.commands.SimpleCommand;

/**
 *
 */
public class CloseArm extends SimpleCommand {

    public CloseArm() {
        super(Robot.alignmentArms);
    }
    
    public void initialize() {
    	Robot.alignmentArms.closeArms();
    }
    
    public boolean isFinished() {
    	return true;
    }
}