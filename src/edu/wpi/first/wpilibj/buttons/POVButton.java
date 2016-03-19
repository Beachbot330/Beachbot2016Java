package edu.wpi.first.wpilibj.buttons;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class POVButton extends Button {
    
	private Joystick joy;
	private int pov;
	private int dir;
	
	/**
	 * Create a trigger for choosing one of two commands based on joystick buttons
	 * @param joy Joystick to use
	 * @param pov POV to use
	 */
	public POVButton(Joystick joy, int pov, int dir) {
		this.joy = joy;
		this.pov = pov;
		this.dir = dir;
	}	
    
    public boolean get() {
        return joy.getPOV(pov) == dir && joy.getPOVCount()>0;
    }
}
