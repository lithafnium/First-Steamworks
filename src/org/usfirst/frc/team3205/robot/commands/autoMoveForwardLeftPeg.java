package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveForwardLeftPeg extends Command {
	boolean done; 
	Timer timer; 
    public autoMoveForwardLeftPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    	requires(Robot.gear);
    	timer = new Timer(); 

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start(); 
    	Robot.gear.pushOut(); 
    	Robot.driveTrain.driveCertainAmounts(-0.575, -0.4115);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(timer.get() > 3.2){
    		Robot.driveTrain.stop(); 
    		done = true; 

    	}
    	
//    	if(Robot.gear.isGearIn()){
//    		Robot.gear.retract(); 
//
//    		done = true; 
//    	}
//    	if(Robot.driveTrain.getEncoderTwo() >= 1300){
//    		Robot.driveTrain.stop(); 
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.driveTrain.stop(); 

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.driveTrain.stop(); 

    }
}
