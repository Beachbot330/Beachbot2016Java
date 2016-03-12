
package org.usfirst.frc330.commands.autocommands;
import org.usfirst.frc330.Robot;
import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.LidOpen;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class SpyboxShoot extends BBCommandGroup {
    
	// Add Commands here:
    // e.g. addSequential(new Command1());
    //      addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    //      addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
	
    public  SpyboxShoot() {
    	
    	// Starting position: Spybox
    	//
    	// Sequence:
    	//	Shift Low
    	//  Set Turret to center
    	//  Set arm to 90 deg
    	//  Drive forward to stronghold tower
    	//  Auto aim to shooting goal
    	//  Shoot
    	
    	addSequential(new ShiftLow());
    	addSequential(new SetTurretPosition(-38.0, 3.0, 5.0));		                    //angle, tol, timeout
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor, 3.0, 5.0)); 		    //angle, tol, timeout
    	addSequential(new LidOpen());
    	addSequential(new DriveDistance(94.0, 2.0, 10.0, true, ChassisConst.DriveLow) ); // distance, tol, timeout, stop
    	
//    	addSequential(new Wait(2.0));
    	addSequential(new Aim(3.5, 10.0));
    	
//    	addSequential(new Wait(2.0));
    	addSequential(new Shoot());
    }
}
