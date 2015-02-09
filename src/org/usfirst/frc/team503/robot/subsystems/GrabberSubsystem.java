package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class GrabberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
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
	
	public void setSpeed(int speed) {
		CustomRobotDrive.getInstance().setGrabberSpeed(speed);
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

 