
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle;

/**
 *
 */
public class LowBar extends BBCommandGroup {
    
    public  LowBar() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addParallel(new PickupOn());
    	addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new DriveDistanceAtAbsAngle(167.0, 5.0, 0, 3.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow, ChassisConst.GyroTurnLow) ); 
    	// distance, tol, timeout, stop  -- Was 164 inches before angle change
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new PickupOff());
    }
}
