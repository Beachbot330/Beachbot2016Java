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
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger.Severity;

/**
 *
 */
public class RetryUpperClimber extends BBCommand {
	int debounce = 5;
	int debounce_count = 0;
	boolean deployed = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RetryUpperClimber() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.climber);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.climber.isUpperClimberDeployed()){
    		debounce_count = debounce + 1;
    		Robot.logger.println("Climber deploy confirmed!", Severity.INFO);
    	}
    	else{
    		Robot.logger.println("Climber deploy failed! Attempt Retry...", Severity.ERROR);
    		deployed = Robot.climber.upperClimberDeploy();
    		debounce_count = 0;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.climber.isUpperClimberDeployed()){
    		if(debounce_count > debounce){
    			return true;
    		}
    		else{
    			debounce_count++;
    			return false;
    		}
    	}
    	else{
    		debounce_count = 0;
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.lockUpperClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
