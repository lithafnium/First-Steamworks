package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * ONLY CALL THIS IF GETIMAGE WAS CALLED ALREADY, AS THIS SETS THE CONTOURS AND THE SIZES 
 */
public class visionMoveAngle extends Command {
	private double angle; 

    public visionMoveAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.calibrateGyro();
    	angle = Robot.vision.findAngle();
    	if(Robot.vision.leftOfPeg){
        	Robot.driveTrain.driveCertainAmounts(.5, -.5);

    	}
    	else Robot.driveTrain.driveCertainAmounts(-.5, .5); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.driveTrain.getAngle() - angle) <= 5 ){
    		Robot.driveTrain.stop(); 
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