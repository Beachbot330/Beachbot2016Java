package edu.wpi.first.wpilibj.command;

import org.usfirst.frc330.Robot;
import org.usfirst.frc330.util.Logger.Severity;

public class BBCommandGroup extends CommandGroup {
    
	boolean m_completed = false;
	
	@Override
	void _initialize(){
		super._initialize();
		Robot.logger.println(this.getClass().getName() + " initialized", false, Severity.COMMAND);
		m_completed = false;
	}

	@Override
	void _end(){
		super._end();
		Robot.logger.println(this.getClass().getName() + " ended", false, Severity.COMMAND);
		m_completed = true;
	}
	
	@Override
	void _interrupted(){
		super._interrupted();
		Robot.logger.println(this.getClass().getName() + " interrupted", false, Severity.WARNING);
	}
	
	public boolean isCompleted(){
		boolean complete = m_completed;
		m_completed = false;
		return complete;
	}
    
}
