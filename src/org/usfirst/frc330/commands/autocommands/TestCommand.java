package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.ShiftLow;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveTime;
import org.usfirst.frc330.constants.ChassisConst;

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
    	addSequential(new ShiftLow());
    	addSequential(new DriveDistance(12, ChassisConst.DriveLow));
    }
}
