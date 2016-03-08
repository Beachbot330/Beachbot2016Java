package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.ShiftHigh;
import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveTime;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.commands.drivecommands.TurnGyroRel;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestCommand extends CommandGroup {
    
    public  TestCommand() {
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
//    	addSequential(new ShiftLow());
//    	addSequential(new DriveDistance(60, ChassisConst.DriveHigh));
//    	addSequential(new TurnGyroRel(30,ChassisConst.GyroTurnLow));
    	addSequential(new ShiftLow());
//    	addSequential(new SetTurretPosition(TurretConst.center, 3.0, 20.0));  //angle, tol, timeout
//    	addSequential(new SetArmPosition(ArmConst.lowBar, 3.0, 20.0));  //angle, tol, timeout
//    	addSequential(new DriveWaypoint(0.0, 48.0, 4.0, 3.0, true, ChassisConst.DriveLow,ChassisConst.GyroDriveLow));  //X Y Tol Timeout Stop
    	addSequential(new TurnGyroRel(45,1,5,false,ChassisConst.GyroTurnLow));
    }
}
