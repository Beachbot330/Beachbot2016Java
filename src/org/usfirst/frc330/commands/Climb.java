// RobotBuilder Version: 2.0.1BB
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
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;

/**
 *
 */
public class Climb extends BBCommand {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Climb() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climber);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);
    }

    
    double leftStartDistance = 0;
    double rightStartDistance = 0;
    static boolean initalized = false;
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.oi.getDriverR().getRawButton(3)) {
    		init();
    	}
    	
    }
    
    private void init() {
		Robot.climber.upperClimberDeployUnsafe();
		Robot.chassis.shiftLow();
		Robot.climber.engagePTO();
		leftStartDistance = Robot.chassis.getLeftDistance();
		rightStartDistance = Robot.chassis.getRightDistance();
		initalized = true;
    }

    double leftDriven = 0;
    double rightDriven = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!initalized)
    		init();
    	
    	leftDriven = Robot.chassis.getLeftDistance() - leftStartDistance;
    	rightDriven = Robot.chassis.getRightDistance() - rightStartDistance;
    	if (initalized && Robot.oi.getDriverR().getRawButton(3) && (leftDriven > -ChassisConst.climberMaxDistance || rightDriven > -ChassisConst.climberMaxDistance) )
    		Robot.chassis.tankDrive(-ChassisConst.climberSpeed, -ChassisConst.climberSpeed);
    	else 
    		Robot.chassis.tankDrive(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
