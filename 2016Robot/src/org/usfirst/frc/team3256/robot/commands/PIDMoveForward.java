package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDMoveForward extends Command {

	double Pos;
	double output;
	PIDController pid;
	
    public PIDMoveForward(double Pos) {
    	requires(Robot.drivetrain);
    	this.Pos=Pos;
    	setInterruptible(false);
    	pid=new PIDController(0.0105,0.0,0.0261);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.ticksToInches(DriveTrain.getRightEncoder()), Pos);
    	DriveTrain.setLeftMotorSpeed(-output);
    	DriveTrain.setRightMotorSpeed(output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(DriveTrain.ticksToInches(DriveTrain.getRightEncoder()), Pos)<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
