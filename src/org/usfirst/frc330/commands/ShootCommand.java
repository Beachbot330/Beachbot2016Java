// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class ShootCommand extends BBCommand {

    static int shotNumber=0;
    String name = "default";

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ShootCommand() {
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    public ShootCommand(String name) {
    	this.name = name;
    }
    
    double shootOffTimer;

    // Called just before this Command runs the first time
    protected void initialize() {
    	Severity severity = Severity.INFO;
    	
    	if (Robot.chassis.getPressure() < 60.0)
    		severity = Severity.ERROR;
    	shotNumber++;
        shootOffTimer = Timer.getFPGATimestamp() + 0.5;
        Robot.logger.println("Shot Number: " + shotNumber + " " + name + "   Air Pressure: " +
        					 Robot.chassis.getPressure(), severity);
        SmartDashboard.putString("savePictureName", "Shot_" + shotNumber + "_begin");
        SmartDashboard.putBoolean("savePicture", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Timer.getFPGATimestamp() > shootOffTimer);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickup.shootSolenoidsOff();
    	SmartDashboard.putString("savePictureName", "Shot_ " + shotNumber + "_end");
    	SmartDashboard.putBoolean("savePicture", true); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pickup.shootSolenoidsOff();
    	Robot.logger.println("Shot Number: " + shotNumber + " was interrupted", Severity.WARNING);
    }
}
