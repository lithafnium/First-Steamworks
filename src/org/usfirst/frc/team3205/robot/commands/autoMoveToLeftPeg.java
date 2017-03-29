package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveToLeftPeg extends Command {

    public autoMoveToLeftPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    	requires(Robot.gear); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.driveCertainAmounts(-0.5, -0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gear.isGearIn()){
    		Robot.driveTrain.stop(); 
    		Robot.gear.retract(); 
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
