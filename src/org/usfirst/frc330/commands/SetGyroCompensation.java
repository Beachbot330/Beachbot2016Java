package org.usfirst.frc330.commands;

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.BBCommand;

/**
 *
 */
public class SetGyroCompensation extends BBCommand {

	double gyroCompensation;
    public SetGyroCompensation(double gyroCompensation) {
        requires(Robot.chassis);
        this.gyroCompensation = gyroCompensation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.setGyroComp(gyroCompensation);
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
