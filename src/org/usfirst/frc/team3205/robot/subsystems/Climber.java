package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController one; 
	private SpeedController two; 
	private DigitalInput climbLimitOne;
	private DigitalInput climbLimitTwo; 
	private PowerDistributionPanel pdp; 
	private Encoder climbDistance; 
	
	public double initialPosition; 
    // two limit switches 
	public Climber(){
		one = new Talon(RobotMap.CLIMBER_ONE); 
		two = new Talon(RobotMap.CLIMBER_TWO); 
		climbLimitOne = new DigitalInput(RobotMap.IS_UP_ONE); 
		climbLimitTwo = new DigitalInput(RobotMap.IS_UP_TWO); 

		
		pdp = new PowerDistributionPanel(); 
		climbDistance = new Encoder(RobotMap.CLIMBER_ENCODER_PORT_ONE, RobotMap.CLIMBER_ENCODER_PORT_TWO, false, Encoder.EncodingType.k4X); 

	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void climb(){
		
		two.set(RobotMap.CLIMB_SPEED);
		one.set(RobotMap.CLIMB_SPEED);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		two.set(0);
//		one.set(0);
//		two.set(RobotMap.CLIMB_SPEED);
		
	}
	
	public double getDistance(){
		return climbDistance.get(); 
	}
	
	public void resetEncoder(){
		//climbDistance.reset(); 
	}
	
	public boolean climbingLimit(){
		return climbLimitOne.get(); 
	}
	public boolean climbingLimitTwo(){
		return climbLimitTwo.get(); 
	}
	
	public void resetDrum(){
		one.set(RobotMap.CLIMB_SPEED);
		//two.set(RobotMap.CLIMB_SPEED); 
	}
	public void stop(){
		one.set(0.0); 
		two.set(0.0); 
	}
	public double getCurrent(){
		//return pdp.getCurrent(RobotMap.PDP_PORT); 
		return 0.0;
	}
	
	public boolean motorStall(){
		//return (pdp.getCurrent(RobotMap.PDP_PORT) > RobotMap.PDP_STALL);
		return true; 
	}
	public void updateSmartDashboard(){
    	//SmartDashboard.putNumber("Drive Encoder One", );
    	SmartDashboard.putBoolean("Hit climb", true);
    	SmartDashboard.putNumber("Current", getCurrent());
    	SmartDashboard.putNumber("Climber Encoder", getDistance());
    }
    
}

