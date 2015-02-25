package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn90Command extends Command {
	double time;
	Timer timer = new Timer();

    public Turn90Command(double time) {
    	this.time = time;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Drivetrain.getInstance().arcadeDrive(0, -0.3, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get()>time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
