package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class hopperUnCoil extends Command {
	Timer timer; 
	boolean done = false; 
    public hopperUnCoil() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    	requires(Robot.box); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	timer = new Timer(); 
//    	timer.start(); 

    	Robot.box.unCoil();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(timer.get() > 3.0){
//    		Robot.box.stop(); 
//    		done = true; 
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//		Robot.box.stop(); 
//
//    	timer.reset(); 
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.box.stop(); 

//    	timer.reset(); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.box.stop(); 

    }
}
