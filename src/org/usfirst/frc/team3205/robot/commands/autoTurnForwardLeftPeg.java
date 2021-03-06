package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoTurnForwardLeftPeg extends Command {
	boolean done; 
	Timer timer; 
    public autoTurnForwardLeftPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    	timer = new Timer(); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start(); 
    	Robot.driveTrain.driveCertainAmounts(0.5, -0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 0.9){
    		Robot.driveTrain.stop(); 
    		done = true; 
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
