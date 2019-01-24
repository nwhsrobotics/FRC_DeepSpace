/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SolenoidArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid Solenoid_pusher;

  public SolenoidArmSubsystem() {
    Solenoid_pusher = new DoubleSolenoid(RobotMap.pmcArmCanId,RobotMap.PusherSolenoidPush,RobotMap.PusherSolenoidRetract);
    Solenoid_pusher.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
    }
  

public void push() {
  Solenoid_pusher.set(DoubleSolenoid.Value.kForward);
}

public void retractpusher() {
  Solenoid_pusher.set(DoubleSolenoid.Value.kReverse);
}
}
