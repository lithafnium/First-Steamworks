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

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;
import org.usfirst.frc.team3205.robot.commands.visionStartCameraStream;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	CameraServer serverOne;
	UsbCamera camera; 
	Pipeline pipeLine; 
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public double smallerHypot = 0.0; 
	public double altitude = 0.0; 
	
    
   // private static final double OPTIMAL_DEG = 60.0; //change later; 
    public boolean leftOfPeg = false; 
    
    private ArrayList<Rect> contours; 
    //TreeMap<Rect, MatofPoint> contours; 
	//private VisionThread visionThread;
	//private double centerX = 0.0;
	public Mat mat; 
	//private final Object imgLock = new Object();
	public Vision(){
		mat = new Mat(); 
		pipeLine = new Pipeline(); 
		contours = new ArrayList<>(); 
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new visionStartCameraStream()); 
	}

	public void cameraInit(){
		serverOne = CameraServer.getInstance();
;
		camera = serverOne.startAutomaticCapture();
		//camera.setResolution(320, 240);
		camera.setBrightness(-50);
		camera.setExposureManual(21);


	}
	
	public void processImages(){
		CvSink cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
		if (cvSink.grabFrame(mat) == 0) {
			// Send the output the error.
			outputStream.notifyError(cvSink.getError());
			//return -1.0; 

		}
		else{
			
			// Give the output stream a new image to display
			// filters the image 
			pipeLine.process(mat);
			
			outputStream.putFrame(mat);

			// sorts contours by size
			
			
			
		}
	}
	public void findAndSort(){
		for(int i = 0; i < pipeLine.filterContoursOutput().size(); i++){
            Rect r = Imgproc.boundingRect(pipeLine.filterContoursOutput().get(i));
            contours.add(r); 
            //pipeLine.filterContoursOutput().get(i).size(); 

		}
		Collections.sort(contours, new compareRectSize());
 
	}
	public void resetContours(){
		contours = new ArrayList<Rect>(); 
	}
	// finds the angle from your original position 
	public double findAngle(){
		if(contours.size() >= 2){
			double angle = getTheta(distanceToTarget(contours.get(0)), distanceToTarget(contours.get(1)), RobotMap.TAPE_DISTANCEBETWEEN); 
			// to the left or right of the peg 
			// if the larger contour x coordinate is less than the next largest 
			leftOfPeg = contours.get(0).x < contours.get(1).x ? true : false; 
			// angle you have to turn to / 2, as you're rotating to the side   
			return angle/2; 
		}
		else return -2; 
	}
	// find the angle to turn once you centered the robot on the peg 
	public double findAngleToTurn(){
		altitude = findAltitude(smallerHypot);
		return getTheta(smallerHypot, altitude, RobotMap.TAPE_DISTANCEBETWEEN / 2); 
		
	} 
	// finds the distance from which you move towards the peg (doesn't move right to it, 
	// moves at the point where it meets the peg if the peg was extended further 
	public double moveToPeg(){
		if(contours.size() >= 2){
			
			double sideOne = distanceToTarget(contours.get(0)); // distance to one contour
			double sideTwo = distanceToTarget(contours.get(1)); // distance to the other 
			
			double largerSide = sideOne > sideTwo ? sideOne : sideTwo; 
			
			double area = area(sideOne, sideTwo); // area of the trignale formed 
			double height = calculateHeight(area);  // height of the triangle formed 
			
			// finds the base of the right triangle formed with the larger distnace as the hypot
			double base =findBase(largerSide, height);  
			
			// finds the length of the smaller hypotenuse, the end of which meets the end of the peg 
			smallerHypot =  findSmallHypot(largerSide, base); 
			
			// calculates the distance the robot has to move 
			double distance =  calculateDistanceToPeg(largerSide, smallerHypot); 
			contours = new ArrayList<Rect>(); // resets the arrayList 
			return distance; 
		}
		//contours = new ArrayList<Rect>(); // resets the arrayList 

		return -1; 
	}
	// area of the triangle formed with the tape being the end points, the legs being distances to the tape
	public double area(double sideOne, double sideTwo){
		double sideThree = RobotMap.TAPE_DISTANCEBETWEEN; 
		double s = (sideOne + sideTwo + sideThree)/2; // semiperimeter
		double area = Math.sqrt(s*(s - sideOne)*(s - sideTwo)*(s - sideThree)); 
		return area; 
	}
	// calculates the height of the triangle before moving 
	public double calculateHeight(double area){
		return area * 2 / RobotMap.TAPE_DISTANCEBETWEEN; 
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
	// use only when you moved the robot to the right place - in front of the peg 
	public double findAltitude(double hypot){
		return Math.pow(hypot,  2) - Math.pow(RobotMap.TAPE_WIDTH, 2); 
	}
	
	// approximate distance to the target using apparent size
	public double distanceToTarget(Rect rectangle) {
        int height = rectangle.height;
        double fovRad = RobotMap.FOV_DEG * Math.PI / 180;
        double ratio = height / RobotMap.IMG_HEIGHT;
        double theta = fovRad * ratio;
        double distance = RobotMap.TAPE_HEIGHT / theta;
        return distance;
    }
    // gets the angle between looking at two contours --> the rectangles 
    public double getTheta(double dist1, double dist2, double dist3) {
        double theta = Math.acos((dist1 * dist1 + dist2 * dist2- dist3 * dist3) / (2 * dist1 * dist2));
        return theta;
    }
    // get angle of offset of robot relative to contour, where 0 is facing it directly
    public double angleToPerpendicular() { // if findAngleToTurn() doesn't work
    	Rect rectangle = contours.get(0); 
    	double directWidth = rectangle.height * RobotMap.TAPE_WIDTH / RobotMap.TAPE_HEIGHT;
    	double ratio = rectangle.width / directWidth;
    	return 90 - ratio * 90;
    }
    
    public double turnToPegFromPerpendicular(){
    	Rect smallerRect = contours.get(1); 
    	double distanceToContour = distanceToTarget(smallerRect); 
    	double perpendicularDistance = (Robot.driveTrain.getDistanceOne() + Robot.driveTrain.getDistanceTwo())/2;
    	
    	double base = Math.sqrt(Math.pow(distanceToContour, 2) - Math.pow(perpendicularDistance, 2)); 
    	
    	return Math.acos(perpendicularDistance / distanceToContour); 
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


