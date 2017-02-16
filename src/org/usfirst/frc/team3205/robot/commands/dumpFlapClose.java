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
    	Robot.box.closeFlap();
    	timer.start(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.box.getDistance() - startDistance <= 100){
//    		Robot.box.stopFlap();
//    		done = true; 
//    	}
    	if(Timer.getFPGATimestamp() > 2.0){
    		Robot.box.stopFlap(); 
    		done = true; 
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
