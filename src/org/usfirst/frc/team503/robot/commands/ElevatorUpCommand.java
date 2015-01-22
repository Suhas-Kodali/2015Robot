package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem.ElevatorPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorUpCommand extends Command {
	

    public ElevatorUpCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OI.position<4){
    		ElevatorSubsystem.setSetpoint(ElevatorSubsystem.getPositions()[OI.position++]);
    	}
    	ElevatorSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (ElevatorSubsystem.onTarget() && ElevatorSubsystem.isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	ElevatorSubsystem.pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
