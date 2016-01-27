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
import org.usfirst.frc330.constants.ArmPos;
import org.usfirst.frc330.constants.TurretPos;
import org.usfirst.frc330.util.CSVLoggable;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;



import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Arm extends Subsystem {

	protected PIDController armPID;
	double    tempSetPoint;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController arm = RobotMap.armarm;
    private final AnalogPotentiometer armPot = RobotMap.armarmPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    // Arm PIDController source and output objects
    public PIDOutput armPIDOutput = new PIDOutput() {

		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
		    setArm(output);
		}
    	
    };
    
    public PIDSource armPIDSource = new PIDSource() {
		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getArmAngle();
		}

		@Override
		public void setPIDSourceType(PIDSourceType arg0) {			
		}
    	
    };
    
	/////////////////////////////////////////////////////////////
	// MAIN CLASS
	/////////////////////////////////////////////////////////////
    public Arm() {

    	super();
    	
    	// Arm PIDController object
    	armPID = new PIDController(
    			ArmPos.proportional, 
    			ArmPos.integral, 
    			ArmPos.derivative, 
    			armPIDSource, 
    			armPIDOutput,
    			0.01);
    	
    	// Add to Smart Dashboard
    	SmartDashboard.putData("ArmPID", armPID);
    	
		/////////////////////////////////////////////////////////////////
		// LOG IT!
    	// TODO: Add additional logging as needed
		
		CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getArmAngle(); }
		};
		
		Robot.csvLogger.add("ArmAngle", temp);

    }
    
	/////////////////////////////////////////////////////////////
	// GET methods
	/////////////////////////////////////////////////////////////
    public double getArmAngle()
	{
		return 0.0;//TODO: Write this method
	}

	public double getArmOutput() {
		return arm.get();
	}
    
	public double pidGet() {
		return getArmAngle();
	}
	
	/////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////
	/* Set the arm */
    public void setArm(double output) {
    	
    	// Is the turret centered?
    	boolean centered = Robot.turret.getTurretAngle() < TurretPos.turretSafeLimitCW &&
    					   Robot.turret.getTurretAngle() > TurretPos.turretSafeLimitCCW;
    					   
    	//Don't let the arm go down if the turret is not centered
    	if ( !centered && output < 0)
    		arm.set(0);
    	/* AHHHH! The arm would eat the ground */
    	else if ( getArmAngle() < ArmPos.limitLowerAngle && output < 0) {
    		arm.set(0);
    	/* OH NOES! The arm would flip off the back of the robot */
    	} else if ( getArmAngle() > ArmPos.limitUpperAngle && output > 0) {
    		arm.set(0);
    	/* We good */
    	} else {
    		arm.set(output);
    	}
    }
    
    /* Set the arm angle */
    public void setArmAngle(double position) {
    	armPID.setSetpoint(position);
    }
	
    public void setTurretAbsoluteTolerance(double absvalue) {
		armPID.setAbsoluteTolerance(absvalue);
	}
    
    public void setPIDConstants (double P, double I, double D, double F)
	{
		armPID.setPID(P, I, D, F);
	}
    
	/////////////////////////////////////////////////////////////
	// OTHER Methods (helper functions and commands)
	/////////////////////////////////////////////////////////////
    /* Control the arm manually */
    public void manualArm() {

    	double armCommand = Robot.oi.armJoystick.getY();	

    	if ( Math.abs(armCommand) > ArmPos.deadZone) {
			if (armPID.isEnabled())
				armPID.disable();
		} else if ( !armPID.isEnabled() ) {
    		tempSetPoint = this.getArmAngle();
    		
    		if ( tempSetPoint < ArmPos.limitLowerAngle ) {
    			tempSetPoint = ArmPos.limitLowerAngle;
    		} else if ( tempSetPoint > ArmPos.limitUpperAngle ) {
    			tempSetPoint = ArmPos.limitUpperAngle;
    		}
    	
    		armPID.setSetpoint(tempSetPoint);
    		armPID.enable();
    	}
    	
    }

    public void stopArm()
	{
		if (armPID.isEnabled())
		{
			armPID.reset();
		}
	}
    
    public synchronized boolean isEnable() {
		return armPID.isEnabled();
	}
}
