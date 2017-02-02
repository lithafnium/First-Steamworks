package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class visionProcessImage extends Command {
	private double start; 
	private double angle; 
	private double distanceToCenter;
	private double distanceToPeg; 
    public visionProcessImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision); 
    	requires(Robot.driveTrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// 1/2 of the angle from your original position 
    	angle = Robot.vision.findAngle();
    	distanceToPeg = Robot.vision.altitude - RobotMap.PEG_LENGTH; 
    	start = Robot.driveTrain.getEncoderOne(); 
    	
    	//Robot.driveTrain.driveCertainAmounts(0.5); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.getEncoderOne() - start >= distanceToPeg){
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
