package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	static Joystick joystick = new Joystick(0);
	
	/*
	public OI(int port) {
        joystick = new Joystick(port);
    }
	*/
	Button buttonA = new JoystickButton(joystick, 1);
    Button buttonB = new JoystickButton(joystick, 2);
	
	public double getLeftY(){return joystick.getRawAxis(1);}
    public double getLeftX(){return joystick.getRawAxis(2);}
    public double getRightY(){return joystick.getRawAxis(5);}
    public double getRightX(){return joystick.getRawAxis(4);}
    public boolean getButtonA(){return joystick.getRawButton(1);}
    public boolean getButtonB(){return joystick.getRawButton(2);}
	
		
}

