
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveTime;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.TurnGyroWaypoint;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommand;
import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class PositionFive extends BBCommandGroup {
    
	double pivotPointX = 0;
	double startY = 160;
	double pivotPointY = 288-35;
	
	double batterX = -35;
	double batterY = 288;
	
    public  PositionFive() {
    	addSequential(new ShiftLow());
    	addParallel(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new DriveWaypoint(pivotPointX, pivotPointY, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(batterX, batterY, 8, 2, ChassisConst.GyroTurnLow));
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new DriveWaypoint(batterX, batterY, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addParallel(new DriveTime(5.0,0.1,0.1));
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
        
        addSequential(new WaitUntilTeleopConditional());
        
        addSequential(new ShiftHigh());
        BBCommand driveCommand = new DriveWaypointBackward(pivotPointX, pivotPointY, 10, 5, false, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh);
        addParallel(driveCommand);
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Wait(0.5));
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
        addParallel(new SetArmPosition(ArmConst.defaultNeutral, 3.0, 20.0));  //angle, tol, timeout
        
        addSequential(new CheckDone(driveCommand));
        
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(0, startY, 5, 2, ChassisConst.GyroTurnLow));
        
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(0, startY, 10, 5, true, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh));
    }
}
