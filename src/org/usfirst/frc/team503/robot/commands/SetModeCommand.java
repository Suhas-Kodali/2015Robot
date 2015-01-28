package org.usfirst.frc.team503.robot.commands;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetModeCommand extends Command {
	
	int mode;
	Drivetrain drivetrain;
	
    public SetModeCommand(int mode) {
        this.mode = mode;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.mode=mode;
    	if(mode == 1){
    		Drivetrain.getInstance().setElevatorSolenoid(true);
    		Drivetrain.getInstance().setGrabberSolenoid(true);
    	}
    	else if(mode == 2){
    		Drivetrain.getInstance().setElevatorSolenoid(false);
    		Drivetrain.getInstance().setGrabberSolenoid(true);
    	}
    	else if(mode == 3){
    		Drivetrain.getInstance().setElevatorSolenoid(true);
    		Drivetrain.getInstance().setGrabberSolenoid(false);
    	}
    	else {
    		Drivetrain.getInstance().setElevatorSolenoid(true);
    		Drivetrain.getInstance().setGrabberSolenoid(true);
    	}
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
