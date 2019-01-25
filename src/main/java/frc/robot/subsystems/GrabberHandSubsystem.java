/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class GrabberHandSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid m_Piston;
  private Compressor m_Compressor;

  @Override
  public void initDefaultCommand() {
    m_Compressor = new Compressor(5);
    m_Piston = new DoubleSolenoid(5, 0, 1);

  }

  public void turnOff() {
    m_Piston.set(DoubleSolenoid.Value.kOff);

  }

  public void extend() {
    m_Piston.set(DoubleSolenoid.Value.kForward);

  }

  public void retract() {
    m_Piston.set(DoubleSolenoid.Value.kReverse);

  }

  public void compressorOn() {
    m_Compressor.setClosedLoopControl(true);

  }

  public void compressorOff() {
    m_Compressor.setClosedLoopControl(false);

  }
}
