
package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	
	private Drivetrain(){}
	
	private static Drivetrain instance = new Drivetrain();
	
	public static Drivetrain getInstance(){
		return instance;
	}
    
	public CustomRobotDrive drive = new CustomRobotDrive(6,9,7,8,4,10);
	public Solenoid elevatorSolenoid = new Solenoid(0);
	public Solenoid grabberSolenoid = new Solenoid(1);
	
	
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs, int mode){
		drive.arcadeDrive(moveValue, rotateValue, squaredInputs, mode);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

