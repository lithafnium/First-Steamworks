
package org.usfirst.frc.team3205.robot;

//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//
//import org.opencv.core.Mat;
//import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;





//import edu.wpi.first.wpilibj.vision.CameraServer;








import org.usfirst.frc.team3205.robot.commands.autoMoveCenterPegGroup;
import org.usfirst.frc.team3205.robot.commands.autoMoveLeftPegGroup;
import org.usfirst.frc.team3205.robot.commands.autoMoveRightPegGroup;
import org.usfirst.frc.team3205.robot.commands.climberBooleanReset;
import org.usfirst.frc.team3205.robot.commands.nothing;
//import org.usfirst.frc.team3205.robot.commands.resetArmEncoder;
import org.usfirst.frc.team3205.robot.commands.resetClimberEncoders;
import org.usfirst.frc.team3205.robot.commands.resetDriveTrainEncoders;
import org.usfirst.frc.team3205.robot.commands.visionAutonomous;
import org.usfirst.frc.team3205.robot.subsystems.Climber;
//import org.usfirst.frc.team3205.robot.commands.ExampleCommand;
import org.usfirst.frc.team3205.robot.subsystems.DriveTrain;
//import org.usfirst.frc.team3205.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3205.robot.subsystems.Gear;
import org.usfirst.frc.team3205.robot.subsystems.Hopper;
import org.usfirst.frc.team3205.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public static DriveTrain driveTrain;
	public static Gear gear;
	public static Climber climb;
	public static Hopper box;
	public static Vision vision;
	Thread visionThread; 

	Command autonomousCommand;
	//SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser chooser; 
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("testing 123");
		driveTrain = new DriveTrain();
		gear = new Gear(); 
		climb = new Climber(); 
		box = new Hopper(); 
		vision = new Vision();
		oi = new OI();
		chooser = new SendableChooser();
       // chooser.addObject("Gear", new visionAutonomous());
        chooser.addObject("Gear Left Peg", new autoMoveLeftPegGroup() );
        chooser.addObject("Gear right Peg", new autoMoveRightPegGroup()); 
        chooser.addObject("Gear Center Peg", new autoMoveCenterPegGroup() );

        chooser.addObject("Nothing", new nothing()); 
        SmartDashboard.putData("Auto mode", chooser);
        
        SmartDashboard.putData("Reset climber Encoder", new resetClimberEncoders());
        SmartDashboard.putData("Reset DriveTrain Encoders", new resetDriveTrainEncoders());
        SmartDashboard.putData("Reset Climber Limitswitch", new climberBooleanReset());


				
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", chooser);

		updateSmartDashboard(); 

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		updateSmartDashboard(); 
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		updateSmartDashboard();

		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		updateSmartDashboard();

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		updateSmartDashboard();

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	public void updateSmartDashboard(){
		box.updateSmartDashboard();
		gear.updateSmartDashboard();
		climb.updateSmartDashboard(); 
		driveTrain.updateSmartDashboard();
		vision.updateSmartDashboard();

	}
}
