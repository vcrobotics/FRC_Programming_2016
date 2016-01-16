package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;
import edu.wpi.first.wpilibj.*;

public class DriveTrain {
	
	private Victor leftFront, leftRear, rightFront, rightRear;
	
	public DriveTrain(){
		leftFront = new Victor (RobotMap.leftFrontMotor);
		rightFront = new Victor (RobotMap.rightFrontMotor);
		leftRear = new Victor (RobotMap.leftRearMotor);
		rightRear = new Victor (RobotMap.rightRearMotor);
	}
	
	public void tankDrive(double left, double right){
		leftFront.set(left);
		leftRear.set(left);
		rightFront.set(right);
		rightRear.set(right);
	}

	
}
