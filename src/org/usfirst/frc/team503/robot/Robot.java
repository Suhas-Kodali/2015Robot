
package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.SetElevatorSpeedCommand;
import org.usfirst.frc.team503.robot.commands.TeleopDriveCommand;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
    public void robotInit() {
    	OI.init();
    	SmartDashboard.putData(Drivetrain.getInstance());
        SmartDashboard.putData(ElevatorSubsystem.getInstance());
        SmartDashboard.putData(GrabberSubsystem.getInstance());
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	ElevatorSubsystem.getInstance().determinePosition();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    
    }

    public void teleopInit() {
    	(new TeleopDriveCommand()).start();
    	(new SetElevatorSpeedCommand()).start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
