
package org.usfirst.frc330.commands.driveAndShoot;
import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveTime;
import org.usfirst.frc330.commands.drivecommands.DriveWaypoint;
import org.usfirst.frc330.commands.drivecommands.TurnGyroWaypoint;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class PositionFive extends BBCommandGroup {
    
    public  PositionFive() {
    	addSequential(new DriveWaypoint(0, 288-37, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addSequential(new TurnGyroWaypoint(-37, 288, 5, 3, ChassisConst.GyroTurnLow));
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new DriveWaypoint(-37, 288, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addParallel(new DriveTime(5.0,0.1,0.1));
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
    }
}
