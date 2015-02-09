package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.RollerSubsystem;
import org.usfirst.frc.team503.robot.subsystems.RollerSubsystem.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRollerCommand extends Command {
	RollerSubsystem instance = RollerSubsystem.getInstance();
	Direction direction;

    public SetRollerCommand(Direction direction) {
    	this.direction = direction;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RollerSubsystem.getInstance().setRollerRelay(direction);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !OI.getRollerButtonIn() && !OI.getRollerButtonOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RollerSubsystem.getInstance().setRollerRelay(Direction.OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
