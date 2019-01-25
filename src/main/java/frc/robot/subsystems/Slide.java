/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SlideForward;
import frc.robot.commands.SlideReposition;
import frc.robot.commands.SlideStop;
import frc.robot.Robot;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class Slide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX m_slide;
  private static final int ENC_COUNT_PER_REV = 4096;

  private static final int SLOT_IDX = 0;

  private static final int PID_PRIMARY = 0;

  private static final int TALON_TIMEOUT_MS = 100;

  private static final double DEFAULT_P = 1.0;
  private static final int DEFAULT_CURR_LIMIT = 2;
  private static final double DEFAULT_MAX_INTEGRAL = 2.0 * DEFAULT_P;

  int m_canId = RobotMap.slideCanId();
  WPI_TalonSRX m_talon;
  
  boolean m_enabled = false; 
  int m_holdPosEnc = 0;

  double m_p = 0.0;
  double m_i = 0.0;
  double m_d = 0.0;
  double m_maxIntegral = 0.0;
  int m_maxAmps = 1;

  public Slide(){

    m_slide = new WPI_TalonSRX(RobotMap.slideCanId());

  
  }
  
  public void init() {
    readPreferences();
    m_talon = new WPI_TalonSRX(RobotMap.slideCanId());
    m_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_PRIMARY, TALON_TIMEOUT_MS);
    m_talon.setSensorPhase(false);
    configTalon();

  }

  public void SlideForward(){

    m_slide.set(0.5);

  }

  public void SlideStop(){

    m_slide.set(0);

  }

  public void SlideReposition(){

    

  }

  public void enable(boolean enabled) {
    System.out.printf("setting enabled: %b\n", enabled);
    m_enabled = enabled;
    ErrorCode status = m_talon.setSelectedSensorPosition(0, PID_PRIMARY, TALON_TIMEOUT_MS);
    checkStatus(status, "setSelectedSensorPosition failed.");
    status = m_talon.setIntegralAccumulator(0.0, PID_PRIMARY, TALON_TIMEOUT_MS);
    checkStatus(status, "Error clearing integral accumulator.");
  }

  public boolean isEnabled() {
    System.out.printf("Checking enabled: %b\n", m_enabled);
    return m_enabled;
  }
  public void updateParameters() {
    // Read PID params from Preferences
    readPreferences();
    configTalon();
  }

  private void readPreferences() {
    Preferences prefs;

    prefs = Preferences.getInstance();

    // Read settings from SmartDashboard preferences control.
    m_canId = prefs.getInt("holdPos_canId", RobotMap.slideCanId());
    m_p = prefs.getDouble("holdPos_p", DEFAULT_P);
    m_i = prefs.getDouble("holdPos_i", 0.0);
    m_d = prefs.getDouble("holdPos_d", 0.0);
    m_maxAmps = prefs.getInt("holdPos_limit", DEFAULT_CURR_LIMIT);
    m_maxIntegral = prefs.getDouble("holdPos_maxIntegral", DEFAULT_MAX_INTEGRAL);

    // Print message to confirm update happened.
    System.out.printf("Updated params:\n");
    System.out.printf("    holdPos_canId: %d\n", m_canId);
    System.out.printf("    holdPos_p: %f\n", m_p);
  }

  private void configTalon() {
    ErrorCode status;

    // write PID values
    status = m_talon.config_kP(SLOT_IDX, m_p, TALON_TIMEOUT_MS);
    checkStatus(status, "Error setting P parameter.");
    status = m_talon.config_kI(SLOT_IDX, m_i, TALON_TIMEOUT_MS);
    checkStatus(status, "Error setting I parameter.");
    status = m_talon.config_kD(SLOT_IDX, m_d, TALON_TIMEOUT_MS);
    checkStatus(status, "Error setting P parameter.");
    // set max integrator accumulator
    m_talon.configMaxIntegralAccumulator(SLOT_IDX, m_maxIntegral, TALON_TIMEOUT_MS);
    // write current limit to talon.
    m_talon.configContinuousCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);
    m_talon.configPeakCurrentLimit(0, TALON_TIMEOUT_MS);
  }

  private int degToEncoder(double degrees) {
    return (int)(ENC_COUNT_PER_REV * degrees / 360.0); 
  }

  // Convert encoder units to degrees.
  private double encToDeg(int enc) {
    return (double)(enc * 360.0 / ENC_COUNT_PER_REV);
  }

  // Prints error messages when certain calls fail.
  private void checkStatus(ErrorCode status, String msg) {
    if (status != ErrorCode.OK) {
      System.out.printf("Error: %d, %s\n", status, msg);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  @Override
  public void periodic() {
    double holdPosDeg = Robot.m_oi.readPositionDeg();

    // Convert to encoder ticks
    m_holdPosEnc = degToEncoder(holdPosDeg);

    // Update motor output 
    if (!m_enabled) {
      m_slide.set(ControlMode.Disabled, 0.0);
    }
    else {
      m_slide.set(ControlMode.Position, m_holdPosEnc);
    }

    // Read Talon position and current
    double actualPosDeg = encToDeg(m_slide.getSelectedSensorPosition(PID_PRIMARY));
    double current = m_slide.getOutputCurrent();

    // update displayed position and current
    // On smartdashboard, add these indicators.
    SmartDashboard.putNumber("target", holdPosDeg);
    SmartDashboard.putNumber("actual", actualPosDeg);
    SmartDashboard.putNumber("hold current", current);
    SmartDashboard.putBoolean("hold active", m_enabled);
  }
}