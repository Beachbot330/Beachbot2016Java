/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands.drivecommands;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;
 
/**
 * Turn in place towards a waypoint using the gyro.
 */
public class TurnGyroWaypointBackward extends TurnGyroWaypoint {
    
    public TurnGyroWaypointBackward(double x, double y, double tolerance, double timeout) {
    	this(x, y, tolerance, timeout, ChassisConst.GyroTurnLow, ChassisConst.GyroTurnHigh);
    }
    
    public TurnGyroWaypointBackward(double x, double y, double tolerance, double timeout, PIDGains low, PIDGains high)
    {
        super(x,y,tolerance,timeout,low, high);       
    }


    protected void calcAngle(double x, double y) {
        double gyroAngle;
        
        super.calcAngle(x, y);
        
        gyroAngle = Robot.chassis.getAngle();
        if (gyroAngle < angle)
            angle = angle-180;
        else
            angle = angle+180;
        Robot.logger.println("Backward Angle: " + angle);
    }
}
