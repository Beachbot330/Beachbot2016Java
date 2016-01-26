/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
public class DriveWaypointLegacy extends DriveWaypoint {

    public DriveWaypointLegacy(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
    	this(x,y,tolerance,timeout,stopAtEnd,ChassisConst.DriveLow,ChassisConst.DriveHigh,ChassisConst.GyroDriveLow,ChassisConst.GyroDriveHigh);
    }
    
    public DriveWaypointLegacy(double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(0,tolerance,0,timeout, stopAtEnd, driveLow, driveHigh, gyroLow, gyroHigh);
        this.x=x;
        this.y=y;
    }
    
    protected boolean isFinished() {
        if ((Robot.chassis.leftDrivePID.onTarget() || Robot.chassis.rightDrivePID.onTarget()) || isTimedOut())
        {
                return true;            
        }
        return false;
    }

}
