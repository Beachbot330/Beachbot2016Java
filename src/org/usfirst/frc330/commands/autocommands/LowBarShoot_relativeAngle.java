package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.PickupOff;
import org.usfirst.frc330.commands.PickupOn;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.breachDefenseCommands.LowBar;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtRelAngle_NoTurn;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.commands.drivecommands.TurnGyroRel;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShoot_relativeAngle extends BBCommandGroup {
    
    public  LowBarShoot_relativeAngle() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new DriveDistanceAtRelAngle_NoTurn(167.0, 5.0, 0, 3.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow) ); 
    	//double distance, double distanceTolerance, double angle, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	// Was 164 inches before angle change
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new PickupOff());
    	
        addSequential(new Wait(0.1));
        addSequential(new TurnGyroRel(41, 5, 2, false, ChassisConst.GyroTurnLow));  // Was 40 deg.
        //double angle, double tolerance, double timeout, boolean stopAtEnd, PIDGains gains
        addParallel(new SetTurretPosition(0,3,3));
        addSequential(new DriveDistanceAtRelAngle_NoTurn(144,5,0,2,false,ChassisConst.DriveLow,ChassisConst.GyroDriveLow)); // Was 40 deg.
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
    }
}
