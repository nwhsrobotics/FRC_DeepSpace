/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
 
  public static int frontLeftMotorCanId() {
    return 0;
  }
  public static int backLeftMotorCanId() {
    return 0;
  }
  public static int frontRightMotorCanId() {
    return 0;
  }
  public static int backRightMotorCanId() {
    return 0;
  }

  public static int pcmClimbCanId = 5;
  public static int FrontLeftSolenoidExtend = 0;
  public static int FrontLeftSolenoidRetract = 1;
  public static int BackLeftSolenoidExtend = 2;
  public static int BackLeftSolenoidRetract = 3;
  public static int FrontRightSolenoidExtend = 4;
  public static int FrontRightSolenoidRetract = 5;
  public static int BackRightSolenoidExtend = 6;
  public static int BackRightSolenoidRetract = 7;

  public static int pcmClimbCanId2 = 6;
  public static int LowerFrontSolenoidExtend = 0;
  public static int LowerFrontSolenoidRetract = 1;
  public static int LowerBackSolenoidExtend = 2;
  public static int LowerBackSolenoidRetract = 3;
  public static int ascendAssistBackLeftExtend = 4;
  public static int ascendAssistBackLeftRetract = 5;
  public static int ascendAssistBackRightExtend = 6;
  public static int ascendAssistBackRightRetract = 7;
  
  
  public static int pcmArmCanId = 7;
  public static int PusherSolenoidPush = 0;
  public static int PusherSolenoidRetract = 1;
  public static int ArmSolenoidForward = 2;
  public static int ArmSolenoidReverse = 3;
 
 
 

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
