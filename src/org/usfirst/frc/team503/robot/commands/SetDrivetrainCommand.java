package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetDrivetrainCommand extends Command {
	double distance;

    public SetDrivetrainCommand(double distance) {
    	this.distance = distance;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.getInstance().setSetpoint(distance);
    	
    	Drivetrain.getInstance().pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Drivetrain.getInstance().onTarget() && Drivetrain.getInstance().isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
