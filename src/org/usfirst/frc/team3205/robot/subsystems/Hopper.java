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
	
	private SpeedController intake; 
	private Servo hopperOne;
	private Servo hoperTwo; 
	private DigitalInput flapOut; 


	
	public Hopper(){
		intake = new Talon(RobotMap.INTAKE); 
		hopperOne = new Servo(RobotMap.INTAKE_FLAP_OUT); 
<<<<<<< HEAD
		flapOut = new DigitalInput(RobotMap.IS_FLAP_OUT); 
=======
		flapOut = new DigitalInput(RobotMap.IF_FLAP_OUT); 
>>>>>>> 8f6f41ddc58ef836844b6c1be994522da710c14f
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intake(){
    	intake.set(RobotMap.SUCKER);
    }
    public boolean isFlapOut(){
    	return flapOut.get(); 
    }
    public void stop(){
    	intake.set(0.0);
    }
    
    public void dump(){
    	hopperOne.set(1);
    }
    
    public void retract(){
    	hopperOne.set(0);
    }
    
    public void updateSmartDashboard(){
    	//SmartDashboard.putNumber("Drive Encoder One", );
    	SmartDashboard.putBoolean("Drive Encoder Two", isFlapOut());
    }
    
}

