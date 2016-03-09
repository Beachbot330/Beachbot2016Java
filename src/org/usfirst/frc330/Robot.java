// RobotBuilder Version: 2.0BB
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc330.commands.*;
import org.usfirst.frc330.commands.autocommands.*;
import org.usfirst.frc330.commands.breachDefenseCommands.*;
import org.usfirst.frc330.commands.driveAndShoot.*;
import org.usfirst.frc330.subsystems.*;
import org.usfirst.frc330.util.CSVLogger;
import org.usfirst.frc330.util.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser autoProgram;
    static SendableChooser autoStartPosition;
    static SendableChooser autoObstacle;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Frills frills;
    public static Chassis chassis;
    public static Arm arm;
    public static Pickup pickup;
    public static Turret turret;
    public static Climber climber;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static Logger logger;
    public static CSVLogger csvLogger;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	logger = new Logger();
        csvLogger = new CSVLogger();
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frills = new Frills();
        chassis = new Chassis();
        arm = new Arm();
        pickup = new Pickup();
        turret = new Turret();
        climber = new Climber();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
    
        //Populate the auto modes and options
        autoProgram = new SendableChooser();
        autoProgram.addObject("Do Nothing", new DoNothing());
        autoProgram.addObject("Drive to Defense", new DriveToDefense());
        autoProgram.addObject("Breach Defense", new BreachDefense());
//        autoProgram.addObject("Pure Awesome", new FullyLoadedAuto());
//        autoProgram.addObject("Test Command", new TestCommand());
        autoProgram.addObject("Spybox Shooter", new SpyboxShoot());
        autoProgram.addObject("Low Bar Shoot", new LowBar());
        
        autoStartPosition = new SendableChooser();
        autoStartPosition.addObject("Pos 1 - Low Bar", new PositionOne());
        autoStartPosition.addObject("Position 2", new PositionTwo());
        autoStartPosition.addObject("Position 3", new PositionThree());
        autoStartPosition.addObject("Position 4", new PositionFour());
        autoStartPosition.addObject("Position 5", new PositionFive());
        
        autoObstacle = new SendableChooser();
//        autoObstacle.addObject("Drawbridge", new Drawbridge());
        autoObstacle.addObject("Low Bar", new LowBar());
        autoObstacle.addObject("Moat", new Moat());
//        autoObstacle.addObject("Portcullis", new Portcullis());
//        autoObstacle.addObject("Ramparts", new Ramparts());
//        autoObstacle.addObject("Rock Wall", new RockWall());
        autoObstacle.addObject("Rough Terrain", new RoughTerrain());
//        autoObstacle.addObject("Sally Port", new SallyPort());
//        autoObstacle.addObject("Teeter Totter", new TeeterTotter());
        
        /* Set to a default value */
        autoObstacle.addObject("Rock Wall", new RockWall());
        
        SmartDashboard.putData("Auto Program", autoProgram);
        SmartDashboard.putData("Robot Start Position", autoStartPosition);
        SmartDashboard.putData("Obstacle", autoObstacle);
        
        csvLogger.writeHeader();
        logger.println("Robot Init");
        
        if (isPracticeRobot())
        	logger.println("Practice Robot Detected");
        else
        	logger.println("Competition Robot Detected");
    
        Command beep = new BuzzerBeepTimed(0.75);
        beep.start();
        
    }
    
    /*****************************************************************
     * 
     * OTHER METHODS (Don't know where to put them?? Put them here)
     * 
     ****************************************************************/

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	logger.println("Disabled Init");
    	logger.updateDate();
    	new DisabledCheckSensors().start();
    	new LockClimber().start();
    }

    
    public void disabledPeriodic() {
    	Scheduler.getInstance().run();
    	chassis.calcXY();
    	csvLogger.writeData();
    }

    public static boolean isPracticeRobot() {
        return (frills.isPracticeRobot());
    }

    public static Command getObstacle() {
    	return (Command)autoObstacle.getSelected();
    }
    
    public static Command getStartPosition() {
    	return (Command)autoStartPosition.getSelected();
    }
    
    /*****************************************************************
     * 
     * AUTONOMOUS METHODS
     * 
     ****************************************************************/
    
    /**
     * This function is responsible for initialization for autonomous
     * mode 
     * 
     * 1. Log entry for Autonomous Initialization
     * 2. Reset position
     * 3. Autonomous command
     */
    public void autonomousInit() {
    	
    	logger.println("Autonomous Init");
    	logger.updateDate();
    	
    	Robot.chassis.resetPosition();
    	
    	autonomousCommand = (Command) autoProgram.getSelected();
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        	logger.println("Running Auto: " + autonomousCommand.getName());
        	logger.println("Start Position: " + ((Command) autoStartPosition.getSelected()).getName());
        	logger.println("Obstacle: " + ((Command) autoObstacle.getSelected()).getName());
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcXY();
        chassis.pidDriveAuto();
        arm.monitorArm();
    	csvLogger.writeData();
    }

    /*****************************************************************
     * 
     * TELEOP METHODS
     * 
     ****************************************************************/
    
    /**
     * This function is responsible for initialization for teleop
     * mode. 
     * 
     * 1. Log entry for Teleop Initialization
     * 2. Sound the buzzer
     */
    public void teleopInit() {
    	
    	logger.println("Teleop Init");
    	logger.updateDate();
    	Command beep= new BuzzerBeepTimed(1.25);
    	beep.start();
    	
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
        chassis.calcXY();
        chassis.pidDrive();
        arm.monitorArm();
    	csvLogger.writeData();
    }
    
    /*****************************************************************
     * 
     * TEST METHODS
     * 
     ****************************************************************/
    
    /**
     * This function is responsible for initialization for test mode. 
     * 
     * 1. Log entry for Test Initialization
     */
    public void testInit() {
        logger.println("Test Init");
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
        chassis.calcXY();
        arm.monitorArm();
        csvLogger.writeData();
    }
}
