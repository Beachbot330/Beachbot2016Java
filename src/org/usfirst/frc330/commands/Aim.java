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
import org.usfirst.frc330.constants.TurretConstPract;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class Aim extends BBCommand {

	double setpoint;
    double prevSetpoint = 0;
    double tolerance;
    double timeout;
    int toleranceCount;
    static int aimNumber = 0;

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
    	toleranceCount = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	aimNumber++;
    	Robot.pickup.openLid();
    	Robot.turret.setTurretAbsoluteTolerance(tolerance);
    	toleranceCount = 0;
    	if (timeout >= 0.0) {
    		setTimeout(timeout);
    	} else {
    		setTimeout(9999999);
    	}
        SmartDashboard.putString("savePictureName", "Aim_" + aimNumber + "_begin");
        SmartDashboard.putBoolean("savePicture", true);
        
        //Slow PID for aiming
        if (!Robot.frills.isPracticeRobot()){
        	Robot.turret.setPIDConstants(TurretConst.aimProportional, TurretConst.aimIntegral, TurretConst.aimDerivative);
            Robot.turret.setVoltageRampRate(TurretConst.aimVoltageRampRate);
        	Robot.turret.configMaxOutputVoltage(TurretConst.aimMaxOutputVoltage);
        }
        else{
        	Robot.turret.setPIDConstants(TurretConstPract.aimProportional, TurretConstPract.aimIntegral, TurretConstPract.aimDerivative);
            Robot.turret.setVoltageRampRate(TurretConstPract.aimVoltageRampRate);
        	Robot.turret.configMaxOutputVoltage(TurretConstPract.aimMaxOutputVoltage);
        }
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setpoint = SmartDashboard.getNumber("targetAngle", prevSetpoint);
    	Robot.turret.setTurretAngle(Robot.turret.getTurretAngle()+setpoint);
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.turret.onTurretTarget() && setpoint != 0)
    		toleranceCount++;
    	else
    		toleranceCount = 0;
    	
    	if (isTimedOut())
    	{
    		Logger.getInstance().println("Aim Timed Out. Aim setpoint: " + this.setpoint + "   Position at timeout: " + Robot.turret.getTurretAngle(),Severity.WARNING);
    	}
    	return (toleranceCount >= 25 || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("savePictureName", "Aim_ " + aimNumber + "_end");
    	SmartDashboard.putBoolean("savePicture", true); 
    	
    	//Return to fast PID
    	Robot.turret.setPIDConstants(TurretConst.proportional, TurretConst.integral, TurretConst.derivative);
        Robot.turret.setVoltageRampRate(TurretConst.VoltageRampRate);
    	Robot.turret.configMaxOutputVoltage(TurretConst.MaxOutputVoltage);
    	
    	Robot.turret.setTurret(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("savePictureName", "Aim_ " + aimNumber + "_interrupted");
    	SmartDashboard.putBoolean("savePicture", true); 
    	
    	//Return to fast PID
    	Robot.turret.setPIDConstants(TurretConst.proportional, TurretConst.integral, TurretConst.derivative);
        Robot.turret.setVoltageRampRate(TurretConst.VoltageRampRate);
    	Robot.turret.configMaxOutputVoltage(TurretConst.MaxOutputVoltage);
    	
    	Robot.turret.setTurret(0.0);
    }
}
