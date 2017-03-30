package org.usfirst.frc.team3205.robot.commands;

import org.usfirst.frc.team3205.robot.Robot;
import org.usfirst.frc.team3205.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAngleAlign extends Command implements PIDOutput {

	private PIDController controller; 
	private double endTime; 
	private double angle; 
	private double speed; 

	private double pidOut; 

	private long onTargetMillis; 
	private long startTimeMillis; 


	public AutoAngleAlign(double angle, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain); 
		this.angle = angle; 
		this.speed = speed; 
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		double kP = RobotMap.kP; 
		double kI = RobotMap.kI; 
		double kD = RobotMap.kD; 

		controller = new PIDController(kP, kI, kD, Robot.driveTrain.gyroSensor, this); 
		controller.setInputRange(RobotMap.MIN_ANGLE, RobotMap.MAX_ANGLE);
		controller.setOutputRange(-speed, speed);
		controller.setAbsoluteTolerance(RobotMap.TOLERANCE);
		controller.setContinuous();
		controller.setSetpoint(angle);
		controller.enable(); 
		startTimeMillis = System.currentTimeMillis();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.driveCertainAmounts(pidOut, -pidOut);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!controller.onTarget()) {
			onTargetMillis = 0;
			return false;
		}
		if (onTargetMillis == 0) {
			onTargetMillis = System.currentTimeMillis();
		}
		return System.currentTimeMillis() > onTargetMillis + RobotMap.ALIGN_STEADY_TIME;
	}

	// Called once after isFinished returns true
	protected void end() {
		controller.disable();
		Robot.driveTrain.driveCertainAmounts(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		synchronized (this) {
			pidOut = output;
		}

	}
}
