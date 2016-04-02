package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.AutoWait;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.breachDefenseCommands.LowBar;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

/**
 *
 */
public class LowBarShoot extends BBCommandGroup {
    
    public  LowBarShoot() {
    	addSequential(new AutoWait());
        addSequential(new LowBar());
        addSequential(new Wait(0.1));
        addSequential(new TurnGyroAbs(41, 5, 2, ChassisConst.GyroTurnLow));  // Was 40 deg.
        addParallel(new SetTurretPosition(0,3,3));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(144,5,41,2,false,ChassisConst.DriveLow,ChassisConst.GyroDriveLow)); // Was 40 deg.
        addSequential(new Aim(3.5, 15.0));
        addSequential(new Shoot());
    }
}
