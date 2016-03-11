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
import org.usfirst.frc330.commands.ManualArm;
import org.usfirst.frc330.constants.ArmConst;
import org.usfirst.frc330.util.CSVLoggable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


/**
 *
 */
public class Arm extends Subsystem implements LiveWindowSendable {
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon armL = RobotMap.armarmL;
    private final CANTalon armR = RobotMap.armarmR;
    private final Solenoid portcullisWedge = RobotMap.armPortcullisWedge;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ManualArm());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    
	/////////////////////////////////////////////////////////////
	// MAIN CLASS
	/////////////////////////////////////////////////////////////
    public Arm() {

    	super();
    	
    	SmartDashboard.putData("Arm", this);
    	
    	int absolutePosition = armL.getPulseWidthPosition() & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        armL.setEncPosition(absolutePosition - getArmZero());
        //armR.setEncPosition(absolutePosition - getArmZero());
    	
    	armL.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	armL.reverseSensor(false);
    	armL.reverseOutput(false);
    	setPIDConstants(ArmConst.proportional, ArmConst.integral, ArmConst.derivative);
    	setArmAbsoluteTolerance(ArmConst.tolerance);
    	setLowerSoftLimit(ArmConst.limitLowerAngle);
    	setUpperSoftLimit(ArmConst.limitUpperAngle);
    	armL.enableForwardSoftLimit(true);
    	armL.enableReverseSoftLimit(true);
    	armL.enableBrakeMode(true);
    	armL.setVoltageRampRate(ArmConst.VoltageRampRate);
    	armL.configMaxOutputVoltage(ArmConst.MaxOutputVoltage);
    	
    	//set armR to follow armL, reversed
    	armR.changeControlMode(TalonControlMode.Follower);
    	armR.set(armL.getDeviceID());
    	armR.reverseOutput(true);
    	armR.enableBrakeMode(true);
    	armR.enableForwardSoftLimit(false);
    	armR.enableReverseSoftLimit(false);
    	
    	
		/////////////////////////////////////////////////////////////////
		// LOG IT!
    	// TODO: Add additional logging as needed
		
		CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getArmAngle(); }
		};
		Robot.csvLogger.add("ArmAngle", temp);

		temp = new CSVLoggable(true) {
			public double get() { return getArmOutput(); }
		};
		Robot.csvLogger.add("ArmOutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getCurrentQuadrant(); }
		};
		Robot.csvLogger.add("ArmQuadrant", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getSetpoint(); }
		};
		Robot.csvLogger.add("ArmSetpoint", temp);
		
//		temp = new CSVLoggable(true) {
//			public double get() { return getArmPositionTicks(); }
//		};
//		Robot.csvLogger.add("ArmTicks", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getLowerLimit(); }
		};
		Robot.csvLogger.add("ArmLowerLimit", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getUpperLimit(); }
		};
		Robot.csvLogger.add("ArmUpperLimit", temp);
		
    }
    
	/////////////////////////////////////////////////////////////
	// GET methods
	/////////////////////////////////////////////////////////////
    
    // Get input from encoder for Arm
    public double getArmAngle()
	{
		return (-convertRotationsToDegrees(armL.getPosition()));
	}
    
    public double getArmPositionTicks()
	{
		return (convertDegreesToTicks(getArmAngle()));
	}
    
    public double getLowerLimit()
	{
		return (convertTicksToDegrees(armL.getForwardSoftLimit()));
	}
    
    public double getUpperLimit()
	{
		return (convertTicksToDegrees(armL.getReverseSoftLimit()));
	}

	public double getArmOutput() {
		return armL.getOutputVoltage()/armL.getBusVoltage();
	}
	
	public boolean getLowerLimitTripped(){
		if (armL.getFaultForSoftLim() != 0)
			return true;
		else
			return false;
	}
	
	public boolean getUpperLimitTripped(){
		if(armL.getFaultRevSoftLim() != 0)
			return true;
		else
			return false;
	}
	
	/////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////
	/* Set the arm */
    public void setArm(double output) {
    	changeControlMode(TalonControlMode.PercentVbus);
    	armL.set(output);
    }
    
    /* Set the arm angle */
    public void setArmAngle(double position) {
    	changeControlMode(TalonControlMode.Position);
    	armL.setSetpoint(convertDegreesToRotations(position));
    	if (SCtable != null)
    		SCtable.putNumber("setpoint", position);
    }
    
    public double getSetpoint() {
    	return convertRotationsToDegrees(-armL.getSetpoint());
    }
    
    public void setPIDConstants (double P, double I, double D)
	{
		armL.setPID(P, I, D);
		if (SCtable != null) {
            SCtable.putNumber("p", P);
            SCtable.putNumber("i", I);
            SCtable.putNumber("d", D);
		}
	}
    
    public int getCurrentQuadrant() {
    	return (int)(getArmAngle()/ArmConst.maxAngleDegrees);
    }
    
    // Quadrants are zero-indexed (0-4)
    // 0 = Lowest; 4 = Highest
    public void setQuadrantPosition(int quadrant)
    {
    	// Grab positioning from arm angle
    	double currentArmAngle = getArmAngle();
    	int currentQuadrant = getCurrentQuadrant();
    	double angleChange = (quadrant-currentQuadrant)*ArmConst.maxAngleDegrees;
    	
    	if ( quadrant >= ArmConst.minQuadrant && quadrant <= ArmConst.maxQuadrant )
    	{
    		// Set the position
    		Robot.logger.println("Original Quadrant: " + currentQuadrant, true);
    		Robot.logger.println("New Quadrant: " + quadrant, true);
    		armL.setPosition(convertDegreesToRotations(currentArmAngle + angleChange));
    		if (armL.getControlMode() == TalonControlMode.Position)
    			setArmAngle(getArmAngle() + angleChange);
    	}
    	else
    	{
    		throw new RuntimeException("setQuadrantPosition: Quadrant must be integer value " 
    				+ ArmConst.minQuadrant + "-" + ArmConst.maxQuadrant);
    	}  	
    	
    }
    
	/////////////////////////////////////////////////////////////
	// OTHER Methods (helper functions and commands)
	/////////////////////////////////////////////////////////////
    /* Control the arm manually */
    int inertiaCounter;
    public void manualArm() {	
    	double gamepadCommand = -Robot.oi.armGamepad.getY();
    	double angle;
    	
    	if (Math.abs(gamepadCommand) > ArmConst.gamepadDeadZone) {
    		setArm(gamepadCommand/Math.abs(gamepadCommand)*Math.pow(gamepadCommand, 2));
    		inertiaCounter = ArmConst.inertiaCounter;
    	}
    	else if (inertiaCounter > 0) {
    		inertiaCounter--;
			setArm(0);
    	}
    	else if ( armL.getControlMode() != TalonControlMode.Position) {
			angle = getArmAngle();
			if (angle < getLowerLimit())
				angle = getLowerLimit();
			else if (angle > getUpperLimit())
				angle = getUpperLimit();

			setArmAngle(angle);
    	} 
    	
    }
    

    public void stopArm()
	{
		if (armL.isEnabled())
		{
			armL.reset();
		}
		armL.enable();
		changeControlMode(TalonControlMode.PercentVbus);
		armL.set(0);
	}
    
    public boolean isEnable() {
		return armL.isEnabled();
	}
    
    double tolerance = 0;
    public void setArmAbsoluteTolerance(double absvalue) {
    	tolerance = absvalue;
	}
    
    // Method to Enable Arm
    public void enableArm() {
        armL.enable();
    }
    
    // Method to check if Arm is on target
    public boolean onArmTarget() {
    	double error = convertTicksToDegrees(armL.getClosedLoopError());
        return (Math.abs(error) < tolerance);
    }
    
    public void retractStinger() {
    	portcullisWedge.set(false);
    }
    public void deployStinger() {
    	if (getArmAngle() < ArmConst.safeToDeployPortcullis)
    		portcullisWedge.set(true);
    }
    
    public boolean isStingerDeployed() {
    	return portcullisWedge.get();
    }
    
    public void setArmZero()
	{        
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeArmZero";
        else
            name = "CompetitionArmZero";
        
        Preferences.getInstance().putInt(name, armL.getPulseWidthPosition());
        armL.setEncPosition(0);
        armR.setEncPosition(0);
    }
    
    public int getArmZero() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeArmZero";
        else
            name = "CompetitionArmZero";
		return Preferences.getInstance().getInt(name,0);
	}
    
    private void setLowerSoftLimit(double lowerAngle) {
    	armL.setForwardSoftLimit(convertDegreesToRotations(lowerAngle));
    }
    
    private void setUpperSoftLimit(double upperAngle) {
    	armL.setReverseSoftLimit(convertDegreesToRotations(upperAngle));
    }
    
    private int convertDegreesToTicks(double degrees) {
    	return (int)(degrees * ArmConst.maxEncoderCounts / ArmConst.maxAngleDegrees + 0.5);
    }
    
    private double convertTicksToDegrees(int ticks) {
    	return (ticks * ArmConst.maxAngleDegrees / ArmConst.maxEncoderCounts);
    }
    
    private double convertDegreesToRotations(double degrees) {
    	return (-degrees / ArmConst.maxAngleDegrees);
    }
    
    private double convertRotationsToDegrees(double rotations) {
    	return (rotations * ArmConst.maxAngleDegrees);
    }
    
    public void monitorArm() {
    	if (isStingerDeployed()) {
    		setUpperSoftLimit(ArmConst.safeToDeployPortcullis);
    	} else {
    		setUpperSoftLimit(ArmConst.limitUpperAngle);
    	}
    		
    	if (Robot.turret.isCentered()) {
        	setLowerSoftLimit(ArmConst.limitLowerAngle);
    	} else {
    		setLowerSoftLimit(ArmConst.armSafeLimit);
    	}
    	
    	if (Robot.arm.getArmAngle() <= ArmConst.armSafeLimit) {
    		Robot.turret.setSoftLimitsSafe(); 
    	} else {
    		Robot.turret.setSoftLimitsArmUp();
    	}
    }


    /**
     * All CAN Speed Controllers have the same SmartDashboard type: "CANSpeedController".
     */
    String SMART_DASHBOARD_TYPE = "CANSpeedController";

    private ITable SCtable;
    @Override
    public void initTable(ITable table) {
        if (this.SCtable != null)
            this.SCtable.removeTableListener(listener);
          this.SCtable = table;
        if(table != null) {
            table.putString("~TYPE~", SMART_DASHBOARD_TYPE);
            table.putString("Type", "CANTalon"); // "CANTalon", "CANJaguar"
            table.putNumber("Mode", armL.getControlMode().getValue());
            table.putNumber("p", armL.getP());
            table.putNumber("i", armL.getI());
            table.putNumber("d", armL.getD());
            table.putNumber("f", armL.getF());
            table.putBoolean("Enabled", isEnable());
            table.putNumber("setpoint", getSetpoint());
            table.addTableListener(listener, false);
        }
    }


	@Override
	public void startLiveWindowMode() {

	}


	@Override
	public void stopLiveWindowMode() {
		
	}
	
	private final ITableListener listener = new ITableListener() {
		@Override
		public void valueChanged(ITable table, String key, Object value, boolean isNew) {
			if (key.equals("p") || key.equals("i") || key.equals("d") || key.equals("f")) {
				if (armL.getP() != table.getNumber("p", 0.0) || armL.getI() != table.getNumber("i", 0.0)
						|| armL.getD() != table.getNumber("d", 0.0) || armL.getF() != table.getNumber("f", 0.0))
					Robot.logger.println("Changing PID from SmartDashboard.");
					Robot.logger.println("Old Value P: " + armL.getP() + " New Value: " + table.getNumber("p", 0.0));
					Robot.logger.println("Old Value I: " + armL.getI() + " New Value: " + table.getNumber("i", 0.0));
					Robot.logger.println("Old Value D: " + armL.getD() + " New Value: " + table.getNumber("d", 0.0));
					Robot.logger.println("Old Value F: " + armL.getF() + " New Value: " + table.getNumber("f", 0.0));
					setPIDConstants(table.getNumber("p", 0.0), table.getNumber("i", 0.0), table.getNumber("d", 0.0));
			} else if (key.equals("setpoint")) {
				if (getSetpoint() != ((Double) value).doubleValue())
					Robot.logger.println("Changing Setpoint from SmartDashboard. Old Value: " + getSetpoint() + " New Value: " + ((Double) value).doubleValue());
					setArmAngle(((Double) value).doubleValue());
			} else if (key.equals("enabled")) {
				if (isEnable() != ((Boolean) value).booleanValue()) {
					if (((Boolean) value).booleanValue()) {
						enableArm();
					} else {
						stopArm();
					}
				}
			} else if (key.equals("Mode")) {
				if (armL.getControlMode().getValue() != ((Double) value).intValue()) {
					changeControlMode(TalonControlMode.valueOf(((Integer) value).intValue()));
				}
			} else if (key.equals("Value")) {
				setArm((Double) value); 
			}
		}
	};

	public void changeControlMode(TalonControlMode newControlMode) {
		TalonControlMode oldControlMode = armL.getControlMode();
		if (newControlMode != TalonControlMode.PercentVbus && newControlMode != TalonControlMode.Position) {
			throw new RuntimeException("Unsupported control mode for arm: " + newControlMode.toString());
		}
		if (oldControlMode != newControlMode) {
			Robot.logger.println("Old Arm Mode: " + oldControlMode);
			armL.changeControlMode(newControlMode);
			Robot.logger.println("New Arm Mode: " + newControlMode);
			if (SCtable != null)
				SCtable.putNumber("Mode", newControlMode.getValue());
		}
	}


	@Override
	public void updateTable() {
		
	}
	
	public boolean isSafeToDeployLowerClimber() {
		return getArmAngle() >= ArmConst.safeToDeployLowerClimber;
	}
	
	public boolean isSafeToDeployUpperClimber() {
		return getArmAngle() >= ArmConst.safeToDeployUpperClimber;
	}
}
