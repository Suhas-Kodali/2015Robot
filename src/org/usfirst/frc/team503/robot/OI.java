package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.ElevatorDownCommand;
import org.usfirst.frc.team503.robot.commands.ElevatorUpCommand;
import org.usfirst.frc.team503.robot.commands.SetElevatorClawCommand;
import org.usfirst.frc.team503.robot.commands.SetModeCommand;
import org.usfirst.frc.team503.robot.commands.SetRollerCommand;
import org.usfirst.frc.team503.robot.commands.SetRollerSolenoidCommand;
import org.usfirst.frc.team503.robot.subsystems.ElevatorSubsystem.ElevatorSolenoidPosition;
import org.usfirst.frc.team503.robot.subsystems.RollerSubsystem.Direction;
import org.usfirst.frc.team503.robot.subsystems.RollerSubsystem.RollerSolenoidPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public static boolean squaredInputs = true;
	
	static Joystick joystick = new Joystick(0);
	static Joystick leftJoystick = new Joystick(1);
	static Joystick rightJoystick = new Joystick(2);
	
	static JoystickButton elevatorButtonOpen = new JoystickButton(joystick, 10),
			elevatorButtonClose = new JoystickButton(joystick, 11),
			elevatorButtonMoveUp = new JoystickButton(joystick, 8),
			elevatorButtonMoveDown = new JoystickButton(joystick, 9),
			mode0Button = new JoystickButton(joystick, 7),
			mode1Button = new JoystickButton(joystick, 5),
			mode2Button = new JoystickButton(joystick, 6),
			rollerButtonExtend = new JoystickButton(joystick, 1),
			rollerButtonRetract = new JoystickButton(joystick, 2),
			rollerButtonIn = new JoystickButton(joystick, 3),
			rollerButtonOut = new JoystickButton(joystick, 4);
	
	
	public static double getJoystickY(){
		return joystick.getRawAxis(0);
	}
	
	public static double getJoystickX(){
		return joystick.getRawAxis(1);
	}
	
	public static double getElevatorInput(){
		return joystick.getRawAxis(5);	
	}
	
	public static boolean getRollerButtonIn(){
		return rollerButtonIn.get();
	}
	
	public static boolean getRollerButtonOut(){
		return rollerButtonOut.get();
	}
	
	public static void init(){
		elevatorButtonOpen.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.OPEN));
		elevatorButtonClose.whenPressed(new SetElevatorClawCommand(ElevatorSolenoidPosition.CLOSE));
		elevatorButtonMoveUp.whenPressed(new ElevatorUpCommand());
		elevatorButtonMoveDown.whenPressed(new ElevatorDownCommand());
		mode0Button.whenPressed(new SetModeCommand(0));
		mode1Button.whenPressed(new SetModeCommand(1));
		mode2Button.whenPressed(new SetModeCommand(2));
		rollerButtonExtend.whenPressed(new SetRollerSolenoidCommand(RollerSolenoidPosition.EXTEND));
		rollerButtonRetract.whenPressed(new SetRollerSolenoidCommand(RollerSolenoidPosition.RETRACT));
		rollerButtonIn.whenPressed(new SetRollerCommand(Direction.IN));
		rollerButtonOut.whenPressed(new SetRollerCommand(Direction.OUT));
	}
}

