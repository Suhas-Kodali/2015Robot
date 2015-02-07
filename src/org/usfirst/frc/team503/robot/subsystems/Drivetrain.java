
package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
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
    
	public final CustomRobotDrive drive = new CustomRobotDrive(0,1,2,3,4,5);
	public final DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(0, 1);
	public final DoubleSolenoid grabberSolenoid = new DoubleSolenoid(2, 3);
	private final Encoder drivetrainEncoder = new Encoder(0, 1);
	
	private Drivetrain(){
		drivetrainEncoder.setMaxPeriod(1 /* seconds */);
		controller.setOutputRange(-1, 1);
		controller.setAbsoluteTolerance(16);
	}
	
	public enum Direction{
		Drivetrain(Value.kForward), Gamespec(Value.kReverse);
		private Value value;
		
		private Direction(Value value){
			this.value = value;
		}
	}
	
	public void arcadeDrive(double speedValue, double rotateValue, boolean squaredInputs, int mode){
		drive.arcadeDrive(speedValue, rotateValue, squaredInputs, mode);
	}
	
	public void setElevatorSolenoid(Direction position){
		elevatorSolenoid.set(position.value);
	}
	
	public void setGrabberSolenoid(Direction position){
		grabberSolenoid.set(position.value);
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
	
	public void pidReset(){
		controller.reset();
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

