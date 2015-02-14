package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardCommand extends Command {
	double seconds;
	Timer timer = new Timer();

    public DriveForwardCommand(double seconds) {
    	this.seconds = seconds;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.getInstance().arcadeDrive(1*Drivetrain.direction, 0, OI.squaredInputs);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return timer.get()>seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().arcadeDrive(0, 0, OI.squaredInputs);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
