package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;
import org.usfirst.frc.team3205.robot.commands.drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	private Ultrasonic ultraOne;
	private Ultrasonic ultraTwo; 
	
	AnalogGyro gyroSensor; 

	Encoder one; 
	Encoder two; 
	RobotDrive robotDrive; 
	
	public DriveTrain(){
		left = new Talon(RobotMap.LEFT_DRIVETRAIN_MOTOR); 
		right = new Talon(RobotMap.RIGHT_DRIVETRAIN_MOTOR); 
		
		robotDrive = new RobotDrive(left, right); 
		robotDrive.setSafetyEnabled(false);
		
		ultraOne = new Ultrasonic(RobotMap.ULTRA_PULSE_OUTPUT_ONE, RobotMap.ULTRA_TRIGGER_INPUT_ONE); 
		ultraTwo = new Ultrasonic(RobotMap.ULTRA_PULSE_OUTPUT_TWO, RobotMap.ULTRA_TRIGGER_INPUT_TWO); 
		
		one = new Encoder(0,1, false, Encoder.EncodingType.k4X);
		two = new Encoder(2,3, false, Encoder.EncodingType.k4X);
		
		gyroSensor = new AnalogGyro(RobotMap.GYRO_PORT); 
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new drive()); 
	}
	public void driveNow(Joystick left, Joystick right){
		robotDrive.tankDrive(left, right); 
	}
	
	public void driveCertainAmounts(double left, double right){
		robotDrive.tankDrive(left, right); 
	}
	
	public void stop(){
    	robotDrive.tankDrive(0.0,0.0);
    }
	
	public void driveSlow(){
		robotDrive.tankDrive(0.5, 0.5); 
	}
	
	public double getDistanceOne(){
		return ultraOne.getRangeInches(); 
	}
	
	public double getDistanceTwo(){
		return ultraTwo.getRangeInches(); 
	}
	
	public void reset1stEncoder(){
    	one.reset();
    }
    
    public void reset2ndEncoder(){
    	two.reset();
    }
    
    public double getEncoderOne(){
    	return one.getDistance();
    }
   
    public double getEncoderTwo(){
    	return two.getDistance();
    }
	
    public void calibrateGyro(){
    	gyroSensor.calibrate(); 
    }
    public double getAngle(){
    	return gyroSensor.getAngle(); 
    }
    
    public void resetGyro(){
    	gyroSensor.reset(); 
    }
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Ultrasonic sensor one", getDistanceOne()); 
		SmartDashboard.putNumber("Ultrasonic sensor two", getDistanceTwo()); 
		
		SmartDashboard.putNumber("Drive Encoder One", getEncoderOne());
    	SmartDashboard.putNumber("Drive Encoder Two", getEncoderTwo());
    	
    	SmartDashboard.putNumber("Angle", getAngle()); 

	}
	
	
}
