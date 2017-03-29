package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class dumpFlapClose extends Command {
	Timer timer; 
	boolean done = false; 
    public dumpFlapClose() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.box); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer(); 
    	timer.start(); 

    	Robot.box.closeFlap();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 3.0){
    		Robot.box.stopFlap(); 
    		done = true; 
    	}
//    	if(Robot.box.isFlapBack()){
//    		Robot.box.stopFlap();
//    		done = true; 
//    	}

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		Robot.box.stopFlap(); 

        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.box.stopFlap(); 

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.box.stopFlap(); 

    }
}
