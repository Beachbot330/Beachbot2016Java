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
public class DriveWaypoint extends DriveDistanceAtAbsAngle_NoTurn {
    double x,y;

    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
    	this(x,y,tolerance,timeout,stopAtEnd,ChassisConst.DriveLow,ChassisConst.DriveHigh,ChassisConst.GyroDriveLow,ChassisConst.GyroDriveHigh);
    }
    
    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveLow, PIDGains driveHigh, PIDGains gyroLow, PIDGains gyroHigh) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(0,tolerance,0,timeout, stopAtEnd, driveLow, driveHigh, gyroLow, gyroHigh);
        this.x=x;
        this.y=y;
    }

    protected void initialize() {
        calcXY(x,y);
        super.initialize();
    }
    
    protected void calcXY(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, calcDistance, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Robot.logger.println("Infinite calcAngle in DriveWaypoint");
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Robot.logger.println("Infinite robotAngle in DriveWaypoint");
            robotAngle = 0;
        }
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle-calcAngle > 180)
        {
            while (robotAngle-calcAngle > 180)
                calcAngle += 360;
        }
        else 
        {
            while (robotAngle-calcAngle < -180)
                calcAngle -= 360;
        }
        Robot.logger.println("DriveWaypoint x: " + x + " y: " + y + " curX: " + curX + " curY: " + curY + " curAngle: " + robotAngle);
        Robot.logger.println("DriveWaypoint distance: " + calcDistance + " angle: " + calcAngle);
        
        leftDistance = calcDistance;
        rightDistance = calcDistance;
        angle = calcAngle;
        
    }
    protected void end() {
    	Robot.logger.println("DriveWaypoint Final Location   X: " + Robot.chassis.getX() + "  Y: " + Robot.chassis.getY());
    	Robot.logger.println("DriveWaypoint Final DriveTrain Distances   Left: " + Robot.chassis.getLeftDistance() + "  Right: " + Robot.chassis.getRightDistance());
        super.end();
    }
    
    protected boolean isFinished() {
        if ((Robot.chassis.leftDrivePID.onTarget() && Robot.chassis.rightDrivePID.onTarget()) || isTimedOut())
        {
                return true;            
        }
        return false;
    }

}
