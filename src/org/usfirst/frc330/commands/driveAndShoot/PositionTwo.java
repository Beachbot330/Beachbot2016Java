
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.commands.Aim;
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
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class PositionTwo extends BBCommandGroup {
    
	double positionOffset = 4*12;
	
	double pivotPointX = 44 + positionOffset;
	double pivotPointY = 210;
	
	double batterX = 44 + positionOffset;
	double batterY = 265;
			
	double startY = 160;
	
	PIDGains GyroTurnLow   = new PIDGains(0.02,0,0.004,0,0.4,0.1,"PosThreeGyroTurnLow");
	PIDGains GyroDriveHigh = new PIDGains(0.005,0,0.001,0,1,1, "GyroDriveHigh"); //AP 3-18
	PIDGains DriveHigh     = new PIDGains(0.050,0,0.13,0,ChassisConst.defaultMaxOutput,ChassisConst.defaultMaxOutputStep, "DriveHigh");
	
    public  PositionTwo() {
    	addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(pivotPointX, pivotPointY, 10, 3, GyroTurnLow));
        
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(pivotPointX, pivotPointY, 5, 5, false, DriveHigh, GyroDriveHigh));

        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(batterX, batterY, 5, 3, GyroTurnLow));
        
        addParallel(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(batterX, batterY, 5, 5, false, DriveHigh, GyroDriveHigh));
        
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
        
        addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));
        addParallel(new SetArmPosition(ArmConst.defaultNeutral, 3.0, 20.0));  //angle, tol, timeout
        addSequential(new DriveWaypointBackward(pivotPointX, pivotPointY, 10, 5, false, ChassisConst.GyroDriveLow, ChassisConst.GyroTurnLow));
        
        
        addSequential(new ShiftLow());
        addSequential(new TurnGyroWaypoint(0, startY, 5, 2, ChassisConst.GyroTurnLow));
        
        addSequential(new ShiftHigh());
        addSequential(new DriveWaypoint(0, startY, 10, 5, true, ChassisConst.GyroDriveHigh, ChassisConst.GyroTurnHigh));
    }
}
