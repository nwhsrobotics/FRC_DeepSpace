/** package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap.MapKeys;
import frc.robot.commands.SlideCommand;

import java.lang.Math;


public class SlideSubsystem extends Subsystem {
  
  public TalonSRX m_motorSlide;


  private double m_speed_ips; //inches per second
  private double m_position_in; //desired position in inches
  private double m_position_counts; 
  private static final double MAXSPEED = 5.0; //inches per second
  private static final double AUTOSLIDESPEED = 2.0; //inches per second
  private static final double SECONDS_PER_TICK = .02; // seconds per encoder tic
  private static final double COUNTS_PER_INCH = 2560; // encoder counts per inch
  private static final int TALON_TIMEOUT_MS = 1000; 
  private static final double DISTANCE_PER_TICK = AUTOSLIDESPEED * SECONDS_PER_TICK; // inches travelled per encoder tick

  private static final double POSITIVE_LIMIT_IN = 4.5;
  private static final double NEGATIVE_LIMIT_IN = -4.5;

  Preferences prefs = Preferences.getInstance();

  private double m_p;//Robot.m_map.pidSlideMotor("p");
  private double m_i;//Robot.m_map.pidSlideMotor("i");
  private double m_d;//Robot.m_map.pidSlideMotor("d");
  private double m_maxIntegral = 1.0;
  private int m_maxAmps = 2;

  private boolean m_autoActiveslide; //is auto active?
  private double m_autoDistance; //distance to travel autonomously

  //private double m_current; //current draw of the talon from the PDP

  public SlideSubsystem(){
    m_position_in = 0.0;
    m_speed_ips = 0.0;
    m_autoActiveslide = false;
    m_autoDistance = 0;

  }
  public void initialize(){
    int canID = Robot.m_map.getId(MapKeys.SLIDE);
    if (canID != 0){
      m_motorSlide = new TalonSRX(canID);
      setOutput(0.0);
      configTalons();
    }
  }

  public void Initialize(){

    Preferences prefs = Preferences.getInstance();

    m_p = prefs.getDouble("Slide_P_Value", 1.0);//Robot.m_map.pidSlideMotor("p");
    m_i = prefs.getDouble("Slide_I_Value", 0.001);//Robot.m_map.pidSlideMotor("i");
    m_d = prefs.getDouble("Slide_D_Value", 0.0);//Robot.m_map.pidSlideMotor("d");

    m_motorSlide = new TalonSRX(Robot.m_map.getId(MapKeys.SLIDE));
    setOutput(0.0);
    configTalons();

  }

  public void setOutput(double output) {
    if ((m_motorSlide != null)) {
      m_motorSlide.set(ControlMode.PercentOutput, output);
    }
  }

  private void configTalons() {
    if ((m_motorSlide == null)) {
      return;
    }

    m_motorSlide.selectProfileSlot(0, 0);
    m_motorSlide.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TALON_TIMEOUT_MS);
    m_motorSlide.setSensorPhase(true);

    m_motorSlide.config_kP(0, m_p, TALON_TIMEOUT_MS);
    m_motorSlide.config_kI(0, m_i, TALON_TIMEOUT_MS);
    m_motorSlide.config_kD(0, m_d, TALON_TIMEOUT_MS);

    m_motorSlide.configMaxIntegralAccumulator(0, m_maxIntegral, TALON_TIMEOUT_MS);
    m_motorSlide.configContinuousCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);
    m_motorSlide.configPeakCurrentLimit(m_maxAmps, TALON_TIMEOUT_MS);

    m_motorSlide.setSelectedSensorPosition(0, 0 , TALON_TIMEOUT_MS); //sets current pos to be 0
    m_position_counts = 0;
    m_motorSlide.setIntegralAccumulator(0);
    m_motorSlide.set(ControlMode.Position, m_position_counts); // moves motor to 0

 
  }

  /** public void slideToBottom() {
    current = Robot.m_pdp.getCurrent(14);
    while(current)
    setOutput(-0.5);
  } 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SlideCommand());
     
  }
  

  public void update(double x) {
    m_speed_ips = x * MAXSPEED;
    //in the commands, converts joystick to motor speed
  }

  @Override
  public void periodic() {
    //if auto is active, do second if loop, if not, revert to manual position
    if(m_autoActiveslide) {

      if (Math.abs(m_autoDistance) < Math.abs(DISTANCE_PER_TICK)) { //if there is only a small distance left to travel, finishes auto move
        m_position_in += m_autoDistance;
        m_autoDistance = 0;
        m_autoActiveslide = false;
      }else{
        if (m_autoDistance > 0) {
          m_position_in += DISTANCE_PER_TICK; 
          m_autoDistance -= DISTANCE_PER_TICK;
        } else if (m_autoDistance < 0) {
          m_position_in -= DISTANCE_PER_TICK;
          m_autoDistance += DISTANCE_PER_TICK;
        }
        
      }
    } else {
      m_position_in += m_speed_ips * SECONDS_PER_TICK;
    }
    
    if (m_position_in > POSITIVE_LIMIT_IN){
      m_position_in = POSITIVE_LIMIT_IN;
    }

    if (m_position_in < NEGATIVE_LIMIT_IN){
      m_position_in = NEGATIVE_LIMIT_IN;
    }

    m_position_counts = m_position_in * COUNTS_PER_INCH; //converts desired position to counts
    if (m_motorSlide != null) { //moves tha motor to desired position
      m_motorSlide.set(ControlMode.Position, m_position_counts);
    }

  }
public void startAutomoveSlide(double position_in) {
  if (m_autoActiveslide) {
    //cancels auto move if previous one was still active when this method is called again
    m_autoActiveslide = false;
    m_autoDistance = 0;
  } else {
    //activate auto slide
    m_autoActiveslide = true;
    m_autoDistance = position_in - m_position_in; //sets auto distance to travel to the desired - current desired distance
  }
}
public boolean autoMoveFinishedSlide() {
	return !m_autoActiveslide;
}

public void set0position() {
  m_motorSlide.setSelectedSensorPosition(0);
}
public void slideResetCommand() {
  startAutomoveSlide(0.0);
}

  
} */
//Power Time Starts here. Encoder code commented out above.
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap.MapKeys;
import frc.robot.commands.SlideCommand;
/**
 * Add your docs here.
 */
public class SlideSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX m_motorSlide;
  public SlideSubsystem(){
  }

  public void initialize(){
    int canID = Robot.m_map.getId(MapKeys.SLIDE);
    if (canID != 0){
      m_motorSlide = new TalonSRX(canID);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SlideCommand());
  }
  
  public void update(double x){
      m_motorSlide.set(ControlMode.PercentOutput, x);
  }

  public void slideStop(){
    m_motorSlide.set(ControlMode.PercentOutput, 0);
  }
  
}
