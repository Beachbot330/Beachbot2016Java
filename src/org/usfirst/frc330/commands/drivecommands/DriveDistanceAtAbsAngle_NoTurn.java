// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.commands.drivecommands;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.wpilibj.PIDGains;
/**
 *
 */
public class  DriveDistanceAtAbsAngle_NoTurn extends DriveDistance{
    double angle;
    PIDGains gyroGains;
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double angle, PIDGains driveGains, PIDGains gyroGains)
    {
        this(distance, 6, angle, -1.0, true, driveGains, gyroGains); //-1 means no timeout
    }
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double angle, double tolerance, PIDGains driveGains, PIDGains gyroGains)
    {
        this(distance, tolerance, angle, -1.0, true, driveGains, gyroGains); //-1 means no timeout
    }
    
    public DriveDistanceAtAbsAngle_NoTurn(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains)
    {
        super(distance, tolerance, timeout, stopAtEnd, driveGains);
        this.angle = angle;
        this.gyroGains = gyroGains;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
//    	leftDistance = leftDistance + Robot.chassis.getLeftDistance();
//        rightDistance = rightDistance + Robot.chassis.getRightDistance();
        super.initialize();

        Robot.chassis.gyroPID.setPID(gyroGains);
        Robot.chassis.gyroPID.setSetpoint(angle);
        Robot.chassis.gyroPID.enable();  
    }
}
