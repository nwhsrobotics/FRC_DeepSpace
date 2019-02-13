/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
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
  private static final int TIMEOUT_MS = 1000;
  private static double m_velocity_fps; //feet per second velocity
  private static double m_velocity_turn_rps;//rad per second
  private static double m_leftSpeed;
  private static double m_rightSpeed;
  private static final double WHEELBASE = 19.5/12; //inches on numerator, convert to feet
  private static double m_radius = WHEELBASE / 2;


  private static int m_maxAmps = 0;
  private static double m_maxIntegral = 0;
  private static final double REVOLUTIONSPERFOOT = 1/Math.PI;
  private static final double COUNTSPERREVOLUTION = 4096;
  private static final double SECONDSPER100MILLISECONDS = .1;
  private static final double MAXVELOCITY = 5; //fps
  private static final double MAX_TURN_RATE_DEG_PER_SEC = 15;
  private static final double RAD_PER_DEG = Math.PI/180;

  private double m_p_right = Robot.m_map.pidDriveRight("p");
  private double m_i_right = Robot.m_map.pidDriveRight("i");
  private double m_d_right = Robot.m_map.pidDriveRight("d");
  private double m_f_right = Robot.m_map.pidDriveRight("f");

  private double m_p_left = Robot.m_map.pidDriveLeft("p");
  private double m_i_left = Robot.m_map.pidDriveLeft("i");
  private double m_d_left = Robot.m_map.pidDriveLeft("d");
  private double m_f_left = Robot.m_map.pidDriveLeft("f");



  public DriveTrain(){
    //initialize + set objects created above
    m_frontleft = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_FRONTLEFT));
    m_backleft = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_BACKLEFT));
    

    m_frontright = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_FRONTRIGHT));
    m_backright = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_BACKRIGHT));
    
    //m_left.setInverted(true); invert left side

      
  }

  public void configTalons() {
    
  }

  public void update(double y, double z){
    
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
