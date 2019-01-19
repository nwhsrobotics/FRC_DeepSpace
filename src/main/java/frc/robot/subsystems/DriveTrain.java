/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  //create CAN Talon SRX objects
  private WPI_TalonSRX m_frontleft;
  private WPI_TalonSRX m_backleft;
  private WPI_TalonSRX m_frontright;
  private WPI_TalonSRX m_backright;
  //create drive train and drive train sides objects
  private SpeedControllerGroup m_left;
  private SpeedControllerGroup m_right;
  private DifferentialDrive m_drive;

  public DriveTrain(){
    //initialize + set objects created above
    m_frontleft = new WPI_TalonSRX(11);
    m_backleft = new WPI_TalonSRX(13);
    m_left = new SpeedControllerGroup(m_frontleft, m_backleft);

    m_frontright = new WPI_TalonSRX(10);
    m_backright = new WPI_TalonSRX(7);
    m_right = new SpeedControllerGroup(m_frontright, m_backright);
    // m_left.setInverted(true);  invert left motor

    m_drive = new DifferentialDrive(m_left, m_right);
    m_drive.arcadeDrive(0.0, 0.0);

  }

  public void update(){
    //this method will be used to control the drive train
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
