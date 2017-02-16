package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
	
	private SpeedController coil; // maybe take out
	private Servo intakeFlap; 
//	private Servo hopperOne;
//	private Servo hopperTwo; 
	private SpeedController flap; 
	private DigitalInput isCoiled; 
	//Encoder openFlap; 



	
	public Hopper(){
		coil = new Talon(RobotMap.COIL_MOTOR); 
		flap = new Talon(RobotMap.FLAP_MOTOR); 
		intakeFlap = new Servo(RobotMap.INTAKE_FLAP); 
//		hopperOne = new Servo(RobotMap.FLAP_OUT_ONE); 
//		hopperTwo = new Servo(RobotMap.FLAP_OUT_TWO);
		isCoiled = new DigitalInput(RobotMap.IS_COILED); 
		//openFlap = new Encoder(23,24, false, Encoder.EncodingType.k4X);


	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void coil(){
    	coil.set(RobotMap.COIL);
    	
    }
    public void unCoil(){
    	coil.set(-RobotMap.COIL); 
    }
    
    public boolean isCoiled(){
    	return isCoiled.get(); 
    }
    
    public void stop(){
    	coil.set(0.0);
    	
    }
    // outake flap
    public void openFlap(){
    	flap.set(0.5);
    }
    // outtake flap
    public void closeFlap(){
    	flap.set(-0.5); 
    }
    // outtake flap 
    public void stopFlap(){
    	flap.set(0.0); 
    }
    
    public void openIntakeFlap(){
    	intakeFlap.set(1.0); 
    }
//    
    public void closeIntakeFlap(){
    	intakeFlap.set(0.0);
    }
    
//    public void resetEncoder(){
//    	openFlap.reset(); 
//    }
//    public double getDistance(){
//    	return openFlap.getDistance(); 
//    }
//    public double getServoOne(){
//    	return hopperOne.getPosition(); 
//    }
//    public double getServoTwo(){
//    	return hopperTwo.getPosition(); 
//    }
    
    
    public void updateSmartDashboard(){
    	//SmartDashboard.putNumber("Drive Encoder One", );
    	SmartDashboard.putBoolean("Coiled", isCoiled());
    	//SmartDashboard.putNumber("Flap distance", getDistance());
//    	SmartDashboard.putNumber("Servo One", getServoOne()); 
//    	SmartDashboard.putNumber("Servo Two", getServoTwo()); 
    }
    
}

