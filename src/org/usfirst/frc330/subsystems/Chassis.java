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
import org.usfirst.frc330.constants.ChassisConst;
import org.usfirst.frc330.util.CSVLoggable;
import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPIDController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Chassis extends Subsystem
{

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController speedControllerL1 = RobotMap.chassisSpeedControllerL1;
    private final SpeedController speedControllerL2 = RobotMap.chassisSpeedControllerL2;
    private final SpeedController speedControllerL3 = RobotMap.chassisSpeedControllerL3;
    private final DoubleSolenoid shifter = RobotMap.chassisShifter;
    private final SpeedController speedControllerR1 = RobotMap.chassisSpeedControllerR1;
    private final SpeedController speedControllerR2 = RobotMap.chassisSpeedControllerR2;
    private final SpeedController speedControllerR3 = RobotMap.chassisSpeedControllerR3;
    private final Encoder driveTrainEncoderL = RobotMap.chassisdriveTrainEncoderL;
    private final Encoder driveTrainEncoderR = RobotMap.chassisdriveTrainEncoderR;
    private final AHRS imu = RobotMap.chassisImu;
    private final AnalogInput pressureSensor = RobotMap.chassisPressureSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    double  gyro_prevVal = 0.0;
    int     ctrRollOver  = 0;
    boolean fFirstUse    = true;
    double  left, right;
    
    public MultiPIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;
    
    public Chassis()
    {
    	super();
        
        PIDSource gyroSource = new PIDSource()
        {

			@Override
			public double pidGet()
			{
				return getAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource)
			{
			}

			@Override
			public PIDSourceType getPIDSourceType()
			{
				return PIDSourceType.kDisplacement;
			}
        	
        };
        
        
        gyroOutput = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();
        
        gyroPID = new MultiPIDController(ChassisConst.GyroTurnLow, gyroSource,gyroOutput,"Gyro");
        leftDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveTrainEncoderL,leftDriveOutput,"LeftDrive");
        rightDrivePID = new MultiPIDController(ChassisConst.DriveLow, driveTrainEncoderR,rightDriveOutput, "RightDrive");
        
        gyroPID.setToleranceBuffer(ChassisConst.gyroTolerancebuffer);
        
        SmartDashboard.putData("gyroPID", gyroPID);
        SmartDashboard.putData("leftDrivePID", leftDrivePID);
        SmartDashboard.putData("rightDrivePID", rightDrivePID);
        
        double pulsePerRevolution;
        if (Robot.isPracticeRobot())
        	pulsePerRevolution = ChassisConst.practicePulsePerRevolution;
        else
        	pulsePerRevolution = ChassisConst.pulsePerRevolution;
        
        final double distanceperpulse = Math.PI*ChassisConst.wheelDiameter/pulsePerRevolution /
        		ChassisConst.encoderGearRatio/ChassisConst.gearRatio * ChassisConst.Fudgefactor;

        driveTrainEncoderR.setReverseDirection(false);
        driveTrainEncoderL.setDistancePerPulse(distanceperpulse);
        driveTrainEncoderR.setDistancePerPulse(distanceperpulse);
     
        	
        // LOGGING!
        CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderL.getDistance(); }
    	};
    	Robot.csvLogger.add("DriveTrainDistanceL", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return driveTrainEncoderR.getDistance(); }
    	};
    	Robot.csvLogger.add("DriveTrainDistanceR", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderL.getRate(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainRateL", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return driveTrainEncoderR.getRate(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainRateR", temp);    	

    	temp = new CSVLoggable() {
			public double get() { return speedControllerL1.get(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainLeft", temp);
    	
    	temp = new CSVLoggable() {
			public double get() { return speedControllerR1.get(); }  		
    	};
    	Robot.csvLogger.add("DriveTrainRight", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getAngle(); }  		
    	};    	
    	Robot.csvLogger.add("ChassisAngle", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.isConnected() ? 1: 0; }  		
    	};    	
    	Robot.csvLogger.add("GyroIsConnected", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getX(); }  		
    	};     	
    	Robot.csvLogger.add("ChassisX", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getY(); }  		
    	};      	
    	Robot.csvLogger.add("ChassisY", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return getPressure(); }  		
    	};  
    	Robot.csvLogger.add("Pressure", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { 
				DoubleSolenoid.Value state = shifter.get();
				double state_int;
				if (state == DoubleSolenoid.Value.kForward)
					state_int = 1.0;
				else
					state_int = 0.0;
				return state_int;}  		
    	};  
    	Robot.csvLogger.add("Shifter", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.getPitch(); }  		
    	};  
    	Robot.csvLogger.add("ChassisPitch", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.getRoll(); }  		
    	};  
    	Robot.csvLogger.add("ChassisRoll", temp);
    	
/*    	temp = new CSVLoggable(true) {
			public double get() { return imu.getDisplacementX(); }  		
    	};  
    	Robot.csvLogger.add("ChassisIMUX", temp);
    	
    	temp = new CSVLoggable(true) {
			public double get() { return imu.getDisplacementY(); }  		
    	};  
    	Robot.csvLogger.add("ChassisIMUY", temp); */
    	
//    	temp = new CSVLoggable(true) {
//    		public double get() { return Robot.powerDP.getChassisLeftDrive1Current(); }
//    	};
//    	Robot.csvLogger.add("LeftDrive1Current", temp);
//    	
//    	temp = new CSVLoggable() {
//    		public double get() { return Robot.powerDP.getChassisLeftDrive2Current(); }
//    	};
//    	Robot.csvLogger.add("LeftDrive2Current", temp);
//    	
//    	temp = new CSVLoggable(true) {
//    		public double get() { return Robot.powerDP.getChassisRightDrive1Current(); }
//    	};
//    	Robot.csvLogger.add("RightDrive1Current", temp);
//    	
//    	temp = new CSVLoggable() {
//    		public double get() { return Robot.powerDP.getChassisRightDrive2Current(); }
//    	};
//    	Robot.csvLogger.add("RightDrive2Current", temp);
        
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    private double x = 0, 
		           y = 0;
    private double prevLeftEncoderDistance  = 0, 
		           prevRightEncoderDistance = 0;
    
    public void calcXY()
    {
        double distance, 
        	   leftEncoderDistance, 
        	   rightEncoderDistance, 
        	   gyroAngle;
        
        leftEncoderDistance  = driveTrainEncoderL.getDistance();
        rightEncoderDistance = driveTrainEncoderR.getDistance();
        gyroAngle = getAngle();
        distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
        x = x + distance * Math.sin(Math.toRadians(gyroAngle));
        y = y + distance * Math.cos(Math.toRadians(gyroAngle));
        prevLeftEncoderDistance  = leftEncoderDistance;
        prevRightEncoderDistance = rightEncoderDistance;
    }
    
    public void resetPosition()
    {
    	driveTrainEncoderL.reset();
    	driveTrainEncoderR.reset();
        imu.zeroYaw();
        fFirstUse = true;
        ctrRollOver = 0;
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
        imu.resetDisplacement();
    } /* End resetPosition */
    
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    } /* End setXY */
    
    public void setXYoffset(double xOffset, double yOffset) {
    	this.x += xOffset;
    	this.y += yOffset;
    }
    
    // Function Name: getAngle()
    // Purpose: Return angle relative to 0 instead of -/+ 180 degrees
    public double getAngle()
    {

    	// First case
    	// Old reading: +150 degrees
    	// New reading: +170 degrees
    	// Difference:  (170 - 150) = +20 degrees
    	
    	// Second case
    	// Old reading: -20 degrees
    	// New reading: -50 degrees
    	// Difference : (-50 - -20) = -30 degrees 
    	
    	// Third case
    	// Old reading: +179 degrees
    	// New reading: -179 degrees
    	// Difference:  (-179 - 179) = -358 degrees
    	
    	// Fourth case
    	// Old reading: -179  degrees
    	// New reading: +179 degrees
    	// Difference:  (+150 - -60) = +358 degrees
    	
    	// Declare variables
    	double difference = 0.0;
    	double gyroVal    = 0.0;
    	
    	// Retrieve current yaw value from gyro
    	double yawVal     = imu.getYaw();
        
    	// Has gyro_prevVal been previously set?
    	// If not, return do not calculate, return current value
    	if( !fFirstUse )
    	{
    		// Determine count for rollover counter
    		difference = yawVal - gyro_prevVal;

	    	// Clockwise past +180 degrees
    		// If difference > 180*, increment rollover counter
	    	if( difference < -180.0 ) {
	    		ctrRollOver++;
	   		
	    	// Counter-clockwise past -180 degrees\
	    	// If difference > 180*, decrement rollover counter
	    	}
	    	else if ( difference > 180.0 ) {
	    		ctrRollOver--;
	    	} 
    	}
    	
    	// Mark gyro_prevVal as being used
    	fFirstUse = false;
    		
    	// Calculate value to return back to calling function
    	// e.g. +720 degrees or -360 degrees
    	gyroVal = yawVal + (360.0 * ctrRollOver);
    	gyro_prevVal = yawVal;
    	
    	return gyroVal;
    } /* End getAngle() */
    
    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick)
    {
       left = -leftJoystick.getY();
       right = -rightJoystick.getY();
    }
    
    public void tankDrive(double left, double right)
    {
        this.left = left;
        this.right = right;
    }
    
    public void stopDrive()
    {
        if (gyroPID.isEnabled())
            gyroPID.reset();
        if (leftDrivePID.isEnabled())
            leftDrivePID.reset();
        if (rightDrivePID.isEnabled())
            rightDrivePID.reset();        
       
        tankDrive(0, 0);  
    }
    
    private void drive(double left, double right)
    {
        
        speedControllerL1.set(-left);
        speedControllerL2.set(-left);
        speedControllerL3.set(-left);
        
        speedControllerR1.set(right);
        speedControllerR2.set(right);
        speedControllerR3.set(right);
    }
    
    public void pidDrive()
    {
        double left, right;
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
            left = this.left+leftDriveOutput.getOutput() + gyroOutput.getOutput();
            right = this.right+rightDriveOutput.getOutput() - gyroOutput.getOutput();
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    }
    
    public void pidDriveAuto()
    {
        double left, right, gyroValue;
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
        	//gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()) , 0.5);
        	gyroValue = Math.signum(gyroOutput.getOutput()) * Math.min(Math.abs(gyroOutput.getOutput()) , 1.0);
        	left = this.left+leftDriveOutput.getOutput() + gyroValue;
            right = this.right+rightDriveOutput.getOutput() - gyroValue;
            drive(left, right);
            this.left = 0;
            this.right = 0;
        }
    } /* End pidDriveAuto() */
    
    
    public void shiftHigh()
    {
    	shifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftLow()
    {
    	shifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean isHighGear()
    {
    	return (shifter.get() == DoubleSolenoid.Value.kForward);
    }
    
    
    /////////////////////////////////////////////////////////////
    // GET methods
    /////////////////////////////////////////////////////////////   
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getLeftDistance() 
    {
    	return driveTrainEncoderL.getDistance();
    }
    
    public double getRightDistance() {
    	return driveTrainEncoderR.getDistance();
    }
    
    public double getPressure()
    {
    	if (Robot.isPracticeRobot())
    		return (50*pressureSensor.getAverageVoltage() -25);
    	return 37.5*(pressureSensor.getAverageVoltage()- 0.5);
    }
}
