package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  private WPI_TalonSRX m_motorup1;
  private WPI_TalonSRX m_motorup2;
  private SpeedControllerGroup m_lift;

  public LiftSubsystem(){
    m_motorup1 = new WPI_TalonSRX(1/**Enter the Talon Value Here */);
    m_motorup1.set(ControlMode.PercentOutput, 0.0);
    m_motorup2 = new WPI_TalonSRX(/**Enter the Talon Value Here */0);
    m_motorup2.set(ControlMode.PercentOutput,0.0);
    m_lift = new SpeedControllerGroup(m_motorup1, m_motorup2);
  
  }


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());


     
  }

  public void GoingUp() {
    m_lift.set(0.5);
  }

  public void GoingDown() {
    m_lift.set(-0.5);
  }
  public void StopLift(){
    m_lift.set(0);
  }
}