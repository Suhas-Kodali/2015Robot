package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class GrabberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon midLeftMotor = Drivetrain.getInstance().drive.midLeftMotor;
	CANTalon midRightMotor = Drivetrain.getInstance().drive.midRightMotor;
	CANTalon rearLeftMotor = Drivetrain.getInstance().drive.rearLeftMotor;
	CANTalon rearRightMotor = Drivetrain.getInstance().drive.rearRightMotor;
	
	DigitalInput outSwitch = new DigitalInput(0); 	
	DigitalInput inSwitch = new DigitalInput(1);
	
	private GrabberSubsystem() {}
	private static GrabberSubsystem instance = new GrabberSubsystem();
	public static GrabberSubsystem getInstance() {
		return instance;
	}
	
	public boolean getOutSwitch() {
		return outSwitch.get();	
	}
	
	public boolean getInSwitch() {
		return inSwitch.get();
	}
	
	public void setSpeed(int speed) {//have it have a enum for "sixcimdrive, liftanddrive, grabanddrive, grabandlift" and switch with a method that takes that enum, and then have 3 different methods that attempt to output to either drivetrain, elev
		if(OI.mode == 3){
			midLeftMotor.set(speed);
			midRightMotor.set(speed);
			rearLeftMotor.set(speed);
			rearRightMotor.set(speed);
		}
	}
	
	public void grabberOut() {
		setSpeed(1);
	}
	
	public void grabberIn() {
		setSpeed(-1);
	}
	
	public void grabberStop(){
		setSpeed(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

 