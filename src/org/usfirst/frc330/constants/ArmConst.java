// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;

public final class ArmConst {
	
	private ArmConst(){}

	// Tolerance
	public static final double tolerance        		= 5.0;
	public static final double deadZone			 		= 0.05;  //AP 2-20
	public static final double gamepadDeadZone   		= 0.10;  //AP 2-27
	public static final double pickupTolerance	 		= 5.0; 	 //AP 2-6 (WAG)
	public static final double defaultTimeout	 		= 5.0;	 //JR 2-28
	public static final int    inertiaCounter	 		= 10;	 //JR 2-28 (loops after manual control before starting position control)
	
	// PID Constants
	public static final double proportional      		= 1.0;   // JR 2/23
	public static final double integral         		= 0.000; // JR 2/23
	public static final double derivative        		= 1.0;   // JR 2/23
	public static final double feedForward	     		= 0.0;   // JR 2/23
	public static final double VoltageRampRate   		= 24.0;  // JR 2/23
	public static final double MaxOutputVoltage  		= 9.0;   // JR 2/23
	
	// Angles
	public static final double limitLowerAngle   		=  2.0;  //AP 1-26
	public static final double pickupAngle		 		=  3.5;  //JR 2-28 
	public static final double lowBar            		=  3.0;  //AP 3-11
	public static final double armSafeLimit	     		= 50.0;  //AP 1-26
	public static final double neutral	         		= 55.0;  //AP 3-11    moved from 60 to 55 to protect stinger
	public static final double safeToDeployPortcullis   = 60.0;  // JR 2-27 
	public static final double shootAngleRamp    		= 90.0;  //AP 2-6 (WAG) - moved 95 to 90 (JED) 3/7
	public static final double shootAngleFloor   		= 90.0;  //AP 2-6 (WAG) moved 95 to 90 (JED) 3/7
	public static final double safeToDeployLowerClimber = 90.0;  //AP 3/10
	public static final double safeToDeployUpperClimber = 108.0; //AP 3/10 (could be reduced to 105
	public static final double limitUpperAngle   		= 113.0; //AP 1-26
	
	// Encoder Stuff
	public static final int    maxAngleDegrees   		= 72;    //JM 2-10
	public static final int    maxEncoderCounts  		= -4096; //AP 2-20
	public static final int    minQuadrant       		= 0;     //JM 2-10
	public static final int    maxQuadrant       		= 4;     //JM 2-10

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	