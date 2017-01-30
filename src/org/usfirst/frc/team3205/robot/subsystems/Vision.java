package org.usfirst.frc.team3205.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect; 
//import com.ni.vision.NIVision.Rect;

import org.usfirst.frc.team3205.robot.commands.startCameraStream;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */
public class Vision extends Subsystem {
	CameraServer serverOne; 
	Pipeline pipeLine; 
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	//private VisionThread visionThread;
	private double centerX = 0.0;
	public Mat mat; 
	private final Object imgLock = new Object();
	public Vision(){
		mat = new Mat(); 
		pipeLine = new Pipeline(); 
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new startCameraStream()); 
	}
	public void calculate(){
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		double turn = centerX - (IMG_WIDTH / 2);
		//drive.arcadeDrive(-0.6, turn * 0.005);
	}
	public void cameraInit(){
		serverOne = CameraServer.getInstance();
	}
	public void cameraStream(){
	
		

		UsbCamera camera = serverOne.startAutomaticCapture("cam0", 0);
		// Set the resolution
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setBrightness(50);
		camera.setExposureManual(50); 

		// Get a CvSink. This will capture Mats from the camera	
		
	}
	public void processImages(){
		CvSink cvSink = serverOne.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = serverOne.getInstance().putVideo("Rectangle", IMG_WIDTH, IMG_HEIGHT);
		if (cvSink.grabFrame(mat) == 0) {
			// Send the output the error.
			outputStream.notifyError(cvSink.getError());

		}
		else{
			
			// Give the output stream a new image to display
			pipeLine.process(mat);
			for(int i = 0; i < pipeLine.filterContoursOutput().size(); i++){
	            Rect r = Imgproc.boundingRect(pipeLine.filterContoursOutput().get(i));
	            //pipeLine.filterContoursOutput().get(i).size(); 

			}

			outputStream.putFrame(mat);
		}
	}
}

