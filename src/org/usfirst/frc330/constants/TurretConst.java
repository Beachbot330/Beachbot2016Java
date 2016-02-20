// Robot Turret Constants

package org.usfirst.frc330.constants;

public final class TurretConst {
	
	private TurretConst(){}

	// Tolerance
	public static final double tolerance            = 0.5;  //TODO: Initial Value
	public static final double pickupTolerance	    = 5.0;  //AP 2-6 (WAG)
	
	// PID Constants
	public static final double proportional         = 0.2;  //TODO: Set Initial Value
	public static final double integral             = 0.0;  //TODO: Set Initial Value
	public static final double derivative           = 0.0;  //TODO: Set Initial Value
	public static final double feedForward	        = 0.0;  //TODO: Set Initial Value

	// Angles
	// NOTE: Assumption - from turret's POV facing forward being (pickup end of robot)
	// CCW = Counter Clock Wise
	// CW  = Clock Wise
	public static final double limitAngleCCW   		= -170.0; 	// AP 1-26
	public static final double aimLeft				=  -90.0;   // AP 2-6
	public static final double turretSafeLimitCCW 	=   -5.0; 	// AP 1-26
	public static final double center				=    0.0;   // AP 2-6
	public static final double neutral				=    0.0;   // AP 2-6
	public static final double turretSafeLimitCW 	=    5.0; 	// AP 1-26
	public static final double aimRight				=   90.0;   // AP 2-6
	public static final double limitAngleCW    		=  170.0; 	// AP 1-26
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   = 360;    //JR 2-19
	public static final int    maxEncoderCounts  = 4096;  //JR 2-19  
	
	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	