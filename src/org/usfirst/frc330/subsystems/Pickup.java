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

import org.usfirst.frc330.RobotMap;
import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.constants.ChassisConst;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Pickup extends Subsystem
{

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController pickupR = RobotMap.pickupPickupR;
    private final SpeedController pickupL = RobotMap.pickupPickupL;
    private final Solenoid shooterFirstStage = RobotMap.pickupShooterFirstStage;
    private final Solenoid shooterSecondStage = RobotMap.pickupShooterSecondStage;
    private final DoubleSolenoid pickupLid = RobotMap.pickuppickupLid;
    private final DigitalOutput cameraLED = RobotMap.pickupCameraLED;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void shootSolenoidsOn()
    {
    	shooterFirstStage.set(true);
    	shooterSecondStage.set(true);
    }

    public void shootSolenoidsOff(){
    	shooterFirstStage.set(false);
    	shooterSecondStage.set(false);
    }
    
    public void shootSecondStageSolenoids(){
    	shooterFirstStage.set(false);
    	shooterSecondStage.set(true);
    }
    
    public void shootFirstStageSolenoids(){
    	shooterFirstStage.set(true);
    	shooterSecondStage.set(false);
    }
    
    //Picks up foward
    public void pickupForward()
    {
    	
    	// Close the lid
    	closeLid();
    	
    	// Turn on right and left motors
    	// for sucking in
    	startPickup(true);
    	
    }
    
    // reverses pick up
    public void pickupReverse()
    {
    	
    	// Closes the lid
    	closeLid();
    
    	// Turn on the right and left motors 
    	// for throwing out
    	startPickup(false);
    	
    }
    
    // this initiates pick up system 
    // note: set in chassis constant
    // 	true means pick up forward
    //	false means pick up reverse 
    public void startPickup(boolean choice)
    {
    	if(choice)
    	{
    		pickupR.set(ChassisConst.pickupSpeed);
    		pickupL.set(-ChassisConst.pickupSpeed);
    	}
    	else
    	{
    		pickupR.set(-(ChassisConst.pickupReverseSpeed));
    		pickupL.set((ChassisConst.pickupReverseSpeed));
    	}
    }
   
    // this stops the the pickup
    public void stopPickup()
    {
    	pickupR.set(0);
    	pickupL.set(0);
    }
    
    // this opens the lid and turns on the camera LEDs
    public void openLid()
    {
    	cameraLED.set(true);
    	pickupLid.set(DoubleSolenoid.Value.kForward);
    }
    
    // this closes the lid and turns off the camera LEDs
    public void closeLid()
    {
    	cameraLED.set(false);
    	pickupLid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void turnOnLEDs(){
    	cameraLED.set(true);
    }
    
    public void turnOffLEDs(){
    	cameraLED.set(false);
    }
    
}

