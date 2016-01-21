// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;

public final class ArmPos {
	
	private ArmPos(){}

	// Tolerance
	public static final double tolerance         = 0.5;
	
	// PID Constants
	public static final double proportional      = 0.06;  // WARNING: Changing this may cause self-destruct in auto
	public static final double integral          = 0.0;
	public static final double derivative        = 0.0;
	public static final double feedForward	     = 2.0;
	
	// Analog Pot Limits- Defaults
	public static final double limitDown         = 1.0;  // Fully down position
	public static final double limitUp           = 5.0;  // Fully up position
	
	// Calibration Angles (Relative to Mast)
	//public static final double frontCalAngle = 			49.0;  //Angle from the mast
	//public static final double verticalCalAngle = 		180.0; //Angle from the mast
	//public static final double rearCalAngle  = 			311.0; //Angle from the mast
	
	// Angles
	public static final double limitLowerAngle   = -10.0;
	public static final double limitUpperAngle   = 90.0;

	//Lengths
	//public static final double armLength       = 44.75; 	//inches  AP 3-7
	public static final double armLength         = 44.75 + 2.0;
	
	//Current
	public static final double currentLowerLimit = -50;
	public static final double currentUpperLimit = 50;
}	