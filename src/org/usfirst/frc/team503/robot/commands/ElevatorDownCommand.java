package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDownCommand extends Command {

    public ElevatorDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OI.position > 0){
    		ElevatorSubsystem.getInstance().setSetpoint(ElevatorSubsystem.getInstance().getPositions()[OI.position--]);
    	}
    	ElevatorSubsystem.getInstance().pidEnable();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (ElevatorSubsystem.getInstance().onTarget() && ElevatorSubsystem.getInstance().isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	ElevatorSubsystem.getInstance().pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
