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
    public JoystickButton killAll_10;
    public JoystickButton lidOpen_5;
    public JoystickButton lidClose_7;
    public JoystickButton pickupBall_2hold;
    public JoystickButton armToNeutral_2rel;
    public JoystickButton pickupReverse_8;
    public JoystickButton armPosition1_3;
    public JoystickButton armPosition2_4;
    public JoystickButton armPosition3_1;
    public JoystickButton aim_6;
    public Joystick armGamepad;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        armGamepad = new Joystick(3);
        
        aim_6 = new JoystickButton(armGamepad, 6);
        aim_6.whileHeld(new Aim());
        armPosition3_1 = new JoystickButton(armGamepad, 1);
        armPosition3_1.whenPressed(new ArmPosition3());
        armPosition2_4 = new JoystickButton(armGamepad, 4);
        armPosition2_4.whenPressed(new ArmPosition2());
        armPosition1_3 = new JoystickButton(armGamepad, 3);
        armPosition1_3.whenPressed(new ArmPosition1());
        pickupReverse_8 = new JoystickButton(armGamepad, 8);
        pickupReverse_8.whileHeld(new PickupReverse());
        armToNeutral_2rel = new JoystickButton(armGamepad, 2);
        armToNeutral_2rel.whenReleased(new ArmToNeutral());
        pickupBall_2hold = new JoystickButton(armGamepad, 2);
        pickupBall_2hold.whileHeld(new PickupBall());
        lidClose_7 = new JoystickButton(armGamepad, 7);
        lidClose_7.whenPressed(new LidClose());
        lidOpen_5 = new JoystickButton(armGamepad, 5);
        lidOpen_5.whenPressed(new LidOpen());
        killAll_10 = new JoystickButton(armGamepad, 10);
        killAll_10.whenPressed(new KillAll());
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
        SmartDashboard.putData("PickupReverse", new PickupReverse());
        SmartDashboard.putData("LidOpen", new LidOpen());
        SmartDashboard.putData("LidClose", new LidClose());
        SmartDashboard.putData("ManualArm", new ManualArm());
        SmartDashboard.putData("ManualTurret", new ManualTurret());
        SmartDashboard.putData("ClimberPTO_engage", new ClimberPTO_engage());
        SmartDashboard.putData("ClimberPTO_disengage", new ClimberPTO_disengage());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ShootWithoutLid", new ShootWithoutLid());
        SmartDashboard.putData("ClimberDeploy", new ClimberDeploy());
        SmartDashboard.putData("ArmPosition1", new ArmPosition1());
        SmartDashboard.putData("ArmPosition2", new ArmPosition2());
        SmartDashboard.putData("ArmPosition3", new ArmPosition3());
        SmartDashboard.putData("Aim", new Aim());
        SmartDashboard.putData("deployLowerClimber", new deployLowerClimber());
        SmartDashboard.putData("deployUpperClimber", new deployUpperClimber());
        SmartDashboard.putData("ArmToNeutral", new ArmToNeutral());
        SmartDashboard.putData("PickupBall", new PickupBall());
        SmartDashboard.putData("DeployPortcullisStinger", new DeployPortcullisStinger());
        SmartDashboard.putData("RetractPortcullisStinger", new RetractPortcullisStinger());

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

