// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands;	


import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class  SetArmPosition extends BBCommand {
	double angle;
	double timeout;
	double tolerance;
	int toleranceCounter;
	PIDGains gains;
    
	public SetArmPosition(double angle) {
		this(angle, ArmConst.tolerance, ArmConst.defaultTimeout);
	}
	
	public SetArmPosition(double angle, double tolerance){
		this(angle, tolerance, ArmConst.defaultTimeout);
	}
	
	public SetArmPosition(double angle, double tolerance, double timeout) {
		this(angle, tolerance, timeout, ArmConst.fullPID);
	}
	
	public SetArmPosition(double angle, double tolerance, double timeout, PIDGains gains) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        this.angle = angle;
        this.timeout = timeout;
        this.tolerance = tolerance;
        toleranceCounter = 0;
        this.gains = gains;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.setArmAngle(angle);
    	Robot.arm.setArmAbsoluteTolerance(tolerance);
    	toleranceCounter = 0;
    	if (timeout >= 0.0)
    		{setTimeout(timeout);}
    	else
    		{setTimeout(9999999);}
    	Robot.arm.enableArm();
    	Logger.getInstance().println("Arm set to: " + this.angle + " degrees, with tolerance: " + this.tolerance);
    	
    	Robot.arm.setPIDConstants(gains.getP(), gains.getI(), gains.getD());
    	Robot.arm.setMaxOutput(gains.getMaxOutput());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	toleranceCounter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (toleranceCounter < 5)
    		return false;

    	if (isTimedOut())
    	{
    		Logger.getInstance().println("SetArmPosition setpoint: " + this.angle + "   Position at timeout: " + Robot.arm.getArmAngle(), Severity.WARNING);
    	}
    	return (Robot.arm.onArmTarget() || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setPIDConstants(ArmConst.fullPID.getP(), ArmConst.fullPID.getI(), ArmConst.fullPID.getD());
    	Robot.arm.setMaxOutput(ArmConst.fullPID.getMaxOutput());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.setPIDConstants(ArmConst.fullPID.getP(), ArmConst.fullPID.getI(), ArmConst.fullPID.getD());
    	Robot.arm.setMaxOutput(ArmConst.fullPID.getMaxOutput());
    }
}
