package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveBack extends Command {
	double start; 
	Timer timer; 
	boolean done; 
    public autoMoveBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	timer = new Timer(); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	start = Robot.driveTrain.getEncoderTwo(); 
    	
    	timer.start(); 
    	
    	Robot.driveTrain.driveCertainAmounts(0.4, 0.4);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 3.0){
    		Robot.driveTrain.stop(); 
    		done = true; 
    		
    	}
//    	if(start - Robot.driveTrain.getEncoderTwo() < 0){
//    		Robot.driveTrain.stop(); 
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//timer.reset(); 
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//timer.reset(); 
		//Robot.driveTrain.stop(); 

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.reset(); 
		Robot.driveTrain.stop(); 

    }
}
