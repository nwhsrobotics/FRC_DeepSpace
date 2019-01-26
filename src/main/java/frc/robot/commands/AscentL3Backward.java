/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AscentL3Backward extends Command {
  public AscentL3Backward() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_PneumaticTestSubsystem);
    //TODO-MR Add Drive subsystem
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    int state = Robot.m_PneumaticTestSubsystem.getstate();
    Robot.m_PneumaticTestSubsystem.setState(state + 1);

    switch(state) {

      case 1: setState0();
              break;
      case 2: setState1();
              break;
      case 3: setState2();
              break;
    }

    private void setState0() {
      Robot.m_PneumaticTestSubsystem.ascendFront(false);
      Robot.m_PneumaticTestSubsystem.ascendBack(false);
      Robot.m_PneumaticTestSubsystem.AuxDrive(-0.5);
    }

    private void setState1(){
      Robot.m_PneumaticTestSubsystem.ascendFront(true);
      //TODO-MR Drive backward
    }

    private void setState2() {
      Robot.m_PneumaticTestSubsystem.ascendBack(true);
    }


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
