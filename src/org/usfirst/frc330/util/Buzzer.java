package org.usfirst.frc330.util;

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Class for logging text files.
 * 
 * Logs to a USB stick if connected, and to the roboRIO flash if USB isn't available.
 * Timestamps all log entries.
 */
public class Buzzer {
	
	double endTime=0;
	boolean enabled=false;
	/**
	 * Constructor
	 */
	public Buzzer() {
	}
	
	/**
	 * Starts a timed buzzer for a given duration. Will extend a current
	 * buzzer, but will never shorten one.
	 * 
	 * @param durationInSeconds The duration of the buzzer in seconds
	 */
	public void enable(double durationInSeconds) {
		if (System.currentTimeMillis() + 1000 * durationInSeconds > endTime){
			Robot.frills.buzzerOn();
			endTime = System.currentTimeMillis() + 1000 * durationInSeconds;
			enabled = true;
		}
	}
	
	/**
	 * Periodic function that checks if it is time to turn off the buzzer
	 * 
	 * @param durationInSeconds The duration of the buzzer in seconds
	 */
	public void update() {
		if (enabled){
			if(System.currentTimeMillis() > endTime){
				Robot.frills.buzzerOff();
				enabled = false;
			}
		}
	}
	
	/**
	 * Disables the buzzer
	 */
	public void disable() {
		Robot.frills.buzzerOff();
		enabled = false;
	}
}
