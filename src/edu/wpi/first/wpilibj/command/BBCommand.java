/**
 * 
 */
package edu.wpi.first.wpilibj.command;

import org.usfirst.frc330.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author allenpeters
 *
 */
public abstract class BBCommand extends Command {
	
	boolean m_initialized = false;
	boolean m_completed = false;
	
	@Override
	void _initialize(){
		super._initialize();
		Robot.logger.println(this.getClass().getName() + " initialized", false);
		m_initialized = true;
		m_completed = false;
	}

	@Override
	void _end(){
		super._end();
		if(this.isTimedOut())
			Robot.logger.println(this.getClass().getName() + " timed out", false);
		else
			Robot.logger.println(this.getClass().getName() + " ended", false);
		m_initialized = false;
		m_completed = true;
	}
	
	@Override
	void _interrupted(){
		super._interrupted();
		Robot.logger.println(this.getClass().getName() + " interrupted", false);
		m_initialized = false;
		m_completed = true;
	}
	
	public boolean isInitialized() {
		return m_initialized;
	}
	
	public boolean isCompleted(){
		boolean complete = m_completed;
		m_completed = false;
		return complete;
	}
}
