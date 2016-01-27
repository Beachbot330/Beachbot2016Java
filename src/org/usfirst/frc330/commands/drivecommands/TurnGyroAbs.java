// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class  TurnGyroAbs extends BBCommand {
    double angle, tolerance, maxOutput, maxOutputStep, maxOutputMax;
    boolean stopAtEnd = false;
    boolean enable = true;
    PIDGains highGains, lowGains, gains;
    
    public TurnGyroAbs(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this(angle, 0, 15, false);
    }
    
    public TurnGyroAbs(double angle, double tolerance)
    {
        this(angle, tolerance, 15, false);
    
    }
    
    public TurnGyroAbs(double angle, double tolerance, double timeout)
    {
        this(angle, tolerance, timeout, false);
    
    }
    
    public TurnGyroAbs(double angle, double tolerance, double timeout, boolean stopAtEnd) {
        this(angle, tolerance, timeout, stopAtEnd, true);
    }
    
    public TurnGyroAbs(double angle, double tolerance, double timeout, boolean stopAtEnd, boolean enable) {
    	this(angle, tolerance, timeout, stopAtEnd, enable, ChassisConst.GyroTurnLow, ChassisConst.GyroTurnHigh);
    }
    
    public TurnGyroAbs(double angle, double tolerance, double timeout, boolean stopAtEnd, boolean enable, PIDGains low, PIDGains high) {
                // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.angle = angle;
        this.tolerance = tolerance;
        if (timeout >= 0.0)
        	setTimeout(timeout);
        this.stopAtEnd = stopAtEnd;
        this.enable = enable;
        lowGains = low;
        highGains = high;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.chassis.leftDrivePID.disable();
        Robot.chassis.rightDrivePID.disable();  
        
        if (!Robot.chassis.isHighGear())
        	gains = lowGains;
        else
        	gains = highGains;
        Robot.chassis.gyroPID.setPID(gains);
 //       Robot.chassis.gyroPID.setMaxOutput(gains.getMaxOutput());
        Robot.chassis.gyroPID.setAbsoluteTolerance(tolerance);
        Robot.chassis.gyroPID.setSetpoint(angle);
        maxOutputMax = gains.getMaxOutput();
        maxOutputStep = gains.getMaxOutputStep();
        Robot.logger.println("TurnGyroAbs Setpoint: " + angle);
        Robot.logger.println("Max output: " + gains.getMaxOutput());
        if (enable) 
            Robot.chassis.gyroPID.enable();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	maxOutput += maxOutputStep;
    	if (maxOutput >= maxOutputMax) 
    		maxOutput = maxOutputMax;
    	Robot.chassis.leftDrivePID.setMaxOutput(maxOutput);
        Robot.chassis.rightDrivePID.setMaxOutput(maxOutput);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.chassis.gyroPID.onTarget() || isTimedOut())
        {
            return true;
        }
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        if (stopAtEnd)
        {
            Robot.chassis.stopDrive();
        }
        Robot.logger.println("TurnGyroAbs Final Angle: " + Robot.chassis.getAngle() + "  Setpoint: " + angle, true);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        stopAtEnd = true;
        end();
    }
}