package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LowBarShoot_waypoint_high extends BBCommandGroup {
    
	double lowBarY = 125;
	double initialDriveY = 194;
	double batterX = 98;
	double batterY = 271;
	
    public  LowBarShoot_waypoint_high() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new DriveDistanceAtAbsAngle(lowBarY, 5.0, 0, 3.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow, ChassisConst.GyroTurnLow) ); 
    	addSequential(new ShiftHigh());
    	addSequential(new DriveWaypoint(initialDriveY, 0.0, 5.0, 5.0, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new PickupOff());
    	
        addSequential(new Wait(0.1));
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(batterX, batterY, 2, 10, ChassisConst.GyroTurnLow));  
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addParallel(new SetTurretPosition(0,3,3));
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(batterX,batterY,3,4,false,ChassisConst.DriveLow,ChassisConst.GyroDriveLow));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        
        addSequential(new Wait(0.5));
        addParallel(new DriveWaypointBackward(initialDriveY, 0, 5, 5, false, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Wait(0.5));
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
        addParallel(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    }
}
