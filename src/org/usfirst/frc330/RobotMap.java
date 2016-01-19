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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DigitalOutput frillsbuzzer;
    public static DigitalInput frillspracticeRobot;
    public static SpeedController chassisSpeedControllerL3;
    public static SpeedController chassisSpeedControllerL2;
    public static DoubleSolenoid chassisShifter;
    public static SpeedController chassisSpeedControllerL1;
    public static SpeedController chassisSpeedControllerR1;
    public static SpeedController chassisSpeedControllerR2;
    public static SpeedController chassisSpeedControllerR3;
    public static Encoder chassisdriveTrainEncoderL;
    public static Encoder chassisdriveTrainEncoderR;
    public static SpeedController armarm;
    public static AnalogPotentiometer armarmPot;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frillsbuzzer = new DigitalOutput(0);
        LiveWindow.addActuator("Frills", "buzzer", frillsbuzzer);
        
        frillspracticeRobot = new DigitalInput(1);
        LiveWindow.addSensor("Frills", "practiceRobot", frillspracticeRobot);
        
        chassisSpeedControllerL3 = new Talon(5);
        LiveWindow.addActuator("Chassis", "Speed Controller L3", (Talon) chassisSpeedControllerL3);
        
        chassisSpeedControllerL2 = new Talon(3);
        LiveWindow.addActuator("Chassis", "Speed Controller L2", (Talon) chassisSpeedControllerL2);
        
        chassisShifter = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("Chassis", "Shifter", chassisShifter);
        
        chassisSpeedControllerL1 = new Talon(2);
        LiveWindow.addActuator("Chassis", "Speed Controller L1", (Talon) chassisSpeedControllerL1);
        
        chassisSpeedControllerR1 = new Talon(6);
        LiveWindow.addActuator("Chassis", "Speed Controller R1", (Talon) chassisSpeedControllerR1);
        
        chassisSpeedControllerR2 = new Talon(7);
        LiveWindow.addActuator("Chassis", "Speed Controller R2", (Talon) chassisSpeedControllerR2);
        
        chassisSpeedControllerR3 = new Talon(8);
        LiveWindow.addActuator("Chassis", "Speed Controller R3", (Talon) chassisSpeedControllerR3);
        
        chassisdriveTrainEncoderL = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderL", chassisdriveTrainEncoderL);
        chassisdriveTrainEncoderL.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderL.setPIDSourceType(PIDSourceType.kRate);
        chassisdriveTrainEncoderR = new Encoder(4, 5, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderR", chassisdriveTrainEncoderR);
        chassisdriveTrainEncoderR.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderR.setPIDSourceType(PIDSourceType.kRate);
        armarm = new Talon(0);
        LiveWindow.addActuator("Arm", "arm", (Talon) armarm);
        
        armarmPot = new AnalogPotentiometer(0, 1.0, 0.0);
        LiveWindow.addSensor("Arm", "armPot", armarmPot);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
