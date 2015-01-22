package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class GrabberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon midLeftMotor = Drivetrain.drive.midLeftMotor;
	CANTalon midRightMotor = Drivetrain.drive.midRightMotor;
	CANTalon rearLeftMotor = Drivetrain.drive.rearLeftMotor;
	CANTalon rearRightMotor = Drivetrain.drive.rearRightMotor;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

 