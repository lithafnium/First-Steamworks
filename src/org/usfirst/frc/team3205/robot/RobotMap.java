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
	public static final double COIL = 0.2; 
	public static final double CLIMB_SPEED = -.6; 
	
	//LIMIT SWITCHES 
	public static final int IS_GEAR_IN = 9; 
	public static final int IS_UP_ONE = 6; // switch with Is_coiled
	//public static final int IS_UP_TWO = 7; 

	public static final int IS_COILED = 8; 
	
	public static final int IS_OUTTAKE_FLAP_BACK = 4; 

	//LINEAR ACTUATORS

	//ULTRASONIC SENSORS 
	public static final int ULTRA_TRIGGER_INPUT_ONE = 4; 
	public static final int ULTRA_PULSE_OUTPUT_ONE = 5; 
	
	public static final int ULTRA_TRIGGER_INPUT_TWO = 6; 
	public static final int ULTRA_PULSE_OUTPUT_TWO = 7; 

	//Encoder Ports 
	public static final int DRIVE_BASE_ENCODER_ONE_PORT_ONE = 0; // right side
	public static final int DRIVE_BASE_ENCODER_ONE_PORT_TWO = 1; // right side
	public static final int DRIVE_BASE_ENCODER_TWO_PORT_ONE = 2; // left side 
	public static final int DRIVE_BASE_ENCODER_TWO_PORT_TWO = 3; // left side 
	
	public static final int CLIMBER_ENCODER_PORT_ONE = 4; 
	public static final int CLIMBER_ENCODER_PORT_TWO = 5; 

	// MOTOR PORTS 
	public static final int TOP_LEFT_DRIVETRAIN_MOTOR = 6; 
	public static final int BOTTOM_LEFT_DRIVETRAIN_MOTOR = 9; 

	public static final int TOP_RIGHT_DRIVETRAIN_MOTOR = 3; 
	public static final int BOTTOM_RIGHT_DRIVETRAIN_MOTOR = 2; 

	
	public static final int CLIMBER_BACK =  7; 
	public static final int CLIMBER_FRONT = 5;  
	
	public static final int COIL_MOTOR = 1; // hopper tarp motor 
	//public static final int FLAP_OUT_ONE = 4;  
	//public static final int FLAP_OUT_TWO = 5; 
	public static final int FLAP_MOTOR = 0; // outtake flap 
	public static final int PUSH_EM_OUT = 8; // push gear out port servo on gear subsystem

	public static final int INTAKE_FLAP = 4; // linear actutor  
	// VISION 
	public static final int IMG_WIDTH = 640;
    public static final int IMG_HEIGHT = 480;
    public static final double FOV_DEG = 34.3;
    public static final double TAPE_WIDTH = 3.0;
    public static final double TAPE_HEIGHT = 5.0;
    public static final double TAPE_DISTANCEBETWEEN = 8.25;
    public static final double PEG_LENGTH = 5.0; 
    //DRIVEBASE stuff 
    public static final int GYRO_PORT = 4; 
    
    //MISC 
    public static final int PDP_PORT = 18; // pdp for the climber 
    public static final double PDP_STALL = 20.0;  // if the motor is stalling 
    public static boolean switchDirection = true; 
	public static boolean camTwoStart = true;
	public static boolean camOneStart = true; 
	public static boolean climberHit = false; 

}
