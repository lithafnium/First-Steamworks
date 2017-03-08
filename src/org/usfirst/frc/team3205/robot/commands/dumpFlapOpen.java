package org.usfirst.frc.team3205.robot.commands;

//import javax.swing.Timer;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class dumpFlapOpen extends Command {
	//double startDistance; 
	Timer timer; 
	boolean done = false; 
    public dumpFlapOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.box); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//startDistance = Robot.box.getDistance(); 
    	timer = new Timer(); 
    	timer.start(); 
    	Robot.box.openFlap();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(timer.get() > 0.1){ 
    		Robot.box.stopFlap(); 
    		done = true; 
    	}
//    	if(Robot.box.getDistance() - startDistance >= 300){
//    		Robot.box.stopFlap();
//    		done = true; 
//    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	timer.reset(); 
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
