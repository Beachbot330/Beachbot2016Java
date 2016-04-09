
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.CheckDone;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.ShiftHigh;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.Wait;
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
public class PositionFour extends BBCommandGroup {
    
	double batterX = -14;
	double batterY = 252;
	
	double startY = 176;
	
    public  PositionFour() {
        addSequential(new TurnGyroWaypoint(batterX, batterY, 5, 3, ChassisConst.GyroTurnLow));
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new DriveWaypoint(batterX, batterY, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addParallel(new DriveTime(5.0,0.1,0.1));
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
        
        addSequential(new ShiftHigh());
        BBCommand driveCommand = new DriveWaypointBackward(batterX, startY, 10, 5, false, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh);
        addParallel(driveCommand);
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new Wait(0.5));
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
        addParallel(new SetArmPosition(ArmConst.defenseStance, 3.0, 20.0));  //angle, tol, timeout
        
        addSequential(new CheckDone(driveCommand));
        
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(0, 0, 5, 2, ChassisConst.GyroTurnLow));
    }
}
