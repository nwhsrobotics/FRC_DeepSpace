/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AscentL2Backward extends Command {

  private int m_time;
  private int m_endTime;
  private static final int STATE0_TIME = 50; //Ticks
  private static final int STATE1_TIME = 50; //Ticks
  private static final int STATE2_TIME = 50; //Ticks
  private static final int STATE3_TIME = 50; //Ticks

  public AscentL2Backward() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_PneumaticTestSubsystem);
    //TODO-MR Add Drive Subsystem
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    int state = Robot.m_PneumaticTestSubsystem.getstate();
    Robot.m_PneumaticTestSubsystem.setState(state - 1);

    m_time = 0;

    switch(state) {

      case 0: 
        setState0();
        break;
      case 1: 
        setState1();
        break;
      case 2: 
        setState2();
        break;
      case 3: 
        setState3();
        break;
    }
    
    

    
  }

  private void setState0(){
    m_endTime = STATE0_TIME;
    Robot.m_PneumaticTestSubsystem.ascendFront(false);
    Robot.m_PneumaticTestSubsystem.ascendFront(false);
  }

  private void setState1(){
    m_endTime = STATE1_TIME;
    Robot.m_PneumaticTestSubsystem.AuxDrive(); //backwards
    Robot.m_PneumaticTestSubsystem.ClimbAssist(false);
  }

  private void setState2(){
    Robot.m_PneumaticTestSubsystem.ascendFront(true);
    Robot.m_PneumaticTestSubsystem.ascendFront(true);
  }

  private void setState3(){
    //TODO-MR Drive Forward
    Robot.m_PneumaticTestSubsystem.ClimbAssist(true);
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_time = m_time + 1;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (m_time >= END);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
