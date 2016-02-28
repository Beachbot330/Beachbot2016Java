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
import org.usfirst.frc330.commands.ManualTurret;
import org.usfirst.frc330.constants.TurretConst;
import org.usfirst.frc330.util.CSVLoggable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


/**
 *
 */
public class Turret extends Subsystem implements LiveWindowSendable {

	double tolerance = 0;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon turret = RobotMap.turretturret;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ManualTurret());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/////////////////////////////////////////////////////////////
	// MAIN CLASS
	/////////////////////////////////////////////////////////////
	public Turret()
	{
		super();
		
    	SmartDashboard.putData("Turret", this);
		
		int absolutePosition = turret.getPulseWidthPosition() & 0xFFF;
		double position = absolutePosition - getTurretZero();
		if (position > TurretConst.maxEncoderCounts/2)
			position -= 4096;
		else if (position < -TurretConst.maxEncoderCounts/2)
			position += 4096;

		turret.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		turret.setPosition(convertTicksToRotations(position));
    	
    	turret.reverseSensor(false);
    	turret.reverseOutput(false);
    	setPIDConstants(TurretConst.proportional, TurretConst.integral, TurretConst.derivative);
    	setTurretAbsoluteTolerance(TurretConst.tolerance);
    	setSoftLimitsSafe();
    	turret.enableForwardSoftLimit(true);
    	turret.enableReverseSoftLimit(true);
    	turret.enableBrakeMode(true);
    	turret.setVoltageRampRate(TurretConst.VoltageRampRate);

		/////////////////////////////////////////////////////////////////
		// LOG IT!
		// TODO: Add additional logging as needed

		CSVLoggable temp = new CSVLoggable(true) {
			public double get() { return getTurretAngle(); }
		};
		Robot.csvLogger.add("TurretAngle", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getTurretOutput(); }
		};
		Robot.csvLogger.add("TurretOutput", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getTurretSetpoint(); }
		};
		Robot.csvLogger.add("TurretSetpoint", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getCCWSoftLimit(); }
		};
		Robot.csvLogger.add("TurretCCWSoftLimit", temp);
		
		temp = new CSVLoggable(true) {
			public double get() { return getCWSoftLimit(); }
		};
		Robot.csvLogger.add("TurretCWSoftLimit", temp);

		temp = new CSVLoggable(true) {
			public double get() { 
				if(getSensorFault())
					return 1.0;
				else
					return 0.0;
			}
		};
		Robot.csvLogger.add("turretFault", temp);
	}
	/////////////////////////////////////////////////////////////
	// SET methods
	/////////////////////////////////////////////////////////////

	// Helper function: SetTurret
	// Function: Set turret angle based on input of type double
	// Parameters:
	// 		double output - variable to set turret angle 
	// Return value:
	//		void
	public void setTurret(double output){
		changeControlMode(TalonControlMode.PercentVbus);
		turret.set(output);
	}

	// Helper function: setPIDConstants
	// Function: Set PID constants
	// Parameters:
	// 		double P - Proportional value
	//		double I - Integral value
	// 		double D - Derivative value
	//     
	// Return value:
	//		void
	public void setPIDConstants(double P, double I, double D)
	{
		turret.setPID(P, I, D);
		if (SCtable != null) {
            SCtable.putNumber("p", P);
            SCtable.putNumber("i", I);
            SCtable.putNumber("d", D);
		}
	}

	public void setTurretAbsoluteTolerance(double absvalue) {
    	tolerance = absvalue;
	}
	
	public void setTurretAngle(double position)
	{
		changeControlMode(TalonControlMode.Position);
		turret.setSetpoint(convertDegreesToRotations(position));
    	if (SCtable != null)
    		SCtable.putNumber("setpoint", position);
	}

	/////////////////////////////////////////////////////////////
	// GET methods
	/////////////////////////////////////////////////////////////	
	public double getTurretAngle()
	{
		return (convertRotationsToDegrees(turret.getPosition()));
	}

	public double getTurretOutput() {
		return turret.getOutputVoltage()/turret.getBusVoltage();
	}
	
	public boolean isCentered() {
    	return Robot.turret.getTurretAngle() < TurretConst.turretSafeLimitCW &&
				   Robot.turret.getTurretAngle() > TurretConst.turretSafeLimitCCW;
	}

	/////////////////////////////////////////////////////////////
	// PID Stuff
	/////////////////////////////////////////////////////////////
	public double getTurretSetpoint() {
		return convertRotationsToDegrees(turret.getSetpoint());
	}

	// Method to check if Turret is on target
	public boolean onTurretTarget() {
		double error = convertTicksToDegrees(turret.getClosedLoopError());
        return (Math.abs(error) < tolerance);
	}

	// Method returns if Turret is enabled
	public boolean isTurretEnabled() {
		return turret.isEnabled();
	}

	// Method to Enable Turret
	public void enableTurret() {
		turret.enable();
	}

	// Method to Disable Turret
	public void disableTurret() {
		turret.disable();
	}

	// Method to Disable Turret - called by disableTurret
	public void stopTurret()
	{
		if (turret.isEnabled())
		{
			turret.reset();
		}
		turret.enable();
		turret.changeControlMode(TalonControlMode.PercentVbus);
		turret.set(0);
		
	}

	//////////////////////////
	// Other Methods
	//////////////////////////
	double tempSetpoint;
	public void manualTurret() {
		double turretCommand = Robot.oi.armJoystick.getZ();
		double gamePad = Robot.oi.armGamepad.getZ();
		if (Math.abs(turretCommand) > TurretConst.deadZone){ //Driving via joystick
			setTurret(turretCommand/Math.abs(turretCommand)*Math.pow(turretCommand,2));
		}
		else if (Math.abs(gamePad) > TurretConst.deadZone){ //Driving via gamepad
			turretCommand = gamePad;
			if (turret.getControlMode() != TalonControlMode.PercentVbus){
				Robot.logger.println("Old Turret Mode: " + turret.getControlMode());
				turret.changeControlMode(TalonControlMode.PercentVbus);
				Robot.logger.println("New Turret Mode: " + turret.getControlMode());
			}
			turret.set(turretCommand/Math.abs(turretCommand)*Math.pow(turretCommand,2));
		}
		else if (turret.getControlMode() != TalonControlMode.Position)
		{
			tempSetpoint = getTurretAngle();
			if(tempSetpoint > getCWSoftLimit())
				tempSetpoint = getCWSoftLimit();
			else if(tempSetpoint < getCCWSoftLimit())
				tempSetpoint = getCCWSoftLimit();
			turret.changeControlMode(TalonControlMode.Position);
			setTurretAngle(tempSetpoint);
		} 
	}

	public boolean isEnable() {
		return turret.isEnabled();
	}
	
    private void setCCWSoftLimit(double CCWAngle) {
    	turret.setReverseSoftLimit(convertDegreesToRotations(CCWAngle));
    }
    
    private void setCWSoftLimit(double CWAngle) {
    	turret.setForwardSoftLimit(convertDegreesToRotations(CWAngle));
    }
    
    public void setSoftLimitsSafe() {
    	setCCWSoftLimit(TurretConst.turretSafeLimitCCW);
    	setCWSoftLimit(TurretConst.turretSafeLimitCW);
    }
    
    public void setSoftLimitsArmUp() {
    	setCCWSoftLimit(TurretConst.limitAngleCCW);
    	setCWSoftLimit(TurretConst.limitAngleCW);
    }
    
    public double getCCWSoftLimit() {
    	return convertTicksToDegrees(turret.getReverseSoftLimit());
    }
    
    public double getCWSoftLimit() {
    	return convertTicksToDegrees(turret.getForwardSoftLimit());
    }
    
    public void setTurretZero()
	{        
        String name;
        
        if (Robot.isPracticeRobot())
            name = "PracticeTurretZero";
        else
            name = "CompetitionTurretZero";
        
        Preferences.getInstance().putInt(name, turret.getPulseWidthPosition());
        turret.setEncPosition(0);
    }
    
    public boolean getSensorFault(){
    	CANTalon.FeedbackDeviceStatus sensorStatus;
    	sensorStatus = turret.isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	return (sensorStatus != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent);
    }
    
    public int getTurretZero() {
		String name;
        if (Robot.isPracticeRobot())
            name = "PracticeTurretZero";
        else
            name = "CompetitionTurretZero";
		return Preferences.getInstance().getInt(name,0);
	}
	
	// Helper function: convertDegreesToTicks
	// Function: Convert degrees to ticks
	// Parameters:
	// 		double degrees - Degrees to convert to ticks
	//     
	// Return value:
	//		int - Tick values
	private int convertDegreesToTicks(double degrees) {
    	return (int)(degrees * TurretConst.maxEncoderCounts / TurretConst.maxAngleDegrees + 0.5);
    }
    
	// Helper function: convertTicksToDegrees
	// Function: Convert ticks to degrees
	// Parameters:
	// 		double ticks - Ticks to convert to degrees
	//     
	// Return value:
	//		int - Degree values
    private double convertTicksToDegrees(int ticks) {
    	return (ticks * TurretConst.maxAngleDegrees / TurretConst.maxEncoderCounts);
    }
    
    // Helper function: convertDegreesToRotations
 	// Function: Convert degrees to rotations
 	// Parameters:
 	// 		double degrees - Degrees to convert to rotations
 	//     
 	// Return value:
 	//		double - Rotation value
    private double convertDegreesToRotations(double degrees) {
    	return (degrees / TurretConst.maxAngleDegrees);
    }
    
    // Helper function: convertRotationsToDegrees
  	// Function: Convert rotations to degrees
  	// Parameters:
  	// 		double rotations - Rotations to convert to degrees
  	//     
  	// Return value:
  	//		double - Degree values
    private double convertRotationsToDegrees(double rotations) {
    	return (rotations * TurretConst.maxAngleDegrees);
    }
    
	private double convertTicksToRotations(double position) {
		return position/TurretConst.maxEncoderCounts;
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
            table.putNumber("Mode", turret.getControlMode().getValue());
            table.putNumber("p", turret.getP());
            table.putNumber("i", turret.getI());
            table.putNumber("d", turret.getD());
            table.putNumber("f", turret.getF());
            table.putBoolean("Enabled", isEnable());
            table.putNumber("setpoint", getTurretSetpoint());
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
				if (turret.getP() != table.getNumber("p", 0.0) || turret.getI() != table.getNumber("i", 0.0)
						|| turret.getD() != table.getNumber("d", 0.0) || turret.getF() != table.getNumber("f", 0.0))
					Robot.logger.println("Changing PID from SmartDashboard.");
					Robot.logger.println("Old Value P: " + turret.getP() + " New Value: " + table.getNumber("p", 0.0));
					Robot.logger.println("Old Value I: " + turret.getI() + " New Value: " + table.getNumber("i", 0.0));
					Robot.logger.println("Old Value D: " + turret.getD() + " New Value: " + table.getNumber("d", 0.0));
					Robot.logger.println("Old Value F: " + turret.getF() + " New Value: " + table.getNumber("f", 0.0));
					setPIDConstants(table.getNumber("p", 0.0), table.getNumber("i", 0.0), table.getNumber("d", 0.0));
			} else if (key.equals("setpoint")) {
				if (getTurretSetpoint() != ((Double) value).doubleValue())
					Robot.logger.println("Changing Setpoint from SmartDashboard. Old Value: " + getTurretSetpoint() + " New Value: " + ((Double) value).doubleValue());
					setTurretAngle(((Double) value).doubleValue());
			} else if (key.equals("enabled")) {
				if (isEnable() != ((Boolean) value).booleanValue()) {
					if (((Boolean) value).booleanValue()) {
						enableTurret();
					} else {
						stopTurret();
					}
				}
			} else if (key.equals("Mode")) {
				if (turret.getControlMode().getValue() != ((Integer) value).intValue()) {
					changeControlMode(TalonControlMode.valueOf(((Integer) value).intValue()));
				}
			} else if (key.equals("Value")) {
				setTurret((Double) value); 
			}
		}
	};

	public void changeControlMode(TalonControlMode newControlMode) {
		TalonControlMode oldControlMode = turret.getControlMode();
		if (newControlMode != TalonControlMode.PercentVbus && newControlMode != TalonControlMode.Position) {
			throw new RuntimeException("Unsupported control mode for arm: " + newControlMode.toString());
		}
		Robot.logger.println("Old Arm Mode: " + oldControlMode);
		turret.changeControlMode(newControlMode);
		Robot.logger.println("New Arm Mode: " + newControlMode);
		if (SCtable != null)
			SCtable.putNumber("Mode", newControlMode.getValue());
	}


	@Override
	public void updateTable() {
		
	}
}
	

