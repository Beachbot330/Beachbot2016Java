// Robot ARM Constants

package org.usfirst.frc330.constants;

import org.usfirst.frc330.Robot;

public final class ArmPos {
	
	private ArmPos(){}

	// Tolerance
	public static final double tolerance         = 0.5;  //TODO: Set Initial Value
	public static final double deadZone			 = 0.2;	 //AP 1-26
	
	// PID Constants
	public static final double proportional      = 0.06; //TODO: Set Initial Value
	public static final double integral          = 0.0;  //TODO: Set Initial Value
	public static final double derivative        = 0.0;  //TODO: Set Initial Value
	public static final double feedForward	     = 2.0;  //TODO: Set Initial Value
	
	// Angles
	public static final double limitLowerAngle   = 0.0;   //AP 1-26
	public static final double armSafeLimit	     = 45.0;  //AP 1-26
	public static final double limitUpperAngle   = 90.0;  //AP 1-26

	//Current
	//public static final double currentLowerLimit = -50;
	//public static final double currentUpperLimit = 50;
}	