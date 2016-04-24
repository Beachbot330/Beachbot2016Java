package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LowBarShoot_waypoint_high extends BBCommandGroup {
    
	double distanceToLowbar = 35.0;
	double lowBarY = 125;
	double initialDriveY = 194;
	double batterX = 98;
	double batterY = 270;
	
	BBCommand armCommand;
	
	public static final PIDGains GyroDriveHigh = new PIDGains(0.005,0,0.001,0,1,1, "GyroDriveHigh"); //AP 3-18
	public static final PIDGains DriveHigh     = new PIDGains(0.050,0,0.10,0,ChassisConst.defaultMaxOutput,ChassisConst.defaultMaxOutputStep, "DriveHigh");
	
    public  LowBarShoot_waypoint_high() {
    	addSequential(new ShiftLow());
    	addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	armCommand = new SetArmPosition(ArmConst.lowBar, 5.0, 20.0); //angle, tol, timeout
    	addParallel(armCommand);
    	addSequential(new Wait(1.0));
    	addParallel(new DriveWaypoint(0.0, distanceToLowbar, 3.0, 5.0, false, DriveHigh, GyroDriveHigh));
    	
    	addSequential(new CheckDone(armCommand));
    	
    	addSequential(new DriveWaypoint(0.0, lowBarY, 5.0, 3.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow) ); 
    	addSequential(new ShiftHigh());
    	addParallel(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new DriveWaypoint(3.0, initialDriveY, 5.0, 5.0, false, DriveHigh, GyroDriveHigh));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	
        addSequential(new Wait(0.1));
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(batterX, batterY, 5, 2, ChassisConst.GyroTurnLow));  
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new PickupOff());
        addParallel(new SetTurretPosition(0,3,3));
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(batterX,batterY,3,4,false,DriveHigh,GyroDriveHigh));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Wait(0.7));
        //addSequential(new Aim(4.5, 15.0)); //was 3.5 tolerance
        addSequential(new Aim(4.5, 15.0));
        addSequential(new Shoot());
        
        addSequential(new WaitUntilTeleopConditional());
        
        addSequential(new Wait(0.1));
        
        BBCommand driveCommand = new DriveWaypointBackward(0, initialDriveY, 5, 5, false, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh);
        addParallel(driveCommand);
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Wait(0.5));
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
        addParallel(new SetArmPosition(ArmConst.defaultNeutral, 3.0, 20.0));  //angle, tol, timeout
        
        addSequential(new CheckDone(driveCommand));
        addSequential(new TurnGyroWaypoint(0, 0, 5, 2, ChassisConst.GyroTurnLow));
    }
}
