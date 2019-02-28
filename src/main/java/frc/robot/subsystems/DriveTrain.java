/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap.MapKeys;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**

* Add your docs here.
 */
public class DriveTrain extends Subsystem {
  //create CAN Talon SRX objects
  public WPI_TalonSRX m_frontleft;
  public WPI_TalonSRX m_backleft;
  public WPI_TalonSRX m_frontright;
  public WPI_TalonSRX m_backright;
  //create drive train and drive train sides objects
  private SpeedControllerGroup m_left;
  private SpeedControllerGroup m_right;
  private DifferentialDrive m_drive;

  private int m_maxAmps = 10;
  private final int TALON_TIMEOUT_MS = 500;



  public DriveTrain(){
    
  }
  public void initialize(){
    int frontLeftCanID = Robot.m_map.getId(MapKeys.DRIVE_FRONTLEFT);
    int backLeftCanID = Robot.m_map.getId(MapKeys.DRIVE_BACKLEFT);
    int frontRightCanID = Robot.m_map.getId(MapKeys.DRIVE_FRONTRIGHT);
    int backRightCanID = Robot.m_map.getId(MapKeys.DRIVE_BACKRIGHT);

    if ((frontLeftCanID != 0) && (backLeftCanID != 0) && (frontRightCanID != 0) && (backRightCanID != 0)){
      //initialize + set objects created above
      m_frontleft = new WPI_TalonSRX(frontLeftCanID);
      m_frontleft.configContinuousCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_frontleft.configPeakCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_backleft = new WPI_TalonSRX(backLeftCanID);
      m_backleft.configContinuousCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_backleft.configPeakCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_left = new SpeedControllerGroup(m_frontleft, m_backleft);

      
      m_frontright = new WPI_TalonSRX(frontRightCanID);
      m_frontright.configContinuousCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_frontright.configPeakCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_backright = new WPI_TalonSRX(backRightCanID);
      m_backright.configContinuousCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_backright.configPeakCurrentLimit(m_maxAmps,TALON_TIMEOUT_MS);
      m_right = new SpeedControllerGroup(m_frontright, m_backright);
      //m_left.setInverted(true); 
      m_drive = new DifferentialDrive(m_left, m_right);
    }
  }
  public void update(double y, double z){
    if (m_drive != null){
      m_drive.arcadeDrive(y, z);
    }
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
