package org.usfirst.frc330.commands.driveAndShoot;

import org.usfirst.frc330.commands.Aim;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.commands.Wait;
import org.usfirst.frc330.commands.breachDefenseCommands.LowBar;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.DriveDistance;
import org.usfirst.frc330.commands.drivecommands.DriveDistanceAtAbsAngle_NoTurn;
import org.usfirst.frc330.commands.drivecommands.TurnGyroAbs;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShoot extends BBCommandGroup {
    
    public  LowBarShoot() {
        addSequential(new LowBar());
        addSequential(new Wait(0.1));
        addSequential(new TurnGyroAbs(40, 5, 2, ChassisConst.GyroTurnLow));
        addParallel(new SetTurretPosition(0,3,3));
        addSequential(new DriveDistanceAtAbsAngle_NoTurn(144,5,40,2,false,ChassisConst.DriveLow,ChassisConst.GyroDriveLow));
        addSequential(new Aim(2.0, 3.0));
        addSequential(new Shoot());
    }
}
