/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

<<<<<<< HEAD
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
=======
import com.ctre.phoenix.motorcontrol.ControlMode;
>>>>>>> b393bda422fc1b0324a7d5544c2f8f58fd646cab
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
=======
>>>>>>> b393bda422fc1b0324a7d5544c2f8f58fd646cab
import frc.robot.RobotMap;
import frc.robot.commands.SlideCommand;
/**
 * Add your docs here.
 */
public class Slide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static final int ENC_COUNT_PER_REV = 4096;

  private static final int SLOT_IDX = 0;

  private static final int PID_PRIMARY = 0;

  private static final int TALON_TIMEOUT_MS = 100;

  private static final double DEFAULT_P = 1;
  private static final int DEFAULT_CURR_LIMIT = 0;
  private static final double DEFAULT_MAX_INTEGRAL = 0.0 * DEFAULT_P;

  int m_canId = RobotMap.slideCanId();
  WPI_TalonSRX m_slide;
  

  int m_holdPosEnc = 0;

  double m_p = 1.0;
  double m_i = 0.0;
  double m_d = 0.0;
  double m_maxIntegral = 0.5;
  int m_maxAmps = 2;

  public Slide(){

<<<<<<< HEAD
    m_slide = new WPI_TalonSRX(RobotMap.slideCanId());

  
  }
  
  public void init() {
    m_slide = new WPI_TalonSRX(RobotMap.slideCanId());
    m_slide.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_PRIMARY, TALON_TIMEOUT_MS);
    m_slide.setSensorPhase(false);
    configTalon();

=======
    //m_slide = new WPI_TalonSRX(RobotMap.slideCanId());
  }
  
  public void update(double x){
    if (m_slide != null) {
      m_slide.set(ControlMode.PercentOutput, x);
    }
>>>>>>> b393bda422fc1b0324a7d5544c2f8f58fd646cab
  }
  

<<<<<<< HEAD
  private void configTalon() {
  

    // write PID values
    m_slide.config_kP(SLOT_IDX, m_p, TALON_TIMEOUT_MS);
    
    m_slide.config_kI(SLOT_IDX, m_i, TALON_TIMEOUT_MS);
    
    m_slide.config_kD(SLOT_IDX, m_d, TALON_TIMEOUT_MS);
    
    // set max integrator accumulator
    m_slide.configMaxIntegralAccumulator(SLOT_IDX, m_maxIntegral, TALON_TIMEOUT_MS);
    // write current limit to talon.
    m_slide.configContinuousCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);
    m_slide.configPeakCurrentLimit(0, TALON_TIMEOUT_MS);
  }

  private int degToEncoder(double degrees) {
    return (int)(ENC_COUNT_PER_REV * degrees / 360.0); 
  }

 

=======
  public void slideStop(){
    if (m_slide != null) {
      m_slide.set(0);
    }
  }
  
>>>>>>> b393bda422fc1b0324a7d5544c2f8f58fd646cab
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SlideCommand());
  }
  
  

  public void periodic() {
    double holdPosDeg = Robot.m_oi.readPositionDeg();

    // Convert to encoder ticks
    m_holdPosEnc = degToEncoder(holdPosDeg);

    m_slide.set(ControlMode.Position, m_holdPosEnc );

  }



}