/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1482.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
  int axisThrottle = 1; // left y
  int axisSteer = 0; // left x
  int axisStrafeLeft = 2; // left trigger
  int axisStrafeRight = 3; // right trigger
  
  int buttonSpin = 5; // left bumper
  int buttonShoot = 6; // right bumper
  
  MecanumDrive drive;
  DifferentialDrive spin;
  DoubleSolenoid shoot;
  Joystick stick;
  
  
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	  // MAKE SURE THESE TALONS ARR IN ORDER
	  WPI_TalonSRX motorFrontLeft = new WPI_TalonSRX(0);
    WPI_TalonSRX motorRearLeft = new WPI_TalonSRX(1);
    WPI_TalonSRX motorFrontRight = new WPI_TalonSRX(2);
    WPI_TalonSRX motorRearRight = new WPI_TalonSRX(3);
    WPI_TalonSRX motorSpin = new WPI_TalonSRX(4);
    
    drive = new MecanumDrive(motorFrontLeft, motorRearLeft, motorFrontRight, motorRearRight);
    spin = new DifferentialDrive(motorSpin, motorSpin);
    shoot = new DoubleSolenoid(0, 1);
    stick = new Joystick(0);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	  double strafeLeft = stick.getRawAxis(axisStrafeLeft);
	  double strafeRight = stick.getRawAxis(axisStrafeRight);
	  
	  drive.driveCartesian(strafeLeft > strafeRight ? strafeLeft : strafeRight, stick.getRawAxis(axisThrottle), stick.getRawAxis(axisSteer));
	  spin.arcadeDrive(stick.getRawButton(buttonSpin) ? 1 : 0, 1);
	  shoot.set(stick.getRawButton(buttonShoot) ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {}
}
