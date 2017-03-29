package org.usfirst.frc.team3205.robot.subsystems;

import org.usfirst.frc.team3205.robot.RobotMap;
import org.usfirst.frc.team3205.robot.commands.pushGearOut;

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
	private Talon gearOut;
	//private Servo gearOut;

	public Gear(){
		gearIn = new DigitalInput(RobotMap.IS_GEAR_IN); 
		//gearOut = new Servo(RobotMap.PUSH_EM_OUT); 
		gearOut = new Talon(RobotMap.PUSH_EM_OUT); 


	}
	// when the limit switch is pressed pull down the limit switch 
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new pushGearOut()); 
	}

	public boolean isGearIn(){
		return gearIn.get(); 
	}

	public void pushOut(){ // this actually pulls in the gear 
		gearOut.set(0.60); 
		//gearOut.setAngle(45);
	}

	public void retract(){ // this actually puts out the gear 
		gearOut.set(-1.0);
		//gearOut.setAngle(10);
	}
//	public double getAngle(){
//		//return gearOut.getAngle(); 
//	}
	public void updateSmartDashboard(){
		SmartDashboard.putBoolean("Is Gear in", isGearIn());
	}
}

