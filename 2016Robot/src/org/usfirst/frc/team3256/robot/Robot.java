
package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3256.robot.subsystems.Hanger;
import org.usfirst.frc.team3256.robot.subsystems.Intake;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import org.usfirst.frc.team3256.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain drivetrain;
	public static Compressor compressor;
	public static Hanger hanger;
	public static Intake intake;
	public static Shooter shooter;
	

    Command autonomousCommand;
	//Dashboard
	//SmartDashboard dash;
    //DriveTrain
    Command ShiftUp;
    Command ShiftDown;
    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command IntakeRollers;
    Command OuttakeRollers;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	drivetrain = new DriveTrain();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		hanger = new Hanger();
		intake = new Intake();
		shooter = new Shooter();
		ShiftUp = new ShiftUp();
		ShiftDown = new ShiftDown();
		IntakeIncrementIn = new IntakeIncrementIn(100,100);
		IntakeIncrementOut = new IntakeIncrementOut(-100,100);
		IntakeRollers = new IntakeRollers();
		OuttakeRollers = new OuttakeRollers();
		
		oi = new OI(0);
		
		DriveTrain.initGyro();
        DriveTrain.calibrateGyro();
        
        SmartDashboard.putData("ShiftUp", ShiftUp);
        SmartDashboard.putData("ShiftDown", ShiftDown);
        //SmartDashboard.putData("autonomousCommand", autonomousCommand);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) autonomousCommand.start();
       /* ShiftUp.start();
        ShiftDown.start();
        ShiftUp.start();
        ShiftDown.start();*/
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
        
        DriveTrain.resetGyro();
        ShiftUp.start();//Automatically puts robot in high gear
        //DriveTrain.sensitivityGyro();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	SmartDashboard.putString("Test", "Hello");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	
        if (OI.getRightBumper()){
        	ShiftDown.start();
        }
        else 
        	ShiftUp.start();
        
        OI.buttonA.whenActive(new IntakeRollers());
        OI.buttonY.whenActive(new OuttakeRollers());
        
    	DriveTrain.tankDrive(OI.getLeftY(),OI.getRightY());
    	
    	System.out.println(DriveTrain.getAngle());
    	
    	SmartDashboard.putNumber("Gyro", DriveTrain.getAngle());
    	SmartDashboard.putBoolean("BallIn", true);
    	SmartDashboard.putBoolean("Distance", true);
    	SmartDashboard.putBoolean("Angle", false);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
