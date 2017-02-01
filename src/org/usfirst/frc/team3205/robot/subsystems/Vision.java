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

import org.usfirst.frc.team3205.robot.RobotMap;
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
		double turn = centerX - (RobotMap.IMG_WIDTH / 2);
	}
	public void cameraInit(){
		serverOne = CameraServer.getInstance();
	}
	public void cameraStream(){
	
		

		UsbCamera camera = serverOne.startAutomaticCapture("cam0", 0);
		// Set the resolution
		camera.setResolution(RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
		camera.setBrightness(50);
		camera.setExposureManual(50); 

		// Get a CvSink. This will capture Mats from the camera	
		
	}
	public double processImages(){
		CvSink cvSink = serverOne.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = serverOne.getInstance().putVideo("Rectangle", RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
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
				// angle you have to turn to / 2, as you're rotating to the side   
				return angle/2; 
			}
			else return distanceToTarget(contours.get(0)); 
			
		}
	}
	public double moveToPeg(){
		if(contours.size() >= 2){
			double sideOne = distanceToTarget(contours.get(0)); 
			double sideTwo = distanceToTarget(contours.get(1)); 
			
			double area = area(sideOne, sideTwo); 
			double height = calculateHeight(area); 
			
			double base = sideOne > sideTwo ? findBase(sideOne, height) : findBase(sideTwo, height);  
			double smallerHypot = sideOne > sideTwo ? findSmallHypot(sideOne, base) : findSmallHypot(sideTwo, base); 
			
			double distance = sideOne > sideTwo ? calculateDistanceToPeg(sideOne, smallerHypot) : calculateDistanceToPeg(sideTwo, smallerHypot); 
			contours = new ArrayList<Rect>(); // resets the arrayList 
			return distance; 
		}
		contours = new ArrayList<Rect>(); // resets the arrayList 

		return -1; 
	}
	public double area(double sideOne, double sideTwo){
		double sideThree = 6.0; 
		double s = (sideOne + sideTwo + sideThree)/2; // semiperimeter
		double area = Math.sqrt(s*(s - sideOne)*(s - sideTwo)*(s - sideThree)); 
		return area; 
	}
	public double calculateHeight(double area){
		return area * 2 / 6; 
	}
	// finds the base of the right triangle formed, with the hypotenuse being the longer side 
	// of the triangle formed by the distances from the tape
	public double findBase(double hypot, double height){
		return Math.sqrt(Math.pow(hypot, 2) - Math.pow(height, 2)); 
	}
	
	// finds the distance from which the peg if extended hits the side 
	public double findSmallHypot(double largerHypot, double largerBase){
		return (2/largerBase) * largerHypot; 
	}
	// distance to travel and turn towards the peg
	public double calculateDistanceToPeg(double largerDistance, double smallerDistance){
		return largerDistance - smallerDistance; 
	}
	
	// distance to the target using apparent size 
	public double distanceToTarget(Rect rectangle) {
        int height = rectangle.height;
        double fovRad = RobotMap.FOV_DEG * Math.PI / 180;
        double ratio = height / RobotMap.IMG_HEIGHT;
        double theta = fovRad * ratio;
        double distance = RobotMap.RECT_HEIGHT / theta;
        return distance;
    }
    // gets the angle between looking at two contours --> the rectangles 
    public double getTheta(Rect rect1, Rect rect2) {
        double dist1 = distanceToTarget(rect1);
        double dist2 = distanceToTarget(rect2);
        double dist3 = RobotMap.RECT_DISTANCE;
        double theta = Math.acos((dist1 * dist1 + dist2 * dist2- dist3 * dist3) / (2 * dist1 * dist2));
        return theta;
    }
    // sorts the rectangles by size --> size of the contours 
    class compareRectSize implements Comparator<Rect>{

		@Override
		public int compare(Rect contourOne, Rect contourTwo) {
			// TODO Auto-generated method stub
			return contourTwo.height * contourTwo.width - contourOne.height * contourOne.width; 
		}
    	
    }
}


