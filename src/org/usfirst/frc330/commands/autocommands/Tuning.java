package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.AimSmart.Goal;
import org.usfirst.frc330.commands.breachDefenseCommands.LowBar;
import org.usfirst.frc330.commands.commandgroups.Shoot;
import org.usfirst.frc330.commands.drivecommands.*;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.wpilibj.PIDGains;

import edu.wpi.first.wpilibj.command.BBCommandGroup;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Tuning extends BBCommandGroup {
    
	double maxArmSpeed = 1.0;
	PIDGains slowArm = new PIDGains(ArmConst.proportional, ArmConst.integral, ArmConst.derivative, ArmConst.feedForward,
									maxArmSpeed, maxArmSpeed, "slowArm");
	//double p, double i, double d, double f, double maxOutput, double maxOutputStep, String name
	
    public  Tuning() {
    	addSequential(new AimSmart(4.5, 120, Goal.center));
    	addSequential(new Shoot());
    }
}
