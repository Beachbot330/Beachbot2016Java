package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.communication.HALControlWord;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class BBIterativeRobot extends IterativeRobot {
	
	protected boolean m_disconnectedInitialized;
	
	public BBIterativeRobot() {
		super();
		m_disconnectedInitialized = false;
	}

	@Override
	public void startCompetition() {
		UsageReporting.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Iterative);

		robotInit();

		// Tell the DS that the robot is ready to be enabled
		FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();

		// loop forever, calling the appropriate mode-dependent function
		LiveWindow.setEnabled(false);
		while (true) {
			// Call the appropriate function depending upon the current robot mode
			if (isDisabled()) {
				// call DisabledInit() if we are now just entering disabled mode from
				// either a different mode or from power-on
				if (!m_disabledInitialized) {
					LiveWindow.setEnabled(false);
					disabledInit();
					m_disabledInitialized = true;
					// reset the initialization flags for the other modes
					m_autonomousInitialized = false;
					m_teleopInitialized = false;
					m_testInitialized = false;
					m_disconnectedInitialized = false;
				}
				if (nextPeriodReady()) {
					FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramDisabled();
					disabledPeriodic();
				}
			} else if (isTest()) {
				// call TestInit() if we are now just entering test mode from either
				// a different mode or from power-on
				if (!m_testInitialized) {
					LiveWindow.setEnabled(true);
					testInit();
					m_testInitialized = true;
					m_autonomousInitialized = false;
					m_teleopInitialized = false;
					m_disabledInitialized = false;
					m_disconnectedInitialized = false;
				}
				if (nextPeriodReady()) {
					FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTest();
					testPeriodic();
				}
			} else if (isAutonomous()) {
				// call Autonomous_Init() if this is the first time
				// we've entered autonomous_mode
				if (!m_autonomousInitialized) {
					LiveWindow.setEnabled(false);
					// KBS NOTE: old code reset all PWMs and relays to "safe values"
					// whenever entering autonomous mode, before calling
					// "Autonomous_Init()"
					autonomousInit();
					m_autonomousInitialized = true;
					m_testInitialized = false;
					m_teleopInitialized = false;
					m_disabledInitialized = false;
					m_disconnectedInitialized = false;
				}
				if (nextPeriodReady()) {
					FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramAutonomous();
					autonomousPeriodic();
				}
			} else if (isEnabled()){
				// call Teleop_Init() if this is the first time
				// we've entered teleop_mode
				if (!m_teleopInitialized) {
					LiveWindow.setEnabled(false);
					teleopInit();
					m_teleopInitialized = true;
					m_testInitialized = false;
					m_autonomousInitialized = false;
					m_disabledInitialized = false;
					m_disconnectedInitialized = false;
				}
				if (nextPeriodReady()) {
					FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTeleop();
					teleopPeriodic();
				}
				else {
					// call Disconnected_Init() if this is the first time
					// we've entered teleop_mode
					if (!m_disconnectedInitialized) {
						LiveWindow.setEnabled(false);
						disconnectedInit();
						m_teleopInitialized = false;
						m_testInitialized = false;
						m_autonomousInitialized = false;
						m_disabledInitialized = false;
						m_disconnectedInitialized = true;
					}
					FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramDisabled();
					disconnectedPeriodic();
					Timer.delay(0.02);
				}
			}
			m_ds.waitForData();
		}
	}

	@Override
	public boolean isDisabled() {
	    HALControlWord controlWord = FRCNetworkCommunicationsLibrary.HALGetControlWord();
	    return !controlWord.getEnabled() && controlWord.getDSAttached();
	}
	
	public boolean isDisconnected() {
	    HALControlWord controlWord = FRCNetworkCommunicationsLibrary.HALGetControlWord();
	    return !controlWord.getDSAttached();
	}
	
	  /**
	   * Initialization code for disconnected mode should go here.
	   *
	   * Users should override this method for initialization code which will be
	   * called each time the robot enters disconnected mode.
	   */
	  public void disconnectedInit() {
	    System.out.println("Default BBIterativeRobot.disconnectedInit() method... Overload me!");
	  }
	  
	  private boolean dipFirstRun = true;

	  /**
	   * Periodic code for disconnected mode should go here.
	   *
	   * Users should override this method for code which will be called
	   * periodically at a regular rate while the robot is in disconnected mode.
	   */
	  public void disconnectedPeriodic() {
	    if (dipFirstRun) {
	      System.out.println("Default BBIterativeRobot.disconnectedPeriodic() method... Overload me!");
	      dipFirstRun = false;
	    }
	    Timer.delay(0.001);
	  }
	
}
