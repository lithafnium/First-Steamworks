package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
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

    
	public Climber(){
		one = new Talon(RobotMap.CLIMBER_ONE); 
		two = new Talon(RobotMap.CLIMBER_TWO); 
		climbLimit = new DigitalInput(RobotMap.IS_UP); 
	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void climb(){
		one.set(RobotMap.CLIMB_SPEED); 
		two.set(RobotMap.CLIMB_SPEED);
	}
	
	public boolean climbingLimit(){
		return climbLimit.get(); 
	}
	public void stop(){
		one.set(0.0); 
		two.set(0.0); 
	}
	
	public void updateSmartDashboard(){
    	//SmartDashboard.putNumber("Drive Encoder One", );
    	SmartDashboard.putBoolean("Drive Encoder Two", climbingLimit());
    }
    
}

