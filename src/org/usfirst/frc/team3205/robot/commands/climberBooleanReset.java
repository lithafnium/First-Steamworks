package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class climberBooleanReset extends Command {

    public climberBooleanReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climb); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.climberHit = false; 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.climberHit = false; 

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.climberHit = false; 

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.climberHit = false; 

    }
}
