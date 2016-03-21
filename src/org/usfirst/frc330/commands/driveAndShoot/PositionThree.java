
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
public class PositionThree extends BBCommandGroup {
    
    public  PositionThree() {
        addSequential(new TurnGyroWaypoint(38, 38+152, 5, 3, ChassisConst.GyroTurnLow));
        //double x, double y, double tolerance, double timeout, PIDGains gains
        addSequential(new DriveWaypoint(38, 38+152, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        //double x, double y, double tolerance, double timeout, boolean stopAtEnd, PIDGains driveGains, PIDGains gyroGains
        addSequential(new TurnGyroWaypoint(38, 38+152, 5, 3, ChassisConst.GyroTurnLow));
        addSequential(new DriveWaypoint(38, 252, 5, 5, true, ChassisConst.DriveLow, ChassisConst.GyroDriveLow));
        addParallel(new DriveTime(5.0,0.1,0.1));
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
        addSequential(new Wait(0.2));
    }
}
