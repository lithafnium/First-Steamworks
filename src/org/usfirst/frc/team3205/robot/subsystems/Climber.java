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
	private DigitalInput climbLimit;
	private PowerDistributionPanel pdp; 
	private Encoder climbDistance; 
    
	public Climber(){
		one = new Talon(RobotMap.CLIMBER_ONE); 
		two = new Talon(RobotMap.CLIMBER_TWO); 
		climbLimit = new DigitalInput(RobotMap.IS_UP); 
		pdp = new PowerDistributionPanel(); 
		climbDistance = new Encoder(4, 5, false, Encoder.EncodingType.k4X); 

	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void climb(){
		one.set(RobotMap.CLIMB_SPEED); 
		two.set(RobotMap.CLIMB_SPEED);
	}
	
	public double getDistance(){
		return climbDistance.get(); 
	}
	
	public void resetEncoder(){
		climbDistance.reset(); 
	}
	
	public boolean climbingLimit(){
		return climbLimit.get(); 
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

    	
    }
    
}

