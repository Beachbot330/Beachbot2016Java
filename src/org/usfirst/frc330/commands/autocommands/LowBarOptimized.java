package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.breachDefenseCommands.LowBar;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.drivecommands.DriveWaypointBackward;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarOptimized extends BBCommandGroup {
    
    public  LowBarOptimized() {
    	addSequential(new ShiftHigh());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	//addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new SetArmPosition(5.0, 3.0, 20.0));  //Practice Field Only!
    	addSequential(new DriveDistanceAtAbsAngle(164.0, 5.0, 0, 3.0, false, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh) ); // distance, tol, timeout, stop
    	addSequential(new SetArmPosition(ArmConst.shootAngleRamp, 5, 1));
    	addSequential(new PickupOff());
    	
        addSequential(new Wait(0.1));
        addSequential(new TurnGyroAbs(40, 5, 2, ChassisConst.GyroTurnLow));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(144,5,40,2,false,ChassisConst.DriveHigh,ChassisConst.GyroDriveHigh));
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
        
        addParallel(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));
        addSequential(new DriveWaypointBackward(0, 164, 5.0, 5.0, true, ChassisConst.DriveHigh, ChassisConst.GyroDriveHigh));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    }
}