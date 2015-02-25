
package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.DeterminePositionCommand;
import org.usfirst.frc.team503.robot.commands.ElevatorSpeedCommand;
import org.usfirst.frc.team503.robot.commands.SetModeCommand;
import org.usfirst.frc.team503.robot.commands.TeleopDriveCommand;
import org.usfirst.frc.team503.robot.commands.TestCommandGroup;
import org.usfirst.frc.team503.robot.subsystems.CustomRobotDrive;
import org.usfirst.frc.team503.robot.subsystems.Drivetrain;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.GrabberSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	DigitalInput testSensor = new DigitalInput(8);
	AnalogInput analogSensor = new AnalogInput(0);
	
    public void autonomousInit() {
    	(new SetModeCommand(1)).start();
    	(new DeterminePositionCommand()).start();
    	//(new Turn90Command(1.8)).start();
    	//(new DriveStraightCommand(50)).start();
    	(new TestCommandGroup()).start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("ELEVATORSOSOFD Error", ElevatorSubsystem.getError());
    	SmartDashboard.putNumber("Drivetrain Encoder", Drivetrain.getDistance());
    	SmartDashboard.putNumber("Elevator Enc", ElevatorSubsystem.getDistance());    
    	SmartDashboard.putNumber("ELEVATORSOFSOFF Output", ElevatorSubsystem.getPIDLastOutput());
    }

    public void teleopInit() {
    	(new SetModeCommand(1)).start();
    	(new DeterminePositionCommand()).start();
    	(new TeleopDriveCommand()).start();
    	(new ElevatorSpeedCommand()).start();
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
    	SmartDashboard.putNumber("Drivetrain Encoder", Drivetrain.getDistance());
    	SmartDashboard.putNumber("Elevator Enc", ElevatorSubsystem.getDistance());
    	SmartDashboard.putNumber("Encoder Rate", Drivetrain.getRate());
    	SmartDashboard.putNumber("Y", OI.getJoystickY());
    	SmartDashboard.putNumber("X", OI.getJoystickX());
    	SmartDashboard.putBoolean("DIGITAL VALUE", testSensor.get());
    	SmartDashboard.putNumber("ANALOG VALUE", analogSensor.getVoltage());
        Scheduler.getInstance().run();
        CustomRobotDrive.getInstance().setGrabberSpeed(OI.getStealerInput());
        GrabberSubsystem.getInstance().setLassoSpeed(OI.getLassoInput());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
