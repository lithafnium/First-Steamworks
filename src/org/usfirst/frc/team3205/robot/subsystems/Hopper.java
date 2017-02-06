package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Hopper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private SpeedController intake; // maybe take out
	private Servo hopperOne;
	private Servo hopperTwo; 
	private DigitalInput isCoiled; 


	
	public Hopper(){
		intake = new Talon(RobotMap.COIL_MOTOR); 
		hopperOne = new Servo(RobotMap.FLAP_OUT_ONE); 
		hopperTwo = new Servo(RobotMap.FLAP_OUT_TWO);
		isCoiled = new DigitalInput(RobotMap.IS_COILED); 

	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void coil(){
    	intake.set(RobotMap.COIL);
    	
    }
    public boolean isCoiled(){
    	return isCoiled.get(); 
    }
    public void stop(){
    	intake.set(0.0);
    	
    }
    
    public void openFlap(){
    	hopperOne.set(1);
    	hopperTwo.set(1); 
    }
    
    public void closeFlap(){
    	hopperOne.set(0);
    	hopperTwo.set(0); 
    }
    public double getServoOne(){
    	return hopperOne.getPosition(); 
    }
    public double getServoTwo(){
    	return hopperTwo.getPosition(); 
    }
    
    
    public void updateSmartDashboard(){
    	//SmartDashboard.putNumber("Drive Encoder One", );
    	SmartDashboard.putBoolean("Coiled", isCoiled());
    	SmartDashboard.putNumber("Servo One", getServoOne()); 
    	SmartDashboard.putNumber("Servo Two", getServoTwo()); 
    }
    
}

