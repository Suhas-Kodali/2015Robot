package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *:^)
 */
public class RollerSubsystem extends Subsystem {
	
	private RollerSubsystem(){}
	private static RollerSubsystem instance = new RollerSubsystem();
	public static RollerSubsystem getInstance(){
		return instance;
	}
	DoubleSolenoid rollerSolenoid = new DoubleSolenoid(4, 5);
	Talon leftTalon = new Talon(1);
	Talon rightTalon = new Talon(0);
	
	public static enum Direction{
		IN(1), OUT(-1), OFF(0);
		private int value;
		
		private Direction(int value){
			this.value = value;
		}
	}
	
	public static enum RollerSolenoidPosition{
        EXTEND(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward), RETRACT(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse);
        private edu.wpi.first.wpilibj.DoubleSolenoid.Value position;

        private RollerSolenoidPosition(edu.wpi.first.wpilibj.DoubleSolenoid.Value position){
                this.position = position;
        }
	}   
	public void setSolenoidPosition(RollerSolenoidPosition position){
		rollerSolenoid.set(position.position);
	}
	
	public void setRollerTalon(Direction direction){
		leftTalon.set(direction.value);		
		rightTalon.set(-direction.value);	
		SmartDashboard.putNumber("Speed", direction.value);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

