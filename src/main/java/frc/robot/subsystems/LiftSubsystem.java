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
public class LiftSubsystem extends Subsystem {
  private WPI_TalonSRX m_motorup1;
  private WPI_TalonSRX m_motorup2;
  public LiftSubsystem(){
    m_motorup1 = new WPI_TalonSRX(m_canId);
    m_motorup1.set(ControlMode.PercentOutput, 0.0);
    m_motorup2 = new WPI_TalonSRX(m_canId2);
    m_motorup2.set(ControlMode.PercentOutput,0.0);
  }
  
  //Using the PID Controller group with only one talon right now
  //declaring all of the variables in the beginning 
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

  //create an encoder count (ticks) ---> distance (inches), for a new vairbale 
  
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

  public void init(){
    readPreferences();

    m_talon = new WPI_TalonSRX(m_canId);
    m_talon.selectProfileSlot(SLOT_IDX, PID_PRIMARY);

    m_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PID_PRIMARY, TALON_TIMEOUT_MS);
    m_talon.setSensorPhase(false);

    configTalon();
  }

  

  @Override
  public void initDefaultCommand() {
    /**Do nothing */ 
  }
  
  @Override
  public void periodic(double inches){
    double holdPosDeg = Robot.m_oi.readPositionDeg();

    m_holdPosEnc = degToEncoder(holdPosDeg);
    if (!m_enabled) {
      m_talon.set(ControlMode.Disabled, 0.0);
    }
    else {
      m_talon.set(ControlMode.Position, inchesToEncoder(inches));
    }

    double actualPosDeg = encToDeg(m_talon.getSelectedSensorPosition(PID_PRIMARY));
    double current = m_talon.getOutputCurrent();

    SmartDashboard.putNumber("target", holdPosDeg);
    SmartDashboard.putNumber("actual", actualPosDeg);
    SmartDashboard.putNumber("hold current", current);
    SmartDashboard.putBoolean("hold active", m_enabled);
  }
public void enable(boolean enabled){
  System.out.printf("setting enabled: %b\n", enabled);
  m_enabled = enabled;

  ErrorCode status = m_talon.setSelectedSensorPosition(0, PID_PRIMARY, TALON_TIMEOUT_MS);
  checkStatus(status, "setSelectedSensorPosition failed.");

  status = m_talon.setIntegralAccumulator(0.0, PID_PRIMARY, TALON_TIMEOUT_MS);
  checkStatus(status, "Error clearing integral accumulator.");
}

public boolean isEnabled(){
  System.out.printf("Checking enabled: %b\n", m_enabled);
  return m_enabled;
}

public void UpdateParameters(){
  readPreferences();
  configTalon();
}

private void configTalon() {
  ErrorCode status;

  status = m_talon.config_kP(SLOT_IDX, m_p, TALON_TIMEOUT_MS);
  checkStatus(status, "error setting the P parameter");

  status = m_talon.config_kI(SLOT_IDX, m_i, TALON_TIMEOUT_MS);
  checkStatus(status, "Error setting the I parameter");

  status = m_talon.config_kD(SLOT_IDX, m_d, TALON_TIMEOUT_MS);
  checkStatus(status, "Error setting the D parameter**CHECK CODE CONFIRM NO TYPO**");

  m_talon.configMaxIntegralAccumulator(SLOT_IDX, m_maxIntegral, TALON_TIMEOUT_MS);

  m_talon.configContinuousCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);
  m_talon.configPeakCurrentLimit(0, TALON_TIMEOUT_MS);
}

private void readPreferences() {
  Preferences prefs;
  prefs = Preferences.getInstance();
  
  m_canId = prefs.getInt("holdPos_canId", DEFAULT_TALON_ID);
  m_p = prefs.getDouble("HoldPos_p", DEFAULT_P);
  m_i = prefs.getDouble("holdPis_i", 0.0);
  m_d = prefs.getDouble("holdPos_limit", DEFAULT_CURR_LIMIT);
  m_maxIntegral = prefs.getDouble("holdPos_maxIntegral", DEFAULT_MAX_INTEGRAL);

  System.out.printf("Updated parameters: \n");
  System.out.printf("     holdPos_canId: %d\n", m_canId);
  System.out.printf("     holdPos_p: %f\n", m_p);
}  
  private void checkStatus(ErrorCode status, String msg) {
    if (status != ErrorCode.OK){
      System.out.printf("Error: %d, %s\n", status, msg);
    }
  }

  private double encToDeg(int enc) {
    return (double)(enc *360.0 / ENC_COUNT_PER_REV);
  }

  private int degToEncoder(double degrees) {
    return (int)(ENC_COUNT_PER_REV * degrees / 360.0);
  }
  public double inchesToEncoder(double inches) {
    return (int)((inches/TRAVEL_PER_REVOLUTION)*ENC_COUNT_PER_REV);
  }

  public void GoingUp() {
    m_motorup1.set(0.5);
    m_motorup2.set(0.5);
    
  }


  public void GoingDown() {
    m_motorup1.set(-0.5);
    m_motorup2.set(-0.5);

  }
  public void StopLift(){
    m_motorup1.set(0);
    m_motorup2.set(0);
  }
}