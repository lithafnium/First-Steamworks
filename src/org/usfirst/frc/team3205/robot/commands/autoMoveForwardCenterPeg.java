package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveForwardCenterPeg extends Command {
	boolean done; 
	Timer timer; 
    public autoMoveForwardCenterPeg() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); 
    	requires(Robot.gear);
    	timer = new Timer(); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gear.pushOut(); 
    	timer.start(); 
    	Robot.driveTrain.driveCertainAmounts(-0.54, -0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gear.isGearIn()){
//    		Robot.driveTrain.driveCertainAmounts(-0.54, -0.5);
//    		try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    		Robot.driveTrain.stop();
    		Robot.gear.retract(); 

    		done = true; 
    	}
    	if(timer.get() > 7 && !done){
    		RobotMap.goBack = true; 

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
