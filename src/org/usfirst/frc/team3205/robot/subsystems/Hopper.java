package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;
import org.usfirst.frc.team3205.robot.commands.intakeFlapClose;

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
	//private Servo intakeFlap; // linear actuaor 
	private SpeedController intakeFlap; 
	private SpeedController outtakeFlap; // winds back the outtake chute 
	private DigitalInput isCoiled; 
	
	//private DigitalInput flapBack; 
	// limit switch for the outtake flap 



	// wind up the outtake flap slowly 
	// let out the flap ASAP 
	public Hopper(){ 
		coil = new Talon(RobotMap.COIL_MOTOR); 
		outtakeFlap = new Talon(RobotMap.FLAP_MOTOR); // outtake flap - dump chute 
		//intakeFlap = new Servo(RobotMap.INTAKE_FLAP); 
		intakeFlap = new Talon(RobotMap.INTAKE_FLAP) ;

		isCoiled = new DigitalInput(RobotMap.IS_COILED); 
		//===========================================================================
		//flapBack = new DigitalInput(RobotMap.IS_OUTTAKE_FLAP_BACK);  //UNCOMMENT THIS 
		//openFlap = new Encoder(23,24, false, Encoder.EncodingType.k4X); UNCOMMENT THIS 
		//============================================================================

	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new intakeFlapClose()); 
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
    // ========================

//    public boolean isFlapBack(){
//    	return flapBack.get(); 
//    } 
    // ========================
    public void stop(){
    	coil.set(0.0);
    	
    }
    public void openFlapSlow(){
    	outtakeFlap.set(0.1); 
    }
    // outake flap
    public void openFlap(){
    	outtakeFlap.set(0.3);
    }
    // outtake flap
    public void closeFlap(){
    	outtakeFlap.set(-0.3); 
    }
    // outtake flap 
    public void stopFlap(){
    	outtakeFlap.set(0.0); 
    }
    
    public void openIntakeFlap(){
    	intakeFlap.set(1.0); 
    }
//    
    public void closeIntakeFlap(){
    	intakeFlap.set(-1.0);
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

