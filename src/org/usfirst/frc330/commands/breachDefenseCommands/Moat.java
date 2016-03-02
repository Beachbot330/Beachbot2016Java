
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class Moat extends BBCommandGroup {
    
    public  Moat() {
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));			    //angle, tol, timeout
    	addSequential(new SetArmPosition(ArmConst.neutral, 3.0, 20.0));   				//angle, tol, timeout
    	addSequential(new DriveDistance(80.0, 5.0, 3.0, true, ChassisConst.DriveLow) ); // distance, tol, timeout, stop
    }
}
