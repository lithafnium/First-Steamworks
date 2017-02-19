package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gear extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private DigitalInput gearIn;
	//private Talon gearOut;
	private Servo gearOut;

	public Gear(){
		gearIn = new DigitalInput(RobotMap.IS_GEAR_IN); 
		gearOut = new Servo(RobotMap.PUSH_EM_OUT); 
		//gearOut = new Talon(RobotMap.PUSH_EM_OUT); 


	}
	// when the limit switch is pressed pull down the limit switch 
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public boolean isGearIn(){
		return gearIn.get(); 
	}

	public void pushOut(){
		//gearOut.set(.75); 
		gearOut.setAngle(90);
	}

	public void retract(){
		//gearOut.set(.25);
		gearOut.setAngle(0);
	}
	public double getAngle(){
		return gearOut.get(); 
	}
	public void updateSmartDashboard(){
		SmartDashboard.putBoolean("Is Gear in", isGearIn());
		SmartDashboard.putNumber("Linear Actuator Angle", getAngle()); 
	}
}

