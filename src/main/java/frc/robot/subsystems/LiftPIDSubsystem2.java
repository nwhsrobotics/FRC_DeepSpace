package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class LiftPIDSubsystem2 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  static final int DEFAULT_TALON_ID = 10;
  static final int TALON_ID2 = 11;/**Enter the Second Talon Here */
  private static final int ENC_COUNT_PER_REV = 4096;
  private static final int SLOT_IDX = 0;
  private static final int PID_PRIMARY = 0;
  private static final int TALON_TIMEOUT_MS = 100;
  private static final int DEFAULT_CURR_LIMIT = 2;
  private static final double DEFAULT_P = 1.0;
  private static final double DEFAULT_MAX_INTEGRAL = 2.0 * DEFAULT_P;
  private static final double TRAVEL_PER_REVOLUTION = 10; // in inches
  int m_canId = DEFAULT_TALON_ID;
  int m_canId2 = DEFAULT_TALON_ID;
  WPI_TalonSRX m_talon;

  boolean m_enabled = false;

  int m_holdPosEnc = 0;  //position holding

  double m_p = 0.0;
  double m_i = 0.0;
  double m_d = 0.0;
  double m_maxIntegral = 0.0;
  int m_maxAmps = 1;
  
  
  
  public LiftPIDSubsystem2(){
    m_talon = new WPI_TalonSRX(m_canId);
    m_talon.selectProfileSlot(SLOT_IDX, PID_PRIMARY);

    m_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_PRIMARY, TALON_TIMEOUT_MS);
    m_talon.setSensorPhase(false);
    
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  private void configTalon() {
    ErrorCode status;
  
    status = m_talon.config_kP(SLOT_IDX, m_p, TALON_TIMEOUT_MS);
    //checkStatus(status, "error setting the P parameter");
  
    status = m_talon.config_kI(SLOT_IDX, m_i, TALON_TIMEOUT_MS);
    //checkStatus(status, "Error setting the I parameter");
  
    status = m_talon.config_kD(SLOT_IDX, m_d, TALON_TIMEOUT_MS);
    //checkStatus(status, "Error setting the D parameter**CHECK CODE CONFIRM NO TYPO**");
  
    m_talon.configMaxIntegralAccumulator(SLOT_IDX, m_maxIntegral, TALON_TIMEOUT_MS);
  
    m_talon.configContinuousCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);
    m_talon.configPeakCurrentLimit(0, TALON_TIMEOUT_MS);
  }
}
