/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap.MapKeys;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import java.math.*;


/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private WPI_TalonSRX m_frontleft;
  private WPI_TalonSRX m_backleft;
  private WPI_TalonSRX m_frontright;
  private WPI_TalonSRX m_backright;
  private static final int TIMEOUT_MS = 1000;
  //feet per second velocity
  private static double m_velocity_fps;
  private static double m_velocity_turn_rps;//rad per second
  private static double m_left;
  private static double m_right;
  private static final double WHEELBASE = 15;
  private static double m_radius = WHEELBASE / 2;


  private static int m_maxAmps = 0;
  private static double m_maxIntegral = 0;
  private static final double COUNTSPERFOOT = 1800;
  private static final double MAXVELOCITY = 5;
  private static final double MAX_TURN_RATE_DEG_PER_SEC = 15;
  private static final double RAD_PER_DEG = Math.PI/180;



  public DriveTrain(){
    //initialize + set objects created above
    m_frontleft = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_FRONTLEFT));
    m_backleft = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_BACKLEFT));
    m_frontright = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_FRONTRIGHT));
    m_backright = new WPI_TalonSRX(Robot.m_map.getId(MapKeys.DRIVE_BACKRIGHT));

    configTalons();
    m_velocity_fps = 0;
    m_velocity_turn_rps = 0;

  }

  public void configTalons() {
    m_frontright.selectProfileSlot(0, 0);
    m_frontright.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m_frontright.setSensorPhase(false);

    m_frontleft.selectProfileSlot(0, 0);
    m_frontleft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m_frontleft.setSensorPhase(false);
    //_talon.configEncoderCodesPerRev(XXX), // if using FeedbackDevice.QuadEncoder
    //_talon.configPotentiometerTurns(XXX), // if using FeedbackDevice.AnalogEncoder or AnalogPot

	/* set closed loop gains in slot0 */
    m_frontright.config_kP(0, 0, TIMEOUT_MS);
    m_frontright.config_kI(0, 0, TIMEOUT_MS);
    m_frontright.config_kD(0, 0, TIMEOUT_MS);
    m_frontright.config_kF(0, 0, TIMEOUT_MS);

    m_frontleft.config_kP(0, 0, TIMEOUT_MS);
    m_frontleft.config_kI(0, 0, TIMEOUT_MS);
    m_frontleft.config_kD(0, 0, TIMEOUT_MS);
    m_frontleft.config_kF(0, 0, TIMEOUT_MS);

    m_frontright.configMaxIntegralAccumulator(0, m_maxIntegral, TIMEOUT_MS);
    m_frontright.configContinuousCurrentLimit(m_maxAmps, TIMEOUT_MS);
    m_frontright.configPeakCurrentLimit(m_maxAmps, TIMEOUT_MS);

    m_frontleft.configMaxIntegralAccumulator(0, m_maxIntegral, TIMEOUT_MS);
    m_frontleft.configContinuousCurrentLimit(m_maxAmps, TIMEOUT_MS);
    m_frontleft.configPeakCurrentLimit(m_maxAmps, TIMEOUT_MS);

    m_backleft.set(ControlMode.Follower, Robot.m_map.getId(MapKeys.DRIVE_BACKLEFT));
    m_backright.set(ControlMode.Follower, Robot.m_map.getId(MapKeys.DRIVE_BACKRIGHT));
  }

  public void update(double y, double z){
    m_velocity_fps = y * MAXVELOCITY;
    m_velocity_turn_rps = -z * (MAX_TURN_RATE_DEG_PER_SEC/RAD_PER_DEG);

    m_right = m_velocity_fps + m_velocity_turn_rps;
    m_left = m_velocity_fps - m_velocity_turn_rps;
  }

  @Override
  public void periodic() {
    m_frontleft.set(ControlMode.Velocity, m_left);
    m_frontleft.set(ControlMode.Velocity, m_right);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
