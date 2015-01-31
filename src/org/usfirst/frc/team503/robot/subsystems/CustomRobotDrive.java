package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;

public class CustomRobotDrive {
	CANTalon frontLeftMotor;
	CANTalon midLeftMotor;
	CANTalon rearLeftMotor;
	CANTalon frontRightMotor;
	CANTalon midRightMotor;
	CANTalon rearRightMotor;

	public CustomRobotDrive(int frontLeftMotor, int midLeftMotor,
			int rearLeftMotor, int frontRightMotor, int midRightMotor,
			int rearRightMotor) {
		this.frontLeftMotor = new CANTalon(frontLeftMotor);
		this.midLeftMotor = new CANTalon(midLeftMotor);
		this.rearLeftMotor = new CANTalon(rearLeftMotor);
		this.frontRightMotor = new CANTalon(frontRightMotor);
		this.midRightMotor = new CANTalon(midRightMotor);
		this.rearRightMotor = new CANTalon(rearRightMotor);
	}

	public void arcadeDrive(double moveValue, double rotateValue,
			boolean squaredInputs, int mode) {

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

		if (mode == 1) {
			setModeOneMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (mode == 2) {
			setModeTwoMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else if (mode == 3) {
			setModeThreeMotorOutputs(leftMotorSpeed, rightMotorSpeed);
		} else {
			setModeOneMotorOutputs(0, 0);
		}
	}

	public void setModeOneMotorOutputs(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(leftSpeed);
		midLeftMotor.set(leftSpeed);
		rearLeftMotor.set(leftSpeed);
		frontRightMotor.set(rightSpeed);
		midRightMotor.set(rightSpeed);
		rearRightMotor.set(rightSpeed);
	}

	public void setModeTwoMotorOutputs(double leftSpeed, double rightSpeed) {
		midLeftMotor.set(leftSpeed);
		rearLeftMotor.set(leftSpeed);
		midRightMotor.set(rightSpeed);
		rearRightMotor.set(rightSpeed);
	}

	public void setModeThreeMotorOutputs(double leftSpeed, double rightSpeed) {
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
		
		return num;
	}

}
