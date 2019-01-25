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
public class GrabberArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    private DoubleSolenoid m_grabberarm;
    private DoubleSolenoid m_grabberarm2; 

    

    @Override
    public void initDefaultCommand() {
      m_grabberarm = new DoubleSolenoid(5, 0, 1);
      m_grabberarm2 = new DoubleSolenoid(5, 0, 2);
      
  
        
    }

    public void TurnOff() {
        m_grabberarm.set(DoubleSolenoid.Value.kOff);
        m_grabberarm2.set(DoubleSolenoid.Value.kOff);


    }

    public void Extend() {
      m_grabberarm.set(DoubleSolenoid.Value.kForward);
      m_grabberarm2.set(DoubleSolenoid.Value.kForward);


    }

    public void Retract(){
      m_grabberarm.set(DoubleSolenoid.Value.kReverse);
      m_grabberarm2.set(DoubleSolenoid.Value.kReverse);
    }

   
    








}
