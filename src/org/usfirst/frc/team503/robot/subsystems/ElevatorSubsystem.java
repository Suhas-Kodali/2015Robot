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
public class ElevatorSubsystem extends Subsystem implements PIDSource, PIDOutput {
	
	private final int CHECK_TOLERANCE = 50;
	private final static double i = -0.000000, d = 0, p = -0.006;
    private final static PIDController controller = new PIDController(p, i, d, getInstance(), getInstance());
    private final static Encoder elevatorEncoder = new Encoder(0, 1);
	
	CANTalon frontLeftMotor = Drivetrain.drive.frontLeftMotor;
	CANTalon frontRightMotor = Drivetrain.drive.frontRightMotor;
	Solenoid elevatorSolenoid = new Solenoid(2);
	
    
    static {
        //encoder.setDistancePerPulse(360/(ENCODER_CPR/*GEARBOX_RATIO*/*CHAIN_RATIO));
        elevatorEncoder.setMaxPeriod(1/*seconds*/);    
        controller.setOutputRange(-1, 1);
        controller.setAbsoluteTolerance(16/*degrees, should be*/);
        //anglerMotor.setSafetyEnabled(false);
    }
    
	public enum ElevatorSolenoidPosition{
        OPEN(true), CLOSE(false);
        private boolean position;

        private ElevatorSolenoidPosition(boolean position){
                this.position = position;
        }
	}   
	
	public enum ElevatorPosition{
		TOP(800), SECOND(600), THIRD(400), FOURTH(200), BOTTOM(0);
		private int position;
		private ElevatorPosition(int position){
			this.position = position;
		}
	}
	
	private static ElevatorPosition[] positions = new ElevatorPosition[]{ElevatorPosition.BOTTOM, ElevatorPosition.FOURTH, ElevatorPosition.THIRD, ElevatorPosition.SECOND, ElevatorPosition.TOP};
	private ElevatorSubsystem(){}
	private static ElevatorSubsystem instance = new ElevatorSubsystem();
	public static ElevatorSubsystem getInstance(){
		return instance;
	}
	
	public void determinePosition(){
		for(int i = 0; i < positions.length; i++){
			if(positions[i].position < getDistance()+CHECK_TOLERANCE && positions[i].position > getDistance()-CHECK_TOLERANCE){
				OI.position = i;
			}
		}
	}
	
	public static ElevatorPosition[] getPositions(){
		return positions;
	}
	
	public ElevatorPosition[] getElevatorPositions(){
		return positions;
	}
	
	public void setSolenoidPosition(ElevatorSolenoidPosition position){
			elevatorSolenoid.set(position.position);
	}
	
	public void setSpeed(double speed, int mode){
		if(mode == 2){
			frontLeftMotor.set(speed);
			frontRightMotor.set(speed);
		}
	}
	public static double getDistance(){
        return elevatorEncoder.getDistance();
    }
    
    	@Override
	public void pidWrite(double speed) {
		SmartDashboard.putNumber("PIDWRITE", speed);
        setSpeed(speed, OI.mode);
	}
	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}
	
	public static void setSetpoint(ElevatorPosition position){
		controller.setSetpoint(position.position);
	}
	
	public static boolean onTarget(){
        return controller.onTarget();
    }
	
	public static boolean isStopped() {
        return elevatorEncoder.getRate() < 3;
    }
	
	public static void pidEnable(){
        controller.enable();
    }
    public static void pidDisable(){
        controller.disable();
    }
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

}

