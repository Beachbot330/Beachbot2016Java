
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.AimSmart;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class PositionOne extends BBCommandGroup {
    
    public  PositionOne() {
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
    	
    	// Action: Shooting after Low Bar crossing
    	// 
    	// Sequence:
    	//   Turn clockwise 90 degrees
    	//   Drive 10 feet (need to get to position 3)
    	//   Turn counterclockwise 90 degrees
    	//   Drive forward 5 feet (towards tower)
    	//   Arm goes up (can be in parallel)
    	//   Turret position in neutral
    	//   Auto aim
    	//   Shoot
    	
    	addSequential(new TurnGyroAbs(90, 3.0, 10.0, ChassisConst.GyroDriveLow)); 		 // angle, tolerance, timeout, PID gain
    	addSequential(new DriveDistance(100.0, 5.0, 3.0, true, ChassisConst.DriveLow) ); // distance, tol, timeout, stop
    	addSequential(new TurnGyroAbs(0, 3.0, 10.0, ChassisConst.GyroDriveLow)); 		 // angle, tolerance, timeout, PID gain
    	addSequential(new DriveDistance(60.0, 5.0, 3.0, true, ChassisConst.DriveLow) );  // distance, tol, timeout, stop
    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));		     //angle, tol, timeout
    	addSequential(new SetArmPosition(ArmConst.shootAngleAuto, 3.0, 20.0)); 		 //angle, tol, timeout
    	//addSequential(new Aim());
    	addSequential(new AimSmart());
    	addSequential(new Shoot());   	
    }
}
