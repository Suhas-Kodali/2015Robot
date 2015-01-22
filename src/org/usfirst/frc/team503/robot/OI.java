package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.ElevatorDownCommand;
import org.usfirst.frc.team503.robot.commands.ElevatorUpCommand;
import org.usfirst.frc.team503.robot.commands.SetElevatorSolenoidCommand;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem.ElevatorSolenoidPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public static int mode = 1;
	
	public static int position = 0;
	
	public static boolean squaredInputs = false;
	
	static Joystick joystick = new Joystick(0);
	
	static JoystickButton elevatorButtonOpen = new JoystickButton(joystick, 1),
			elevatorButtonClose = new JoystickButton(joystick, 2),
			elevatorButtonMoveUp = new JoystickButton(joystick,3),
			elevatorButtonMoveDown = new JoystickButton(joystick,4);
	
	public static double getJoystickY(){
		return joystick.getRawAxis(5);
	}
	
	public static double getJoystickX(){
		return joystick.getRawAxis(4);
	}
	
	public static double getElevatorInput(){
		return joystick.getRawAxis(2);	
	}
	public static void init(){
		elevatorButtonOpen.whenPressed(new SetElevatorSolenoidCommand(ElevatorSolenoidPosition.OPEN));
		elevatorButtonClose.whenPressed(new SetElevatorSolenoidCommand(ElevatorSolenoidPosition.CLOSE));
		elevatorButtonMoveUp.whenPressed(new ElevatorUpCommand());
		elevatorButtonMoveDown.whenPressed(new ElevatorDownCommand());
	}
}
