// This is a good copy and paste command for writing additional conditional command wrappers

package org.usfirst.frc330.commands.breachDefenseCommands;	

import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.command.BBCommand;

public class BreachDefenseSelector extends BBCommand {

	BBCommand defenseCommand;
	
    public BreachDefenseSelector() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	defenseCommand = (BBCommand)Robot.getObstacle();
    	defenseCommand.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return defenseCommand.isCompleted();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
