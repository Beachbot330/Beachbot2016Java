package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LowBarOptimized extends BBCommandGroup {
    
    public  LowBarOptimized() {
    	addSequential(new ShiftHigh());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	//addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new AutoWait());
    	addSequential(new SetArmPosition(5.0, 3.0, 20.0));  //Practice Field Only!
    	addSequential(new DriveDistanceAtAbsAngle(164.0, 5.0, 0, 3.0, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh) ); // distance, tol, timeout, stop
    	addSequential(new SetArmPosition(ArmConst.shootAngleRamp, 5, 1));
    	addSequential(new PickupOff());
    	
        addSequential(new Wait(0.1));
        //addSequential(new TurnGyroAbs(40, 5, 2, ChassisConst.GyroTurnLow));
        //addSequential(new DriveDistanceAtAbsAngle_NoTurn(144,5,40,2,false,ChassisConst.DriveHigh,ChassisConst.GyroDriveHigh));
        
        addSequential(new TurnGyroWaypoint(114, 276, 5, 3, ChassisConst.GyroTurnHigh));
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new DriveWaypoint(114, 276, 5, 5, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addParallel(new DriveTime(5.0,0.1,0.1));
        //92,274 (110)
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout)
        addParallel(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));
        addSequential(new DriveWaypointBackward(35, 165, 5.0, 5.0, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new TurnGyroAbs(180,5,5,false,ChassisConst.GyroTurnHigh));
    }
}
