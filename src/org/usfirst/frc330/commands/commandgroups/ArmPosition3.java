// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.commands.commandgroups;

import edu.wpi.first.wpilibj.command.BBCommandGroup;

import org.usfirst.frc330.commands.LidOpen;
import org.usfirst.frc330.commands.PickupOn;
import org.usfirst.frc330.commands.SetArmPosition;
import org.usfirst.frc330.commands.SetTurretPosition;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.TurretConst;

/**
 *
 */
public class ArmPosition3 extends BBCommandGroup {

    public ArmPosition3() {
    	addParallel(new PickupOn(0.5));
    	addParallel(new SetTurretPosition(TurretConst.aimLeft));
    	addSequential(new SetArmPosition(ArmConst.shootAngleFloor));
    	addSequential(new LidOpen());
    } 
}
