// Robot Turret Constants

package org.usfirst.frc330.constants;

public final class TurretPos {
	
	private TurretPos(){}

	// Tolerance
	public static final double tolerance       = 0.5;  //TODO: Initial Value
	
	// PID Constants
	public static final double proportional    = 0.06; //TODO: Set Initial Value
	public static final double integral        = 0.0;  //TODO: Set Initial Value
	public static final double derivitive      = 0.0;  //TODO: Set Initial Value
	public static final double feedForward	   = 2.0;  //TODO: Set Initial Value

	//Angles
	public static final double limitAngleCCW   		= -170.0; 	// AP 1-26
	public static final double turretSafeLimitCCW 	=   -5.0; 	// AP 1-26
	public static final double turretSafeLimitCW 	=    5.0; 	// AP 1-26
	public static final double limitAngleCW    		=  170.0; 	// AP 1-26
	
	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	