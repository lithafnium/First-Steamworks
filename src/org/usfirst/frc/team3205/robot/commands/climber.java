package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class climber extends Command {
	double startDistance; 
	boolean done = false; 
    public climber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climb); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climb.climb(); 
    	Robot.climb.resetEncoder();
    	Robot.climb.initialPosition = Robot.climb.getDistance(); 
    	startDistance = Robot.climb.getDistance(); 

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// change later 
    	if(Robot.climb.motorStall() || Robot.climb.getDistance() - startDistance >= 20 || Robot.climb.climbingLimit() || Robot.climb.climbingLimitTwo()){
    		Robot.climb.stop(); 
    		done = true; 
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climb.stop(); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
