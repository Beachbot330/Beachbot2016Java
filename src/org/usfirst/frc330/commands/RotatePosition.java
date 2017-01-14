package org.usfirst.frc330.commands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class RotatePosition extends BBCommand {

	double x,y,gyro;
    public RotatePosition() {
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	x = -2*Robot.chassis.getX();
        y = -2*Robot.chassis.getY();
        
        Logger.getInstance().println("Old coordinates: " + Robot.chassis.getX() + ", " + Robot.chassis.getY() +
        					 "   At an angle of: " + Robot.chassis.getAngle(), Severity.DEBUG);
        Logger.getInstance().println("Old gyro comp: " + Robot.chassis.getGyroComp(), Severity.DEBUG);
        
        if (Robot.chassis.getAngle() < 0)
        	Robot.chassis.setGyroComp(Robot.chassis.getGyroComp()+180);
        else
        	Robot.chassis.setGyroComp(Robot.chassis.getGyroComp()-180);
        
    	Robot.chassis.setXYoffset(x, y);
    	
    	Logger.getInstance().println("New coordinates: " + Robot.chassis.getX() + ", " + Robot.chassis.getY() +
				 			 "   At an angle of: " + Robot.chassis.getAngle(), Severity.DEBUG);
    	Logger.getInstance().println("New gyro comp: " + Robot.chassis.getGyroComp(), Severity.DEBUG);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
