// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.subsystems;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;
import org.usfirst.frc330.util.Logger.Severity;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Climber extends Subsystem
{

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DoubleSolenoid powerTakeOff = RobotMap.climberPowerTakeOff;
    private final Solenoid pivotHigh = RobotMap.climberPivotHigh;
    private final Solenoid pivotLow = RobotMap.climberPivotLow;
    private final DigitalInput lowerLimit = RobotMap.climberLowerLimit;
    private final DigitalInput upperLimit = RobotMap.climberUpperLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Climber() {
		/////////////////////////////////////////////////////////////////
		// LOG IT!
    	// TODO: Add additional logging as needed
		
		CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return isLowerClimberDeployed()?1:0; }
		};
		CSVLogger.getInstance().add("LowerClimberDeployed", temp);

		temp = new CSVLoggable(true) {
			public double get() { return isUpperClimberDeployed()?1:0; }
		};
		CSVLogger.getInstance().add("UpperClimberDeployed", temp);
    }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void lowerClimberDeploy()
    {
    	if (Robot.arm.isSafeToDeployLowerClimber())
    	{
    		pivotLow.set(true);
    		Logger.getInstance().println("Deploying (Unlocking) Lower Climber Stage", Severity.INFO);
    	}
    	else
    		Logger.getInstance().println("Unsafe to deploy lower climber", true, Severity.WARNING);
    }
    
    public void lockLowerClimber() {
    	Logger.getInstance().println("Locking Lower Climber Stage", Severity.INFO);
    	pivotLow.set(false);
    }
    
    public boolean isLowerClimberDeployed()
    {
    	return !lowerLimit.get();
    }
    
    public boolean isUpperClimberDeployed()
    {
    	return !upperLimit.get();
    }

    public boolean upperClimberDeploy()
    {
    	if (Robot.arm.isSafeToDeployUpperClimber() && isLowerClimberDeployed()){
    		pivotHigh.set(true);
    		Logger.getInstance().println("Deploying (Unlocking) Upper Climber Stage", Severity.INFO);
    		return true;
    	}
    	else{
    		Logger.getInstance().println("Unsafe to deploy upper climber", true, Severity.WARNING);
    		return false;
    	}
    }
    
    public void upperClimberDeployUnsafe()
    {
    	pivotHigh.set(true);
    }
    
    public void lockUpperClimber() {
    	Logger.getInstance().println("Locking Upper Climber Stage", Severity.INFO);
    	pivotHigh.set(false);
    }

    public void engagePTO(){
       	powerTakeOff.set(DoubleSolenoid.Value.kForward);
    }

    public void disengagePTO(){
    	powerTakeOff.set(DoubleSolenoid.Value.kReverse);
    }

    public void stopClimber(){
    	disengagePTO();
    }
}

