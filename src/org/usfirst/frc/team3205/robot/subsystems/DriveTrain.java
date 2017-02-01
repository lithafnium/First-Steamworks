package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;
import org.usfirst.frc.team3205.robot.commands.drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	private SpeedController left; 
	private SpeedController right;
	private Ultrasonic ultra; 

	Encoder one; 
	Encoder two; 
	RobotDrive robotDrive; 
	
	public DriveTrain(){
		left = new Spark(RobotMap.LEFT_DRIVETRAIN_MOTOR); 
		right = new Spark(RobotMap.RIGHT_DRIVETRAIN_MOTOR); 
		robotDrive = new RobotDrive(left, right); 
		robotDrive.setSafetyEnabled(false);
		//ultra = new Ultrasonic(0, 0); 

	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new drive()); 
	}
	public void driveNow(Joystick left, Joystick right){
		robotDrive.tankDrive(left, right); 
	}
	
	public void stop(){
    	robotDrive.tankDrive(0.0,0.0);
    }
	
	public void driveSlow(){
		robotDrive.tankDrive(0.5, 0.5); 
	}
	
	public double getDistance(){
		return ultra.getRangeInches(); 
	}
	
	
	
}
