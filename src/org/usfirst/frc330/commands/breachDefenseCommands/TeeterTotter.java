
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.constants.*;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class TeeterTotter extends BBCommandGroup {
    
	
	double carefulDriving = 0.6;
	PIDGains driveLow	   = new PIDGains(0.100,0,0.000,0,carefulDriving,carefulDriving, "DriveLow");
	PIDGains gyroDriveLow  = new PIDGains(0.010,0,0.000,0,1,1, "GyroDriveLow");
	
	double superCarefulDriving = 0.35;
	PIDGains creepForward	   = new PIDGains(0.080,0,0.000,0,superCarefulDriving,superCarefulDriving, "DriveLow");
	
	double distanceToTeeterY = 46.5;
	double finishPositionY = 176.0;
	
	
    public  TeeterTotter() {
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
    	
    	addParallel(new SetTurretPosition(TurretConst.center, 3.0, 20.0));
    	addSequential(new ShiftLow());
    	addParallel(new PickupOn());
    	addSequential(new DriveWaypoint(0.0, distanceToTeeterY, 2.0, 15.0, false, driveLow, gyroDriveLow));
    	addSequential(new SetArmPosition(ArmConst.lowBar, 5.0, 20.0));
    	
    	addSequential(new DriveWaypoint(0.0, distanceToTeeterY+13, 2.0, 15.0, false, driveLow, gyroDriveLow));
    	
    	addParallel(new SetArmPosition(ArmConst.defaultNeutral, 5.0, 20.0));
    	addSequential(new DriveWaypoint(0.0, distanceToTeeterY+13+29.5, 2.0, 15.0, false, creepForward, gyroDriveLow));
    	addSequential(new Wait(0.7)); //time to tip
    	
    	addParallel(new SetArmPosition(ArmConst.shootAngleFloor, 5, 1));
    	addSequential(new DriveWaypoint(0.0, distanceToTeeterY+13+29.5+5, 4.0, 15.0, false, driveLow, gyroDriveLow));
    	addSequential(new DriveWaypoint(0.0, finishPositionY, 4.0, 15.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
    }
}
