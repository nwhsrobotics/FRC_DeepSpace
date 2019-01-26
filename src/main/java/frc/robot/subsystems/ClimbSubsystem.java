/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import java.util.EnumMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  enum ClimbState {
    IDLE,
    DESCEND_S1, DESCEND_S2, DESCEND_S3, DESCEND_S4,
    CLIMB_L2_S1, CLIMB_L2_S2, CLIMB_L2_S3, CLIMB_L2_S4, CLIMB_L2_S5, CLIMB_L2_S6,
    CLIMB_L3_S1, CLIMB_L3_S2, CLIMB_L3_S3, CLIMB_L3_S4,
  }
  private ClimbState m_climbState = ClimbState.IDLE;

  private static final EnumMap<ClimbState, ClimbState> nextStageMap = new EnumMap<>(ClimbState.class);
  private static final EnumMap<ClimbState, ClimbState> prevStageMap = new EnumMap<>(ClimbState.class);

  private static final double DESCEND_SLOW = 0.1;

  private static final double L2_ASCEND_SPEED = 0.1;

  private static final double L2_ASCEND_SLOW = 0.1;

  private static final double L3_ASCEND_SPEED = 0.1;

  private static final double L3_ASCEND_SLOW = 0.1;


  private int m_timeLeft;

  private DoubleSolenoid Solenoid_1;
  private DoubleSolenoid Solenoid_2;
  private DoubleSolenoid Solenoid_3;
  private DoubleSolenoid Solenoid_4;
  private DoubleSolenoid Solenoid_5;
  private DoubleSolenoid Solenoid_6;
  private DoubleSolenoid Solenoid_7;
  private DoubleSolenoid Solenoid_8;
  WPI_TalonSRX backleftwheel;
  WPI_TalonSRX backrightwheel;
  SpeedControllerGroup climbwheels;

  private double m_mainDrive;

  private double m_auxDrive;
  


  public ClimbSubsystem() {

    nextStageMap.put(ClimbState.IDLE, ClimbState.IDLE);

    nextStageMap.put(ClimbState.DESCEND_S1, ClimbState.DESCEND_S2);
    nextStageMap.put(ClimbState.DESCEND_S2, ClimbState.DESCEND_S3);
    nextStageMap.put(ClimbState.DESCEND_S3, ClimbState.DESCEND_S4);
    nextStageMap.put(ClimbState.DESCEND_S4, ClimbState.IDLE);

    nextStageMap.put(ClimbState.CLIMB_L2_S1, ClimbState.CLIMB_L2_S2);
    nextStageMap.put(ClimbState.CLIMB_L2_S2, ClimbState.CLIMB_L2_S3);
    nextStageMap.put(ClimbState.CLIMB_L2_S3, ClimbState.CLIMB_L2_S4);
    nextStageMap.put(ClimbState.CLIMB_L2_S4, ClimbState.CLIMB_L2_S5);
    nextStageMap.put(ClimbState.CLIMB_L2_S5, ClimbState.CLIMB_L2_S6);
    nextStageMap.put(ClimbState.CLIMB_L2_S6, ClimbState.IDLE);

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
    prevStageMap.put(ClimbState.CLIMB_L2_S5, ClimbState.CLIMB_L2_S4);
    prevStageMap.put(ClimbState.CLIMB_L2_S6, ClimbState.CLIMB_L2_S5);

    prevStageMap.put(ClimbState.CLIMB_L3_S1, ClimbState.IDLE);
    prevStageMap.put(ClimbState.CLIMB_L3_S2, ClimbState.CLIMB_L3_S1);
    prevStageMap.put(ClimbState.CLIMB_L3_S3, ClimbState.CLIMB_L3_S2);
    prevStageMap.put(ClimbState.CLIMB_L3_S4, ClimbState.CLIMB_L3_S3);

    backleftwheel = new WPI_TalonSRX(100);   
    backrightwheel = new WPI_TalonSRX(101);
    climbwheels = new SpeedControllerGroup(backleftwheel, backrightwheel);

    Solenoid_1 = new DoubleSolenoid(RobotMap.pcmClimbCanId,RobotMap.FrontLeftSolenoidExtend,RobotMap.FrontLeftSolenoidRetract);
    Solenoid_1.set(DoubleSolenoid.Value.kOff);

    Solenoid_2 = new DoubleSolenoid(RobotMap.pcmClimbCanId,RobotMap.FrontRightSolenoidExtend,RobotMap.FrontRightSolenoidRetract);
    Solenoid_2.set(DoubleSolenoid.Value.kOff);

    Solenoid_3 = new DoubleSolenoid(RobotMap.pcmClimbCanId,RobotMap.BackLeftSolenoidExtend,RobotMap.BackLeftSolenoidRetract);
    Solenoid_3.set(DoubleSolenoid.Value.kOff);

    Solenoid_4 = new DoubleSolenoid(RobotMap.pcmClimbCanId,RobotMap.BackRightSolenoidExtend,RobotMap.BackRightSolenoidRetract);
    Solenoid_4.set(DoubleSolenoid.Value.kOff);

    Solenoid_5 = new DoubleSolenoid(RobotMap.pcmClimbCanId2,RobotMap.LowerFrontSolenoidExtend,RobotMap.LowerFrontSolenoidRetract);
    Solenoid_5.set(DoubleSolenoid.Value.kOff);

    Solenoid_6 = new DoubleSolenoid(RobotMap.pcmClimbCanId2,RobotMap.LowerBackSolenoidExtend,RobotMap.LowerBackSolenoidRetract);
    Solenoid_6.set(DoubleSolenoid.Value.kOff);

    Solenoid_7 = new DoubleSolenoid(RobotMap.pcmClimbCanId2,RobotMap.ascendAssistBackLeftExtend,RobotMap.ascendAssistBackLeftRetract);
    Solenoid_7.set(DoubleSolenoid.Value.kOff);

    Solenoid_8 = new DoubleSolenoid(RobotMap.pcmClimbCanId2,RobotMap.ascendAssistBackRightExtend,RobotMap.ascendAssistBackRightRetract);
    Solenoid_8.set(DoubleSolenoid.Value.kOff);

    m_mainDrive = 0.0;






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
    setActuators();

  }

  public void prevStage() {

    m_climbState = prevStageMap.get(m_climbState);

  }

  private void setActuators() {

    switch(m_climbState) {

      case IDLE:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = 0.0;
        m_timeLeft = 0;
        break;

      case DESCEND_S1:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(true);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = DESCEND_SLOW;
        m_timeLeft = 0;
        break;

      case DESCEND_S2:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(true);
        descendAssistFront(true);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = DESCEND_SLOW;
        m_timeLeft = 0;
        break;

      case DESCEND_S3:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(true);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = DESCEND_SLOW;
        m_timeLeft = 0;
        break;

      case DESCEND_S4:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = DESCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S1:
        ascendFront(true);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = 0.0;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S2:
        ascendFront(true);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = L2_ASCEND_SPEED;
        m_mainDrive = L2_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S3:
        ascendFront(true);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(true);
        m_auxDrive = L2_ASCEND_SPEED;
        m_mainDrive = L2_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S4:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(true);
        m_auxDrive = 0.0;
        m_mainDrive = L2_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S5:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = L2_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L2_S6:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = 0.0;
        m_timeLeft = 0;
        break;

      case CLIMB_L3_S1:
        ascendFront(true);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = 0.0;
        m_timeLeft = 0;
        break;

      case CLIMB_L3_S2:
        ascendFront(true);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = L3_ASCEND_SPEED;
        m_mainDrive = L3_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L3_S3:
        ascendFront(false);
        ascendBack(true);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = L3_ASCEND_SPEED;
        m_mainDrive = L3_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

      case CLIMB_L3_S4:
        ascendFront(false);
        ascendBack(false);
        descendAssistBack(false);
        descendAssistFront(false);
        ascendAssistBack(false);
        m_auxDrive = 0.0;
        m_mainDrive = L3_ASCEND_SLOW;
        m_timeLeft = 0;
        break;

    }

  }

  public double getMainDrive() {

    return m_mainDrive;

  }

  public int getTimeLeft() {

    return m_timeLeft;

  }




  public void ascendFront(boolean state) {
    if (state) {
    Solenoid_1.set(DoubleSolenoid.Value.kForward);
    Solenoid_2.set(DoubleSolenoid.Value.kForward);
  }
  else {
    Solenoid_1.set(DoubleSolenoid.Value.kReverse);
    Solenoid_2.set(DoubleSolenoid.Value.kReverse);
  }
}

public void ascendBack(boolean state) {
  if (state) {
    Solenoid_3.set(DoubleSolenoid.Value.kForward);
    Solenoid_4.set(DoubleSolenoid.Value.kForward);
  }
  else{
  Solenoid_3.set(DoubleSolenoid.Value.kReverse);
  Solenoid_4.set(DoubleSolenoid.Value.kReverse);
  }
}

public void descendAssistFront(boolean state) {
  if (state) {
    Solenoid_5.set(DoubleSolenoid.Value.kForward);
  }
  else {
    Solenoid_5.set(DoubleSolenoid.Value.kReverse);
  }

}

public void descendAssistBack(boolean state) {
  if (state) {
    Solenoid_6.set(DoubleSolenoid.Value.kForward);
  }
  else {
    Solenoid_6.set(DoubleSolenoid.Value.kReverse);
  }
}

public void ascendAssistBack(boolean state) {
  if (state) {
    Solenoid_7.set(DoubleSolenoid.Value.kForward);
    Solenoid_8.set(DoubleSolenoid.Value.kForward);
  }

}
}
