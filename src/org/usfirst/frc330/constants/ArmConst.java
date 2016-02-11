// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;

public final class ArmConst {
	
	private ArmConst(){}

	// Tolerance
	public static final double tolerance         = 0.5;  //TODO: Set Initial Value
	public static final double deadZone			 = 0.2;	 //AP 1-26
	public static final double pickupTolerance	 = 5.0;  //AP 2-6 (WAG)
	
	// PID Constants
	public static final double proportional      = 0.06; //TODO: Set Initial Value
	public static final double integral          = 0.0;  //TODO: Set Initial Value
	public static final double derivative        = 0.0;  //TODO: Set Initial Value
	public static final double feedForward	     = 2.0;  //TODO: Set Initial Value
	
	// Angles
	public static final double limitLowerAngle   =  0.0;  //AP 1-26
	public static final double pickupAngle		 =  0.0;  //AP 2-6 (WAG)
	public static final double armSafeLimit	     = 45.0;  //AP 1-26
	public static final double neutral	         = 60.0;  //AP 2-6
	public static final double shootAngleRamp    = 90.0;  //AP 2-6 (WAG)
	public static final double shootAngleFloor   = 90.0;  //AP 2-6 (WAG)
	public static final double limitUpperAngle   = 90.0;  //AP 1-26
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   = 72;    //JM 2-10
	public static final int    maxEncoderCounts  = 4096;  //JM 2-10  
	public static final int    minQuadrant       = 0;     //JM 2-10
	public static final int    maxQuadrant       = 4;     //JM 2-10

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	