package org.usfirst.frc.team503.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestCommandGroup extends CommandGroup {
    
    public  TestCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new ElevatorGoToPosition(2));
    	addSequential(new WaitCommand(1.8));
    	addSequential(new DriveStraightCommand(26));
    	addParallel(new ElevatorGoToPosition(0));
    	addSequential(new WaitCommand(3));
    	addParallel(new ElevatorGoToPosition(1));
    	addSequential(new WaitCommand(1));
    	addSequential(new Turn90Command(1.8));
    	addSequential(new DriveStraightCommand(108));
    	
    }
}
