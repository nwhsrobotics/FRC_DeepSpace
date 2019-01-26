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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 * Add your docs here.
 */
public class SolenoidClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

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
  


  public SolenoidClimbSubsystem() {



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






    }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
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
  else {
    Solenoid_7.set(DoubleSolenoid.Value.kReverse);
    Solenoid_8.set(DoubleSolenoid.Value.kReverse);
  }

}
}
