// Robot Turret Constants

package org.usfirst.frc330.constants;

public final class TurretConst {
	
	private TurretConst(){}

	// Tolerance
	public static final double tolerance            = 0.5;  //TODO: Initial Value
	public static final double pickupTolerance	    = 5.0;  //AP 2-6 (WAG)
	public static final double deadZone             = 0.05; // AP 2/21
	public static final int    inertiaCounter	    = 25;   //JR 2-28 (loops after manual control before starting position control)
	
	// PID Constants
	public static final double proportional         = 1.2;  // AP 2/28 - Was 2.0JR 2-23 
	public static final double integral             = 0.0011;  // AP 2-21 (pickup not installed)
	public static final double derivative           = 1.0;  //TODO: Set Initial Value
	public static final double feedForward	        = 0.0;  //TODO: Set Initial Value
	public static final double VoltageRampRate      = 24.0; // AP 2/28 - Was 12 JR 2/23	, first idea by MD- V/s
	public static final double MaxOutputVoltage		= 4.0;  // AP 2/28 - Was 6 JR 2/23
	
	// Angles
	// NOTE: Assumption - from turret's POV facing forward being (pickup end of robot)
	// CCW = Counter Clock Wise
	// CW  = Clock Wise
	public static final double limitAngleCCW   		= -90.0; 	// JR 2-23
	public static final double aimLeft				=  -60.0;   // JR 3-1
	public static final double turretSafeLimitCCW 	=   -5.0; 	// AP 1-26
	public static final double center				=    0.0;   // AP 2-6
	public static final double neutral				=    0.0;   // AP 2-6
	public static final double turretSafeLimitCW 	=    5.0; 	// AP 1-26
	public static final double aimRight				=   60.0;   // JR 3-1
	public static final double limitAngleCW    		=  90.0; 	// JR 2-23
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   = 360;    //JR 2-19
	public static final int    maxEncoderCounts  = 4096;  //JR 2-19  
	
	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	