package edu.wpi.first.wpilibj.command;

import org.usfirst.frc330.Robot;

public class BBCommandGroup extends CommandGroup {
    
	boolean m_completed = false;
	
	void _initialize(){
		super._initialize();
		Robot.logger.println(this.getClass().getName() + " initialized", false);
		m_completed = false;
	}

	void _end(){
		super._end();
		Robot.logger.println(this.getClass().getName() + " ended", false);
		m_completed = true;
	}
	
	void _interrupted(){
		super._interrupted();
		Robot.logger.println(this.getClass().getName() + " interrupted", false);
	}
	
	public boolean isCompleted(){
		boolean complete = m_completed;
		m_completed = false;
		return complete;
	}
    
}
