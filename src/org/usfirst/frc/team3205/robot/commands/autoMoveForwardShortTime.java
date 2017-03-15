package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveForwardShortTime extends Command {
	Timer timer; 
	boolean done; 
    public autoMoveForwardShortTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer(); 
    	Robot.driveTrain.driveCertainAmounts(-0.6, -0.6);
    	timer.start(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 1.5){
    		Robot.driveTrain.stop(); 
    		done = true; 
    		timer.reset(); 
    	}
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
