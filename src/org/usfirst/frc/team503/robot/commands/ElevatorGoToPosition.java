package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.CustomRobotDrive;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorGoToPosition extends Command {
	
	int position;
	int positionsRunning;
	int penisSize;

    public ElevatorGoToPosition(int position) {
    	this.position = position;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(position > -1 && position < ElevatorSubsystem.getInstance().getPositions().length){
    		OI.positionCommandsRan++;
    		positionsRunning = OI.positionCommandsRan;
    		ElevatorSubsystem.setSetpoint(ElevatorSubsystem.getInstance().getPositions()[position]);
    		ElevatorSubsystem.pidEnable();
    	}else{
    		end();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = ElevatorSubsystem.getError();
    	SmartDashboard.putNumber("Elevator dte:", error);
    	if(Math.abs(error) > 1){
    		if(ElevatorSubsystem.getDistance() < 0 && position == 0){
    			CustomRobotDrive.getInstance().setElevatorSpeed(0); 
    		}
    		else if(Math.abs(error) > Math.min(Math.abs(ElevatorSubsystem.getRate()*3), 5)){ //rate*3 is usually a lot higher than 5
    			SmartDashboard.putBoolean("Elevator", true);
    			CustomRobotDrive.getInstance().setElevatorSpeed(error > 0 ? 0.75 : -0.75); 
    		}else{
    			SmartDashboard.putBoolean("Elevator", false);
    			CustomRobotDrive.getInstance().setElevatorSpeed(ElevatorSubsystem.getPIDLastOutput());
    		}
    	}else{
    		CustomRobotDrive.getInstance().setElevatorSpeed(0);
    	}
    	SmartDashboard.putBoolean("Elevator END", false);
    	if((ElevatorSubsystem.onTarget() && ElevatorSubsystem.isStopped())){
    		SmartDashboard.putBoolean("Elevator END", true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return OI.positionCommandsRan > positionsRunning;
    }
    // Called once after isFinished returns true
    protected void end() {
    	ElevatorSubsystem.pidDisable();
    	ElevatorSubsystem.getInstance().setSpeed(0);    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
