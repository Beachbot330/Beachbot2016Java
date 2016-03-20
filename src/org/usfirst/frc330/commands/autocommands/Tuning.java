package org.usfirst.frc330.commands.autocommands;

import org.usfirst.frc330.commands.*;
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
    
    public  Tuning() {
    	addSequential(new ShiftHigh());
        
    	PIDGains tuneGyroHigh  = new PIDGains(0.025,0,0.002,0,1,1, "TuneGyroHigh");
        addSequential(new TurnGyroRel(45, 1, 5, true, tuneGyroHigh));
        //double angle, double tolerance, double timeout, boolean stopAtEnd, PIDGains gains
    }
}
