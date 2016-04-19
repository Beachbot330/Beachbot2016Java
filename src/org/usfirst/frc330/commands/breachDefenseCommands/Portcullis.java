
package org.usfirst.frc330.commands.breachDefenseCommands;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.*;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class Portcullis extends BBCommandGroup {
    
	double distanceToPort = -58.0;
	double destination = -170;
	
	double superCarefulDriving = 0.15;
	PIDGains creepForward	   = new PIDGains(0.080,0,0.000,0,superCarefulDriving,superCarefulDriving, "SuperCareful");
	
	double CarefulDriving = 0.5;
	PIDGains carefulDrive	   = new PIDGains(0.10,0,0.000,0,CarefulDriving,CarefulDriving, "Careful");
	
	double maxArmVoltage = 8;
	PIDGains slowArm = new PIDGains(ArmConst.proportional, ArmConst.integral, ArmConst.derivative, ArmConst.feedForward,
									maxArmVoltage, maxArmVoltage, "slowArm");
	
    public  Portcullis() {
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
    	
    	addParallel(new DriveWaypointBackward(0, distanceToPort, 1.0, 4.0, false, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
    	//double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
    	addSequential(new SetArmPosition(ArmConst.defaultNeutral, 5.0, 20.0));
    	addSequential(new DeployPortcullisStinger());
    	
    	addSequential(new Wait(0.6));
    	
    	addParallel(new DriveWaypointBackward(0, distanceToPort-4.0, 1.0, 4.0, false, creepForward, ChassisConst.GyroDriveLow));
    	addSequential(new Wait(0.1));
    	addSequential(new SetArmPosition(ArmConst.pickupAngle, 10.0, 20.0, slowArm));
    	
    	addSequential(new DriveWaypointBackward(0, destination+24, 1.0, 6.0, false, carefulDrive, ChassisConst.GyroDriveLow));
    	
    	//addSequential(new Wait(1.0));
    	
    	addSequential(new RetractPortcullisStinger());
    	addParallel(new SetArmPosition(ArmConst.defaultNeutral, 5.0, 20.0));
    	addSequential(new TurnGyroWaypoint(0, destination, 5.0, 6.0, ChassisConst.GyroTurnLow));
    	addSequential(new DriveWaypoint(0, destination, 5.0, 6.0, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
    	addSequential(new RotatePosition());
    	
    	
    }
}
