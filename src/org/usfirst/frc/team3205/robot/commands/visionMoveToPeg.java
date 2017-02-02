package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class visionMoveToPeg extends Command {
	
	private double distanceToPeg; 
	private double start; 
	private boolean done; 
	
    public visionMoveToPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    	requires(Robot.gear); 
    	requires(Robot.driveTrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	start = Robot.driveTrain.getEncoderOne(); 
    	distanceToPeg = Robot.vision.altitude - RobotMap.PEG_LENGTH; 
    	Robot.driveTrain.driveCertainAmounts(0.5, 0.5);
		Robot.gear.pushOut();


    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.getEncoderOne() - start >= distanceToPeg){
    		if(Robot.gear.isGearIn()){
    			Robot.driveTrain.stop(); 
    			done = true; 
    		}
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
    }
}
