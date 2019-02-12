/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class startDescendCommand extends Command {
  public startDescendCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climbSubsystem);
    requires(Robot.m_drivetrain);
    //TODO-MR Add Drive Train Subsystem
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climbSubsystem.startDescend();
    setTimeout(Robot.climbSubsystem.getTimeLeft());
    Robot.climbSubsystem.setauxDrive();
    Robot.m_drivetrain.update(Robot.climbSubsystem.getMainDrive(), 0);
    //TODO-MR Robot.DriveTrain.update(Robot.climbSubsystem.getMainDrive());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
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
