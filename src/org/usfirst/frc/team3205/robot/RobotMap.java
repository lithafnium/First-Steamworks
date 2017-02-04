package org.usfirst.frc.team3205.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//MOTORSPEEDS
	public static final double COIL = 0.6; 
	public static final double CLIMB_SPEED = 0.6; 
	
	//LIMIT SWITCHES 
	public static final int IS_GEAR_IN = 5; 
	public static final int IS_UP = 9; 
	public static final int IS_COILED = 10; 

	//LINEAR ACTUATORS
	public static final int PUSH_EM_OUT = 6; // push gear out port

	//ULTRASONIC SENSORS 
	public static final int ULTRA_TRIGGER_INPUT_ONE = 14; 
	public static final int ULTRA_PULSE_OUTPUT_ONE = 15; 
	
	public static final int ULTRA_TRIGGER_INPUT_TWO = 18; 
	public static final int ULTRA_PULSE_OUTPUT_TWO = 20; 


	// MOTOR PORTS 
	public static final int TOP_LEFT_DRIVETRAIN_MOTOR = 0; 
	public static final int BOTTOM_LEFT_DRIVETRAIN_MOTOR = 1; 

	public static final int TOP_RIGHT_DRIVETRAIN_MOTOR = 40; 
	public static final int BOTTOM_RIGHT_DRIVETRAIN_MOTOR = 60; 

	
	public static final int CLIMBER_ONE = 2; 
	public static final int CLIMBER_TWO = 3; 
	
	public static final int COIL_MOTOR = 7; 
	public static final int FLAP_OUT_ONE = 8; 
	public static final int FLAP_OUT_TWO = 11; 
	
	// VISION 
	public static final int IMG_WIDTH = 320;
    public static final int IMG_HEIGHT = 240;
    public static final double FOV_DEG = 34.3;
    public static final double RECT_WIDTH = 3.0;
    public static final double RECT_HEIGHT = 5.0;
    public static final double RECT_DISTANCE = 6.25;
    public static final double PEG_LENGTH = 5.0; 
    //DRIVEBASE stuff 
    public static final int GYRO_PORT = 50; 
    
    //MISC 
    public static final int PDP_PORT = 30; // pdp for the climber 
    public static final double PDP_STALL = 20.0;  // if the motor is stalling 
}
