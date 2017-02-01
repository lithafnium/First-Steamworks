package org.usfirst.frc.team3205.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private static final double FOV_DEG = 34.3;
    private static final double RECT_WIDTH = 3.0;
    private static final double RECT_HEIGHT = 5.0;
    private static final double RECT_DISTANCE = 12.0;
    
    private static final double OPTIMAL_DEG = 60.0; //change later; 
    boolean leftOfPeg = false; 
    
    ArrayList<Rect> contours; 
    //TreeMap<Rect, MatofPoint> contours; 
	//private VisionThread visionThread;
	private double centerX = 0.0;
	public Mat mat; 
	private final Object imgLock = new Object();
	public Vision(){
		mat = new Mat(); 
		pipeLine = new Pipeline(); 
		contours = new ArrayList<>(); 
		
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
		//serverOne = CameraServer.getInstance();
	}
	public void cameraStream(){
	
		

//		UsbCamera camera = serverOne.startAutomaticCapture("cam0", 0);
//		// Set the resolution
//		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
//		camera.setBrightness(50);
//		camera.setExposureManual(50); 

		// Get a CvSink. This will capture Mats from the camera	
		
	}
	public double processImages(){
		CvSink cvSink = serverOne.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = serverOne.getInstance().putVideo("Rectangle", IMG_WIDTH, IMG_HEIGHT);
		if (cvSink.grabFrame(mat) == 0) {
			// Send the output the error.
			outputStream.notifyError(cvSink.getError());
			return -1.0; 

		}
		else{
			
			// Give the output stream a new image to display
			pipeLine.process(mat);
			for(int i = 0; i < pipeLine.filterContoursOutput().size(); i++){
	            Rect r = Imgproc.boundingRect(pipeLine.filterContoursOutput().get(i));
	            contours.add(r); 
	            //pipeLine.filterContoursOutput().get(i).size(); 

			}
			outputStream.putFrame(mat);

			// sorts contours by size
			Collections.sort(contours, new compareRectSize());
			
			if(contours.size() >= 2){
				double angle = getTheta(contours.get(0), contours.get(1)); 
				// to the left or right of the peg 
				leftOfPeg = contours.get(0).x < contours.get(1).x ? true : false; 
				// angle you have to turn to; 
				return angle; 
			}
			else return distanceToTarget(contours.get(0)); 
			
		}
	}
	public double distanceToTarget(Rect rectangle) {
        int height = rectangle.height;
        double fovRad = FOV_DEG * Math.PI / 180;
        double ratio = height / IMG_HEIGHT;
        double theta = fovRad * ratio;
        double distance = RECT_HEIGHT / theta;
        return distance;
    }
    
    public double getTheta(Rect rect1, Rect rect2) {
        double dist1 = distanceToTarget(rect1);
        double dist2 = distanceToTarget(rect2);
        double dist3 = RECT_DISTANCE;
        double theta = Math.acos((dist1 * dist1 + dist2 * dist2- dist3 * dist3) / (2 * dist1 * dist2));
        return theta;
    }
    class compareRectSize implements Comparator<Rect>{

		@Override
		public int compare(Rect contourOne, Rect contourTwo) {
			// TODO Auto-generated method stub
			return contourTwo.height * contourTwo.width - contourOne.height * contourOne.width; 
		}
    	
    }
}


