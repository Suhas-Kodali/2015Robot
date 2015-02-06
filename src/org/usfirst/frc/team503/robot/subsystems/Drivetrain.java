
package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDSource,
PIDOutput { 
	
	private static Drivetrain instance = new Drivetrain();
	public static Drivetrain getInstance(){
		return instance;
	}
	private final double p = -0.006, i = 0, d = 0;
	private final PIDController controller = new PIDController(p, i, d,
			getInstance(), getInstance());
    
	public CustomRobotDrive drive = new CustomRobotDrive(6,9,7,8,4,10);
	public Solenoid elevatorSolenoid = new Solenoid(0);
	public Solenoid grabberSolenoid = new Solenoid(1);
	private final Encoder drivetrainEncoder = new Encoder(2, 3);
	
	private Drivetrain(){
		drivetrainEncoder.setMaxPeriod(1 /* seconds */);
		controller.setOutputRange(-1, 1);
		controller.setAbsoluteTolerance(16);
	}
	
	public void arcadeDrive(double speedValue, double rotateValue, boolean squaredInputs, int mode){
		drive.arcadeDrive(speedValue, rotateValue, squaredInputs, mode);
	}
	
	public void setElevatorSolenoid(boolean position){
		elevatorSolenoid.set(position);
	}
	
	public void setGrabberSolenoid(boolean position){
		grabberSolenoid.set(position);
	}
	
	public void setSetpoint(double distance){
		controller.setSetpoint(distance);
	}
	public boolean onTarget() {
		return controller.onTarget();
	}

	public boolean isStopped() {
		return drivetrainEncoder.getRate() < 3;
	}

	public void pidEnable() {
		controller.enable();
	}

	public void pidDisable() {
		controller.disable();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void pidWrite(double speed) {
		SmartDashboard.putNumber("PIDWRITE", speed);
		if(controller.getError() > 50.0){
			arcadeDrive(1, 0, OI.squaredInputs, OI.mode);
		}
		else if(controller.getError() <-50.0) {
			arcadeDrive(-1, 0, OI.squaredInputs, OI.mode);
		}
		else{
			arcadeDrive(speed, 0, OI.squaredInputs, OI.mode);			
		}
		
	}
	
	public double getDistance(){
		return drivetrainEncoder.getDistance();
		
	}
	
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}
}

