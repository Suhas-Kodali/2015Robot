package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

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
    	Drivetrain.setSetpoint(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Drivetrain.getError() > 50 || Drivetrain.getError() < -50){
    		Drivetrain.getInstance().arcadeDrive(((Drivetrain.getError() > 0) ? 1 : -1)*Drivetrain.direction, 0, OI.squaredInputs); 
    	}else{
    		Drivetrain.pidEnable();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Drivetrain.onTarget() && Drivetrain.isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
