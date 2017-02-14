package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class visionTurnToPeg extends Command {

	double angle; 
	private boolean done =  false;

    public visionTurnToPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	//Robot.driveTrain.calibrateGyro();
    	angle = Robot.vision.findAngleToTurn(); 
    	
    	if(Robot.vision.leftOfPeg){
        	Robot.driveTrain.driveCertainAmounts(.5, -.5);

    	}
    	else Robot.driveTrain.driveCertainAmounts(-.5, .5); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.driveTrain.getAngle() - angle) <= 5 ){
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
    }
}
