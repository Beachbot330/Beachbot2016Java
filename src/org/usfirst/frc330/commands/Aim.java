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

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.TurretConst;

/**
 *
 */
public class Aim extends BBCommand {

	double setpoint;
    double prevSetpoint = 0;
    double tolerance;
    double timeout;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Aim() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	this(TurretConst.tolerance, TurretConst.timeout);
    }
    
    public Aim(double tolerance, double timeout) {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.turret);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	this.tolerance = tolerance;
    	this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pickup.openLid();
    	Robot.turret.setTurretAbsoluteTolerance(tolerance);
    	if (timeout >= 0.0) {
    		setTimeout(timeout);
    	} else {
    		setTimeout(9999999);
    	}
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setpoint = SmartDashboard.getNumber("targetAngle", prevSetpoint);
    	Robot.turret.setTurretAngle(Robot.turret.getTurretAngle()+setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isTimedOut())
    	{
    		Robot.logger.println("Aim setpoint: " + this.setpoint + "   Position at timeout: " + Robot.turret.getTurretAngle());
    	}
    	return ((Robot.turret.onTurretTarget() && setpoint != 0)|| isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
