package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.Robot;
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
	private SpeedController topLeft; 
	private SpeedController topRight;
	private SpeedController bottomLeft; 
	private SpeedController bottomRight;
	private Ultrasonic ultraOne;
	private Ultrasonic ultraTwo; 
	
	
	public boolean backWards = false; 
	AnalogGyro gyroSensor; 

	Encoder one; 
	Encoder two; 
	RobotDrive robotDrive; 
	
	public DriveTrain(){
		topLeft = new Spark(RobotMap.TOP_LEFT_DRIVETRAIN_MOTOR); 
		topRight = new Talon(RobotMap.TOP_RIGHT_DRIVETRAIN_MOTOR); 
		bottomLeft = new Spark(RobotMap.BOTTOM_LEFT_DRIVETRAIN_MOTOR); 
		bottomRight = new Talon(RobotMap.BOTTOM_RIGHT_DRIVETRAIN_MOTOR);
		
		
		robotDrive = new RobotDrive(topLeft, bottomLeft,  topRight, bottomRight); 
		robotDrive.setSafetyEnabled(false);
		
//		ultraOne = new Ultrasonic(RobotMap.ULTRA_PULSE_OUTPUT_ONE, RobotMap.ULTRA_TRIGGER_INPUT_ONE); 
//		ultraTwo = new Ultrasonic(RobotMap.ULTRA_PULSE_OUTPUT_TWO, RobotMap.ULTRA_TRIGGER_INPUT_TWO); 
		
		one = new Encoder(RobotMap.DRIVE_BASE_ENCODER_ONE_PORT_ONE,RobotMap.DRIVE_BASE_ENCODER_ONE_PORT_TWO, false, Encoder.EncodingType.k4X);
		two = new Encoder(RobotMap.DRIVE_BASE_ENCODER_TWO_PORT_ONE,RobotMap.DRIVE_BASE_ENCODER_TWO_PORT_TWO, false, Encoder.EncodingType.k4X);
		
		//gyroSensor = new AnalogGyro(RobotMap.GYRO_PORT); 
    	//calibrateGyro();

	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new drive()); 
	}
	public void driveNow(Joystick left, Joystick right){
    	topLeft.setInverted(true);
    	bottomLeft.setInverted(true);
    	topRight.setInverted(true);
    	bottomRight.setInverted(true);
    	robotDrive.tankDrive(left, right);
    }
    
    public void backwards(Joystick left, Joystick right){
    	topLeft.setInverted(false);
    	bottomLeft.setInverted(false);
    	topRight.setInverted(false);
    	bottomRight.setInverted(false);
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
//		SmartDashboard.putNumber("Ultrasonic sensor one", getDistanceOne()); 
//		SmartDashboard.putNumber("Ultrasonic sensor two", getDistanceTwo()); 
		
		SmartDashboard.putNumber("Drive Encoder One - Right Side", getEncoderOne());
    	SmartDashboard.putNumber("Drive Encoder Two - Left Side", getEncoderTwo());
    	
    	SmartDashboard.putBoolean("Backwards:" , backWards); 
    	
    	
    	//SmartDashboard.putNumber("Angle", getAngle()); 

	}
	//
	
}
