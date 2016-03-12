// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.commands;	

import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.command.BBCommand;
import org.usfirst.frc330.commands.*;

public class ArmDeployWait extends BBCommand {

	BBCommand commandOne, commandTwo;
	double time;
	
    public ArmDeployWait(double delayTimeInSeconds) {
    	time = delayTimeInSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	commandOne = new Wait(time);
    	commandTwo = new Wait(0.1);
    	if(Robot.climber.isLowerClimberDeployed()){
    		Robot.logger.println("Lower climber deployed, starting 2s timer");
    		commandOne.start();
    	}
    	else{
    		Robot.logger.println("Starting 0.1s timer");
    		commandTwo.start();
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return commandOne.isCompleted() || commandTwo.isCompleted();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
