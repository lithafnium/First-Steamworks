package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class cameraTwoInit extends Command {

	int cameraTwo;
	Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    public cameraTwoInit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	RobotMap.camTwoInit = false;
//    	RobotMap.camOneInit = true;
    	if(RobotMap.camTwoStart){
	    	cameraTwo = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	    	NIVision.IMAQdxConfigureGrab(cameraTwo);
	    	RobotMap.camTwoStart = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	NIVision.IMAQdxStartAcquisition(cameraTwo);
    	NIVision.IMAQdxGrab(cameraTwo, frame, 1);
    	//CameraServer.getInstance().setQuality(50);
    	CameraServer.getInstance().setImage(frame);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		NIVision.IMAQdxStopAcquisition(cameraTwo);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	NIVision.IMAQdxStopAcquisition(cameraTwo);
    }
}
