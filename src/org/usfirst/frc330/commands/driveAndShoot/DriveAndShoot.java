
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.Robot;


import org.usfirst.frc330.Robot;
import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

public class DriveAndShoot extends BBCommandGroup {

	BBCommandGroup driveAndShootCommand;
	
 public DriveAndShoot() {
 }

 // Called just before this Command runs the first time
 protected void initialize() {
 	
 	driveAndShootCommand = (BBCommandGroup)Robot.getStartPosition();
 	driveAndShootCommand.start();
 }

 protected void execute() {
 }

 protected boolean isFinished() {
 	return driveAndShootCommand.isCompleted();
 }

 protected void end() {
 }

 protected void interrupted() {
 }
}

