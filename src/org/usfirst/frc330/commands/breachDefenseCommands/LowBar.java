
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;

/**
 *
 */
public class LowBar extends BBCommandGroup {
    
    public  LowBar() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
    	addSequential(new DriveDistance(40.0, 5.0, 3.0, true, ChassisConst.DriveLow) ); // distance, tol, timeout, stop
    }
}
