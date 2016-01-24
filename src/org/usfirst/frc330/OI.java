// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.commandgroups.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc330.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shiftHigh_1;
    public Joystick driverL;
    public JoystickButton shiftLow_1;
    public Joystick driverR;
    public JoystickButton killAll_13;
    public Joystick armJoystick;
    public JoystickButton killAll_16;
    public Joystick armGamepad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        armGamepad = new Joystick(3);
        
        killAll_16 = new JoystickButton(armGamepad, 1);
        killAll_16.whenPressed(new KillAll());
        armJoystick = new Joystick(2);
        
        killAll_13 = new JoystickButton(armJoystick, 1);
        killAll_13.whenPressed(new KillAll());
        driverR = new Joystick(1);
        
        shiftLow_1 = new JoystickButton(driverR, 1);
        shiftLow_1.whenPressed(new ShiftLow());
        driverL = new Joystick(0);
        
        shiftHigh_1 = new JoystickButton(driverL, 1);
        shiftHigh_1.whenPressed(new ShiftHigh());


        // SmartDashboard Buttons
        SmartDashboard.putData("KillAll", new KillAll());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("TankDrive", new TankDrive());
        SmartDashboard.putData("BuzzerBeepTimed", new BuzzerBeepTimed());
        SmartDashboard.putData("PickupOn", new PickupOn());
        SmartDashboard.putData("PickupOff", new PickupOff());
        SmartDashboard.putData("ReversePickup", new ReversePickup());
        SmartDashboard.putData("LidOpen", new LidOpen());
        SmartDashboard.putData("LidClose", new LidClose());
        SmartDashboard.putData("ManualArm", new ManualArm());
        SmartDashboard.putData("ManualTurret", new ManualTurret());
        SmartDashboard.putData("ClimberVertical", new ClimberVertical());
        SmartDashboard.putData("ClimberRatchetDisengage", new ClimberRatchetDisengage());
        SmartDashboard.putData("ClimberRatchetEngage", new ClimberRatchetEngage());
        SmartDashboard.putData("ClimberPTO_engage", new ClimberPTO_engage());
        SmartDashboard.putData("ClimberPTO_disengage", new ClimberPTO_disengage());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ShootWithoutLid", new ShootWithoutLid());
        SmartDashboard.putData("ClimberDeploy", new ClimberDeploy());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverL() {
        return driverL;
    }

    public Joystick getDriverR() {
        return driverR;
    }

    public Joystick getArmJoystick() {
        return armJoystick;
    }

    public Joystick getArmGamepad() {
        return armGamepad;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

