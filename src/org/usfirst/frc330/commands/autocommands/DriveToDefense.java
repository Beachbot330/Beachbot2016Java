// RobotBuilder Version: 1.5BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.autocommands;


import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class DriveToDefense extends BBCommandGroup {
    
    public  DriveToDefense() {
    	
    	//Drive to the outer works to score points
    	addSequential(new ShiftLow());
    	addSequential(new DriveWaypoint(0.0, 73.0, 4.0, 3.0, true, ChassisConst.DriveLow,ChassisConst.GyroDriveLow));  //X Y Tol Timeout Stop
    }
}