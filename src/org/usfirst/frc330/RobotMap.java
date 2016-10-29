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
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

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
    public static PowerDistributionPanel frillsPowerDistPanel;
    public static SpeedController chassisSpeedControllerL1;
    public static SpeedController chassisSpeedControllerL2;
    public static SpeedController chassisSpeedControllerL3;
    public static DoubleSolenoid chassisShifter;
    public static SpeedController chassisSpeedControllerR1;
    public static SpeedController chassisSpeedControllerR2;
    public static SpeedController chassisSpeedControllerR3;
    public static Encoder chassisdriveTrainEncoderL;
    public static Encoder chassisdriveTrainEncoderR;
    public static AHRS chassisImu;
    public static AnalogInput chassisPressureSensor;
    public static CANTalon armarmL;
    public static CANTalon armarmR;
    public static Solenoid armPortcullisWedge;
    public static SpeedController pickupPickupR;
    public static SpeedController pickupPickupL;
    public static Solenoid pickupShooterFirstStage;
    public static Solenoid pickupShooterSecondStage;
    public static DoubleSolenoid pickuppickupLid;
    public static DigitalOutput pickupCameraLED;
    public static CANTalon turretturret;
    public static DoubleSolenoid climberPowerTakeOff;
    public static Solenoid climberPivotHigh;
    public static Solenoid climberPivotLow;
    public static DigitalInput climberLowerLimit;
    public static DigitalInput climberUpperLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frillsbuzzer = new DigitalOutput(0);
        LiveWindow.addActuator("Frills", "buzzer", frillsbuzzer);
        
        frillspracticeRobot = new DigitalInput(1);
        LiveWindow.addSensor("Frills", "practiceRobot", frillspracticeRobot);
        
        frillsPowerDistPanel = new PowerDistributionPanel(0);
        LiveWindow.addSensor("Frills", "PowerDistPanel", frillsPowerDistPanel);
        
        chassisSpeedControllerL1 = new VictorSP(2);
        LiveWindow.addActuator("Chassis", "Speed Controller L1", (VictorSP) chassisSpeedControllerL1);
        
        chassisSpeedControllerL2 = new VictorSP(3);
        LiveWindow.addActuator("Chassis", "Speed Controller L2", (VictorSP) chassisSpeedControllerL2);
        
        chassisSpeedControllerL3 = new VictorSP(4);
        LiveWindow.addActuator("Chassis", "Speed Controller L3", (VictorSP) chassisSpeedControllerL3);
        
        chassisShifter = new DoubleSolenoid(1, 3, 1);
        LiveWindow.addActuator("Chassis", "Shifter", chassisShifter);
        
        chassisSpeedControllerR1 = new VictorSP(5);
        LiveWindow.addActuator("Chassis", "Speed Controller R1", (VictorSP) chassisSpeedControllerR1);
        
        chassisSpeedControllerR2 = new VictorSP(6);
        LiveWindow.addActuator("Chassis", "Speed Controller R2", (VictorSP) chassisSpeedControllerR2);
        
        chassisSpeedControllerR3 = new VictorSP(7);
        LiveWindow.addActuator("Chassis", "Speed Controller R3", (VictorSP) chassisSpeedControllerR3);
        
        chassisdriveTrainEncoderL = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderL", chassisdriveTrainEncoderL);
        chassisdriveTrainEncoderL.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderL.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisdriveTrainEncoderR = new Encoder(4, 5, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "driveTrainEncoderR", chassisdriveTrainEncoderR);
        chassisdriveTrainEncoderR.setDistancePerPulse(1.0);
        chassisdriveTrainEncoderR.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisImu = new AHRS(Port.kMXP, (byte)50);
        LiveWindow.addSensor("Chassis", "Imu", chassisImu);
        
        chassisPressureSensor = new AnalogInput(0);
        LiveWindow.addSensor("Chassis", "PressureSensor", chassisPressureSensor);
        
        armarmL = new CANTalon(2);
        LiveWindow.addActuator("Arm", "armL", armarmL);
        
        armarmR = new CANTalon(3);
        LiveWindow.addActuator("Arm", "armR", armarmR);
        
        armPortcullisWedge = new Solenoid(0, 4);
        LiveWindow.addActuator("Arm", "PortcullisWedge", armPortcullisWedge);
        
        pickupPickupR = new Victor(1);
        LiveWindow.addActuator("Pickup", "PickupR", (Victor) pickupPickupR);
        
        pickupPickupL = new Victor(0);
        LiveWindow.addActuator("Pickup", "PickupL", (Victor) pickupPickupL);
        
        pickupShooterFirstStage = new Solenoid(1, 2);
        LiveWindow.addActuator("Pickup", "ShooterFirstStage", pickupShooterFirstStage);
        
        pickupShooterSecondStage = new Solenoid(1, 5);
        LiveWindow.addActuator("Pickup", "ShooterSecondStage", pickupShooterSecondStage);
        
        pickuppickupLid = new DoubleSolenoid(1, 6, 7);
        LiveWindow.addActuator("Pickup", "pickupLid", pickuppickupLid);
        
        pickupCameraLED = new DigitalOutput(9);
        LiveWindow.addActuator("Pickup", "CameraLED", pickupCameraLED);
        
        turretturret = new CANTalon(4);
        LiveWindow.addActuator("Turret", "turret", turretturret);
        
        climberPowerTakeOff = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("Climber", "PowerTakeOff", climberPowerTakeOff);
        
        climberPivotHigh = new Solenoid(0, 2);
        LiveWindow.addActuator("Climber", "PivotHigh", climberPivotHigh);
        
        climberPivotLow = new Solenoid(0, 3);
        LiveWindow.addActuator("Climber", "PivotLow", climberPivotLow);
        
        climberLowerLimit = new DigitalInput(6);
        LiveWindow.addSensor("Climber", "LowerLimit", climberLowerLimit);
        
        climberUpperLimit = new DigitalInput(7);
        LiveWindow.addSensor("Climber", "UpperLimit", climberUpperLimit);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
