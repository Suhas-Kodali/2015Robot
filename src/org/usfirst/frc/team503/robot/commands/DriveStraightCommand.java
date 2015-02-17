package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command {
	double distance;

    public DriveStraightCommand(double inches) {
    	this.distance = inches;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.setSetpoint(distance);
    	Drivetrain.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Drivetrain.getError();
    	SmartDashboard.putNumber("dte:", error);
    	if(Math.abs(error) > Math.min(Math.abs(Drivetrain.getRate()*3), 18)){
    		SmartDashboard.putBoolean("DriveStraight", true);
    		Drivetrain.getInstance().arcadeDrive(error > 0 ? 0.5 : -0.5, 0, false); 
    	}else{
    		SmartDashboard.putBoolean("DriveStraight", false);
    		Drivetrain.getInstance().arcadeDrive(Drivetrain.getPIDLastOutput(), 0, false);
    	}
    	SmartDashboard.putBoolean("END", false);
    	if((Drivetrain.onTarget() && Drivetrain.isStopped())){
    		SmartDashboard.putBoolean("END", true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Drivetrain.onTarget() && Drivetrain.isStopped());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.pidDisable();
    	Drivetrain.getInstance().arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
