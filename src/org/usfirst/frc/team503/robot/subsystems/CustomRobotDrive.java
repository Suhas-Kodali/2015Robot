package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;

public class CustomRobotDrive {
	private CANTalon frontLeftMotor;
	private CANTalon midLeftMotor;
	private CANTalon rearLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon midRightMotor;
	private CANTalon rearRightMotor;

	private CustomRobotDrive(int frontLeftMotor, int midLeftMotor,
			int rearLeftMotor, int frontRightMotor, int midRightMotor,
			int rearRightMotor) {
		this.frontLeftMotor = new CANTalon(frontLeftMotor);
		this.midLeftMotor = new CANTalon(midLeftMotor);
		this.rearLeftMotor = new CANTalon(rearLeftMotor);
		this.frontRightMotor = new CANTalon(frontRightMotor);
		this.midRightMotor = new CANTalon(midRightMotor);
		this.rearRightMotor = new CANTalon(rearRightMotor);
	}
	private static CustomRobotDrive instance = new CustomRobotDrive(4,0,2,5,1,3);
	
	public static CustomRobotDrive getInstance(){
		return instance;
	}

	public void arcadeDrive(double moveValue, double rotateValue,
			boolean squaredInputs) {

		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine
			// control while permitting full power
			if (moveValue >= 0.0) {
				moveValue = (moveValue * moveValue);
			} else {
				moveValue = -(moveValue * moveValue);
			}
			
			if (rotateValue >= 0.0) {
				rotateValue = (rotateValue * rotateValue);
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
		}

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}

		if (OI.mode == 0) {
			setModeZeroMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (OI.mode == 1) {
			setModeOneMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (OI.mode == 2) {
			setModeTwoMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		}
	}
	
	public void setElevatorSpeed(double speed){
		if(OI.mode == 1 || OI.mode == 3){
			frontLeftMotor.set(speed);
			frontRightMotor.set(-speed);
		}		
	}
	
	public void setGrabberSpeed(double speed){
		if(OI.mode == 2 || OI.mode == 3){
			midLeftMotor.set(speed);
			rearLeftMotor.set(speed);
			midRightMotor.set(speed);
			rearRightMotor.set(speed);
		}
	}

	private void setModeZeroMotorOutputs(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(leftSpeed);
		midLeftMotor.set(leftSpeed);
		rearLeftMotor.set(leftSpeed);
		frontRightMotor.set(rightSpeed);
		midRightMotor.set(rightSpeed);
		rearRightMotor.set(rightSpeed);
	}

	private void setModeOneMotorOutputs(double leftSpeed, double rightSpeed) {
		midLeftMotor.set(leftSpeed);
		rearLeftMotor.set(leftSpeed);
		midRightMotor.set(rightSpeed);
		rearRightMotor.set(rightSpeed);
	}

	private void setModeTwoMotorOutputs(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(leftSpeed);
		frontRightMotor.set(rightSpeed);
	}

	private static double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		
		if (num < -1.0) {
			return -1.0;
		}
		
		if(num < 0.2 && num > -0.2){
			return 0;
		}
		
		return num;
	}

}
