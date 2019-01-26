/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AscentL3Forward extends Command {
  public AscentL3Forward() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_PneumaticTestSubsystem);
    //TODO-MR Add Drive Subsystem
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    int state = Robot.m_PneumaticTestSubsystem.getstate();
    Robot.m_PneumaticTestSubsystem.setState(state + 1);

    switch(state) {

      case 1: setState1();
              break;
      case 2: setState2();
              break;
      case 3: setState3();
              break;
    }

    private void setState1() {
      Robot.m_PneumaticTestSubsystem.ascendFront(true);
      Robot.m_PneumaticTestSubsystem.ascendBack(true);
      Robot.m_PneumaticTestSubsystem.AuxDrive(0.5);
    }

    private void setState2() {
      Robot.m_PneumaticTestSubsystem.ascendFront(false);
      //TODO-MR Drive forward
    }

    private void setState3(){
      Robot.m_PneumaticTestSubsystem.ascendFront(false);
    }


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    time = time + 1;

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (time >= END);
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
