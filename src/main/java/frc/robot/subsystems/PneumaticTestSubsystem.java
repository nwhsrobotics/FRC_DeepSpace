/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.EnumMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class PneumaticTestSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid m_FrontleftLargePiston;
  private DoubleSolenoid m_FrontrightLargePiston;


  enum ClimbState {
    IDLE,
    DESCEND_S1, DESCEND_S2, DESCEND_S3, DESCEND_S4,
    CLIMB_L2_S1, CLIMB_L2_S2, CLIMB_L2_S3, CLIMB_L2_S4,
    CLIMB_L3_S1, CLIMB_L3_S2, CLIMB_L3_S3, CLIMB_L3_S4,
  }
  private ClimbState m_climbState = ClimbState.IDLE;

  private static final EnumMap<ClimbState, ClimbState> nextStageMap = new EnumMap<>(ClimbState.class);
  private static final EnumMap<ClimbState, ClimbState> prevStageMap = new EnumMap<>(ClimbState.class);


  private int m_timeLeft;
  
  /*
  private DoubleSolenoid m_BackrightLargePiston;
  private DoubleSolenoid m_BackleftLargePiston;


  private DoubleSolenoid m_FrontleftSmallPiston;
  private DoubleSolenoid m_FrontrightSmallPiston;
  private DoubleSolenoid m_BackrightSmallPiston;
  private DoubleSolenoid m_BackleftSmallPiston;


  private TalonSRX m_MiniDrive;

*/
  public PneumaticTestSubsystem() {

    nextStageMap.put(ClimbState.IDLE, ClimbState.IDLE);

    nextStageMap.put(ClimbState.DESCEND_S1, ClimbState.DESCEND_S2);
    nextStageMap.put(ClimbState.DESCEND_S2, ClimbState.DESCEND_S3);
    nextStageMap.put(ClimbState.DESCEND_S3, ClimbState.DESCEND_S4);
    nextStageMap.put(ClimbState.DESCEND_S4, ClimbState.IDLE);

    nextStageMap.put(ClimbState.CLIMB_L2_S1, ClimbState.CLIMB_L2_S2);
    nextStageMap.put(ClimbState.CLIMB_L2_S2, ClimbState.CLIMB_L2_S3);
    nextStageMap.put(ClimbState.CLIMB_L2_S3, ClimbState.CLIMB_L2_S4);
    nextStageMap.put(ClimbState.CLIMB_L2_S4, ClimbState.IDLE);

    nextStageMap.put(ClimbState.CLIMB_L3_S1, ClimbState.CLIMB_L3_S2);
    nextStageMap.put(ClimbState.CLIMB_L3_S2, ClimbState.CLIMB_L3_S3);
    nextStageMap.put(ClimbState.CLIMB_L3_S3, ClimbState.CLIMB_L3_S4);
    nextStageMap.put(ClimbState.CLIMB_L3_S4, ClimbState.IDLE);

    prevStageMap.put(ClimbState.IDLE, ClimbState.IDLE);

    prevStageMap.put(ClimbState.DESCEND_S1, ClimbState.IDLE);
    prevStageMap.put(ClimbState.DESCEND_S2, ClimbState.DESCEND_S1);
    prevStageMap.put(ClimbState.DESCEND_S3, ClimbState.DESCEND_S2);
    prevStageMap.put(ClimbState.DESCEND_S4, ClimbState.DESCEND_S3);

    prevStageMap.put(ClimbState.CLIMB_L2_S1, ClimbState.IDLE);
    prevStageMap.put(ClimbState.CLIMB_L2_S2, ClimbState.CLIMB_L2_S1);
    prevStageMap.put(ClimbState.CLIMB_L2_S3, ClimbState.CLIMB_L2_S2);
    prevStageMap.put(ClimbState.CLIMB_L2_S4, ClimbState.CLIMB_L2_S3);

    prevStageMap.put(ClimbState.CLIMB_L3_S1, ClimbState.IDLE);
    prevStageMap.put(ClimbState.CLIMB_L3_S2, ClimbState.CLIMB_L3_S1);
    prevStageMap.put(ClimbState.CLIMB_L3_S3, ClimbState.CLIMB_L3_S2);
    prevStageMap.put(ClimbState.CLIMB_L3_S4, ClimbState.CLIMB_L3_S3);

    m_FrontleftLargePiston = new DoubleSolenoid(RobotMap.PcmCanID,RobotMap.SolenoidPort1,RobotMap.SolenoidPort2);
    m_FrontrightLargePiston = new DoubleSolenoid(RobotMap.PcmCanID,RobotMap.SolenoidPort3,RobotMap.SolenoidPort4);
    
    /*
    m_BackleftLargePiston = new DoubleSolenoid(1,1,1);
    m_BackrightLargePiston = new DoubleSolenoid(1,1,1);
    

    m_FrontleftSmallPiston = new DoubleSolenoid(1,1,1);
    m_FrontrightSmallPiston = new DoubleSolenoid(1,1,1);
    m_BackleftSmallPiston = new DoubleSolenoid(1,1,1);
    m_BackrightSmallPiston = new DoubleSolenoid(1,1,1);
   
    m_MiniDrive = new TalonSRX(1);
    */
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }

  public void startDescend() {

    m_climbState = ClimbState.DESCEND_S1;

  }

  public void startL2Ascend() {

    m_climbState = ClimbState.CLIMB_L2_S1;

  }

  public void startL3Ascend() {

    m_climbState = ClimbState.CLIMB_L3_S1;

  }

  public void nextStage() {

    m_climbState = nextStageMap.get(m_climbState);

  }

  public void prevStage() {

    m_climbState = prevStageMap.get(m_climbState);

  }


  /*
  public void ExtendAllLarge() {

    m_FrontleftLargePiston.set(DoubleSolenoid.Value.kForward);
    m_FrontrightLargePiston.set(DoubleSolenoid.Value.kForward);
    m_BackleftLargePiston.set(DoubleSolenoid.Value.kForward);
    m_BackrightLargePiston.set(DoubleSolenoid.Value.kForward);



  }

  public void RetractAllLarge() {

    m_FrontleftLargePiston.set(DoubleSolenoid.Value.kReverse);
    m_FrontrightLargePiston.set(DoubleSolenoid.Value.kReverse);
    m_BackleftLargePiston.set(DoubleSolenoid.Value.kReverse);
    m_BackrightLargePiston.set(DoubleSolenoid.Value.kReverse);

  }

  */

  public void ExtendFrontLarge() {

    m_FrontleftLargePiston.set(DoubleSolenoid.Value.kForward);
    m_FrontrightLargePiston.set(DoubleSolenoid.Value.kForward);

  }

  public void RetractFrontLarge() {

    m_FrontleftLargePiston.set(DoubleSolenoid.Value.kReverse);
    m_FrontrightLargePiston.set(DoubleSolenoid.Value.kReverse);


  }

  /*

  public void ExtendBackLarge() {

    m_BackleftLargePiston.set(DoubleSolenoid.Value.kForward);
    m_BackleftLargePiston.set(DoubleSolenoid.Value.kForward);


  }

  public void RetractBackLarge() {

    m_BackleftLargePiston.set(DoubleSolenoid.Value.kReverse);
    m_BackrightLargePiston.set(DoubleSolenoid.Value.kReverse);


  }

  public void ExtendFrontSmall() {

    m_FrontleftSmallPiston.set(DoubleSolenoid.Value.kForward);
    m_FrontrightSmallPiston.set(DoubleSolenoid.Value.kForward);


  }

  public void RetractFrontSmall() {

    m_FrontleftSmallPiston.set(DoubleSolenoid.Value.kReverse);
    m_FrontrightSmallPiston.set(DoubleSolenoid.Value.kReverse);


  }

  public void ExtendBackSmall() {

    m_BackleftSmallPiston.set(DoubleSolenoid.Value.kForward);
    m_BackrightSmallPiston.set(DoubleSolenoid.Value.kForward);


  }

  public void RetractBackSmall() {

    m_BackleftSmallPiston.set(DoubleSolenoid.Value.kReverse);
    m_BackrightSmallPiston.set(DoubleSolenoid.Value.kReverse);


  }

  */




}
