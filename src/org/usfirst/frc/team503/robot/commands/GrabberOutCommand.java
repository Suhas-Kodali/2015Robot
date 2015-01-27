package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabberOutCommand extends Command {

    public GrabberOutCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!GrabberSubsystem.getInstance().getOutSwitch()) {
    		GrabberSubsystem.getInstance().grabberOut();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return GrabberSubsystem.getInstance().getOutSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	GrabberSubsystem.getInstance().grabberStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
