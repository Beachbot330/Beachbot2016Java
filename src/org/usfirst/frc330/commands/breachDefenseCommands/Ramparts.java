
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.commands.PickupOff;
import org.usfirst.frc330.commands.PickupOn;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.SetXYOffset;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class Ramparts extends BBCommandGroup {
    
    public  Ramparts() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 1.0));			    //angle, tol, timeout
    	addSequential(new SetArmPosition(ArmConst.defaultNeutral, 3.0, 1.0));   				//angle, tol, timeout
    	addSequential(new DriveDistanceAtAbsAngle(164.0, 12.0, 0.0, 3.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow) ); // distance, tol, timeout, stop
    	addParallel(new PickupOn());
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new PickupOff());
    	addSequential(new SetXYOffset(-10,-12));
    }
}
