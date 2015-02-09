package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *:^)
 */
public class RollerSubsystem extends Subsystem {
	
	private RollerSubsystem(){}
	private static RollerSubsystem instance = new RollerSubsystem();
	public static RollerSubsystem getInstance(){
		return instance;
	}
	Solenoid rollerSolenoid = new Solenoid(2);
	Relay rollerRelay = new Relay(0);
	
	public enum Direction{
		IN(Value.kForward), OUT(Value.kReverse), OFF(Value.kOff);
		private Value value;
		
		private Direction(Value value){
			this.value = value;
		}
	}
	
	public enum RollerSolenoidPosition{
        EXTEND(true), RETRACT(false);
        private boolean position;

        private RollerSolenoidPosition(boolean position){
                this.position = position;
        }
	}   
	public void setSolenoidPosition(RollerSolenoidPosition position){
		rollerSolenoid.set(position.position);
	}
	
	public void setRollerRelay(Direction direction){
		rollerRelay.set(direction.value);		
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

