
package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDriveCommand extends Command {

    public TeleopDriveCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drivetrain.getInstance().arcadeDrive(OI.getJoystickY(), OI.getJoystickX(), OI.squaredInputs, OI.mode);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drivetrain.getInstance().arcadeDrive(0, 0, OI.squaredInputs, OI.mode);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
