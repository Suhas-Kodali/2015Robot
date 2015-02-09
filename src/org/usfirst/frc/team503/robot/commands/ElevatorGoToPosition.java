package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorGoToPosition extends Command {
	
	int position;

    public ElevatorGoToPosition(int position) {
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(position > -1 && position < ElevatorSubsystem.getInstance().getPositions().length){
    		ElevatorSubsystem.setSetpoint(ElevatorSubsystem.getInstance().getPositions()[position]);
    		ElevatorSubsystem.pidEnable();
    	}else{
    		end();
    	}
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
