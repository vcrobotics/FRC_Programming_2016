package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public  Autonomous() {
        setInterruptible(false);
        DriveTrain.resetGyro();
        DriveTrain.resetEncoders();
        addSequential(new CatapultWinchAutomatic());
        ///addSequential(new EngageBallActuators());
        addSequential(new ShiftUp());
        addSequential(new MoveFoward(5,0.75));
        addSequential(new IntakePosAuto());
        addSequential(new PIDMoveForward(150));
        ///addSequential(new DisengageBallActuators());
    	addSequential(new PIDTurn(25));
    	System.out.println("Ready to align");
        addSequential(new PIDTurnGeneric());
        System.out.println("After align");
    	/*//addSequential(new EngageBallActuators());
        addSequential(new PIDMoveForward(150));
    	//addSequential(new DisengageBallActuators());
    	System.out.println("Before MV Forward");
    	addSequential(new PIDMoveForward(45));
    	System.out.println("After MV Forward");
        System.out.println("DONEEEE");*/
    }
}