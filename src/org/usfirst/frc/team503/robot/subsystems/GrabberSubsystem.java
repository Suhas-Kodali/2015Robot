package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class GrabberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private GrabberSubsystem() {}
	private static GrabberSubsystem instance = new GrabberSubsystem();
	public static GrabberSubsystem getInstance() {
		return instance;
	}
	
	private DigitalInput outSwitch = new DigitalInput(11); 	
	private DigitalInput inSwitch = new DigitalInput(12);
	private Talon lasso = new Talon(2);
	
	
	
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
	
	public void setLassoSpeed(double speed) {
		lasso.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

 