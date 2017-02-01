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
	public static final double SUCKER = 0.6; 
	public static final double CLIMB_SPEED = 0.6; 
	
	//LIMIT SWITCHES 
	public static final int IS_GEAR_IN = 5; 
	public static final int IS_UP = 9; 
	public static final int IS_FLAP_OUT = 10; 

	//LINEAR ACTUATORS
	public static final int PUSH_EM_OUT = 6; // push gear out port

	
	// MOTOR PORTS 
	public static final int LEFT_DRIVETRAIN_MOTOR = 0; 
	public static final int RIGHT_DRIVETRAIN_MOTOR = 1; 
	
	public static final int CLIMBER_ONE = 2; 
	public static final int CLIMBER_TWO = 3; 
	
	public static final int INTAKE = 7; 
	public static final int INTAKE_FLAP_OUT = 8; 
}
