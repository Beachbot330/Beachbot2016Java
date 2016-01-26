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
public class TurnGyroWaypoint extends TurnGyroAbs {
    double x, y;
    
    public TurnGyroWaypoint(double x, double y, double tolerance, double timeout) {
    	this(x, y, tolerance, timeout, ChassisConst.GyroTurnLow, ChassisConst.GyroTurnHigh);
    }
    
    public TurnGyroWaypoint(double x, double y, double tolerance, double timeout, PIDGains low, PIDGains high)
    {
        super(0,tolerance,timeout,false,true, low, high);
        this.x=x;
        this.y=y;
        
    }

    protected void initialize() {
        calcAngle(x, y);
        super.initialize();
    }

    protected void calcAngle(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Robot.logger.println("Infinite calcAngle in TurnGyroWaypoint");
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Robot.logger.println("Infinite robotAngle in TurnWaypoint");
            robotAngle = 0;
        }
        
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle > calcAngle)
        {
            while (Math.abs(robotAngle-calcAngle)<180)
                calcAngle += 360;
        }
        else 
        {
            while (Math.abs(robotAngle-calcAngle)<180)
                calcAngle -= 360;
        }
       Robot.logger.println("angle: " + calcAngle);
        
        angle = calcAngle;
    }
}
