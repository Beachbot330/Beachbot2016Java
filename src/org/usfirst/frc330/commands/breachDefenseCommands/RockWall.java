
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class RockWall extends BBCommandGroup {
    
	double carefulDriving = 0.4;
	PIDGains driveCareful	   = new PIDGains(0.100,0,0.000,0,carefulDriving,carefulDriving, "DriveLow");
	PIDGains gyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1, "GyroDriveLow");
	
	double superCarefulDriving = 0.35;
	PIDGains creepForward	   = new PIDGains(0.080,0,0.000,0,superCarefulDriving,superCarefulDriving, "DriveLow");
	
	double centerOfWallY = 82;
	double finishPositionY = 176.0;
	
    public  RockWall() {
    	addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));
    	addSequential(new ShiftLow());
    	addParallel(new SetArmPosition(ArmConst.defaultNeutral, 5.0, 5.0));
    	addParallel(new PickupOn());
    	addSequential(new DriveWaypoint(0.0, centerOfWallY-36, 10.0, 15.0, false, ChassisConst.DriveLow, gyroDriveLow));
    	addSequential(new DriveWaypoint(0.0, centerOfWallY, 2.0, 15.0, false, driveCareful, gyroDriveLow));    	
    	addParallel(new SetArmPosition(ArmConst.defenseStance, 5.0, 20.0));
    	addSequential(new Wait(0.1));
    	
    	addSequential(new DriveWaypoint(0.0, centerOfWallY+36, 2.0, 15.0, false, driveCareful, gyroDriveLow));
    	
    	addParallel(new SetArmPosition(ArmConst.shootAngleFloor, 5.0, 20.0));    	
    	addParallel(new DriveWaypoint(0.0, finishPositionY, 10.0, 15.0, true, ChassisConst.DriveLow, gyroDriveLow));
    	addSequential(new PickupOff());
    }
}


//addSequential(new ShiftLow());
//addSequential(new SetTurretPosition(TurretConst.center, 3.0, 1.0));			    //angle, tol, timeout
//addSequential(new SetArmPosition(ArmConst.defaultNeutral, 3.0, 1.0));   				//angle, tol, timeout
//addSequential(new DriveDistance(164.0, 5.0, 3.0, false, ChassisConst.DriveLow) ); // distance, tol, timeout, stop
//addParallel(new PickupOn());
//addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
//addSequential(new PickupOff());
//addSequential(new SetXYOffset(0,-36));