package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorSubsystem extends Subsystem implements PIDSource,
		PIDOutput {
	
	private final double p = -0.006, i = 0, d = 0;
	private final PIDController controller = new PIDController(p, i, d,
			getInstance(), getInstance());
	private final Encoder elevatorEncoder = new Encoder(2, 3);
	private final CANTalon frontLeftMotor = Drivetrain.getInstance().drive.frontLeftMotor;
	private final CANTalon frontRightMotor = Drivetrain.getInstance().drive.frontRightMotor;
	private final DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(4, 5);

	private ElevatorSubsystem() {
		// encoder.setDistancePerPulse(360/(ENCODER_CPR/*GEARBOX_RATIO*/*CHAIN_RATIO));
			elevatorEncoder.setMaxPeriod(1 /* seconds */);
			controller.setOutputRange(-1, 1);
			controller.setAbsoluteTolerance(16);
				// anglerMotor.setSafetyEnabled(false); lel
	}

	private static ElevatorSubsystem instance = new ElevatorSubsystem();

	public static ElevatorSubsystem getInstance() {
		return instance;
	}

	
	public enum ElevatorSolenoidPosition {
		OPEN(Value.kForward), CLOSE(Value.kReverse);
		private Value position;

		private ElevatorSolenoidPosition(Value position) {
			this.position = position;
		}
	}

	public enum ElevatorPosition {
		TOP(800), SECOND(600), THIRD(400), FOURTH(200), BOTTOM(0);
		private int position;

		private ElevatorPosition(int position) {
			this.position = position;
		}
	}

	// lowest distance at pos 0
	private ElevatorPosition[] positions = new ElevatorPosition[] {
			ElevatorPosition.BOTTOM, ElevatorPosition.FOURTH,
			ElevatorPosition.THIRD, ElevatorPosition.SECOND,
			ElevatorPosition.TOP };

	public void determinePosition() {
		if (getDistance() > (positions[positions.length - 1].position + positions[positions.length-2].position)/2) {
			OI.position = positions.length - 1;
		}else if (getDistance() < ((positions[0].position + positions[1].position)/2)) {
			OI.position = 0;
		}else {
			for(int i = 1; i < positions.length - 1; i++) {
				if ((positions[i + 1].position + positions[i].position) / 2 > getDistance()
						&& (positions[i].position + positions[i - 1].position) / 2 < getDistance()) {
					OI.position = i;
				}
			}
		}
	}

	public ElevatorPosition[] getPositions() {
		return positions;
	}

	public void setSolenoidPosition(ElevatorSolenoidPosition position) {
		elevatorSolenoid.set(position.position);
	}

	public void setSpeed(double speed) {
		if (OI.mode == 1) {
			frontLeftMotor.set(speed);
			frontRightMotor.set(speed);
		}
	}

	public double getDistance() {
		return elevatorEncoder.getDistance();
	}

	@Override
	public void pidWrite(double speed) {
		SmartDashboard.putNumber("PIDWRITE", speed);
		setSpeed(speed);
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getDistance();
	}

	public void setSetpoint(ElevatorPosition position) {
		controller.setSetpoint(position.position);
	}

	public boolean onTarget() {
		return controller.onTarget();
	}

	public boolean isStopped() {
		return elevatorEncoder.getRate() < 3;
	}

	public void pidEnable() {
		controller.enable();
	}

	public void pidDisable() {
		controller.disable();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}