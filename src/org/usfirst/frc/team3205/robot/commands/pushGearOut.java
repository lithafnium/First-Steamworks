package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class pushGearOut extends Command {

    public pushGearOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println(";alksdfja;lskjfl;aksdfj");
    	Robot.gear.pushOut(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gear.pushOut(); 

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.pushOut(); 

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
