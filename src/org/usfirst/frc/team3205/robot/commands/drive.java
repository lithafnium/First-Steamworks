package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.OI;
import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class drive extends Command {

	public drive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain); 
		requires(Robot.gear); 
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.gear.isGearIn() || OI.controller.getRawButton(7)){
			//Robot.driveTrain.stop(); 
			Robot.gear.retract();
		}
		if(OI.controller.getRawButton(5)){
			Robot.gear.pushOut();
		}
		
		if(OI.controller.getRawButton(9)){
			Robot.box.openIntakeFlap();
		}
		if(OI.controller.getRawButton(10)){
			Robot.box.closeIntakeFlap();
		}
		
//		if(Robot.gear.isGearIn() || OI.xbox.getRawButton(3)){
//			//Robot.driveTrain.stop(); 
//			Robot.gear.retract();
//		}
//		if(OI.xbox.getRawButton(4)){
//			Robot.gear.pushOut();
//		}
//		
//		if(OI.xbox.getRawButton(6)){
//			Robot.box.openIntakeFlap();
//		}
//		if(OI.xbox.getRawButton(7)){
//			Robot.box.closeIntakeFlap();
//		}
		if(Robot.climb.climbingLimit()){
    		//RobotMap.climberHit = true; 
    		

    		//done = true; 
    	}
		
		Robot.driveTrain.driveNow(OI.left, OI.right);
		//Robot.driveTrain.backwards(OI.right, OI.left); 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
