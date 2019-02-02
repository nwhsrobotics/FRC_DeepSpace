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
 
  //slide
 // public static int slideCanId(){
   // return 5;
 // }
  //drive train
  public static int frontLeftMotorCanId() {
    return 20;
  }
  public static int backLeftMotorCanId() {
    return 21;
  }
  public static int frontRightMotorCanId() {
    
    return 11;
  }
  public static int backRightMotorCanId() {
  
    return 10;
  }
  //lift
 /* public static int leftLiftMotorCanId() {
    return 1; //replace with actual can id
  }
  public static int rightLiftMotorCanId() {
    return 6; //replace with actual can id
  }
  */

  public static int pmc1CanID() {
    return 5;
  }

 /* public static int pmc2CanID() {
    return 6;
  }
  
  public static int pcm3CanID() {
    return 7;
  }

 
  public static int GrabberSolenoidForward() {
    return 0;
  }
  
  public static int GrabberSolenoidReverse() {
    return 1;
  }
  
  public static int ArmSolenoidForward() {
    return 2;
  }

  public static int ArmSolenoidReverse() {
    return 3;
  }
  */
  
  public static int backLeftWheel() {
    return 3;
  }
  public static int backRightWheel() {
    return 2;
  }
  
  

  
  public static int frontLeftSolenoidExtend() {
    return 0;
  }
  public static int frontLeftSolenoidRetract() {
    return 1;
  }
  public static int backLeftSolenoidExtend() {
    return 4;
  }
  public static int backLeftSolenoidRetract() {
    return 5;
  }
  public static int frontRightSolenoidExtend() {
    return 2;
  }
  public static int frontRightSolenoidRetract() {
    return 3;
  }
 /* public static int backRightSolenoidExtend() {
    return 6;
  }
  public static int backRightSolenoidRetract() {
    return 7;
  }

  
  public static int LowerFrontSolenoidExtend() {
    return 0;
  }
  public static int LowerFrontSolenoidRetract() {
    return 1;
  }
  public static int LowerBackSolenoidExtend() {
    return 2;
  }
  public static int LowerBackSolenoidRetract() {
    return 3;
  } 
  public static int ascendAssistBackLeftExtend() {
    return 4;
  }
  public static int ascendAssistBackLeftRetract() {
    return 5;
  }
  public static int ascendAssistBackRightExtend() {
    return 6;
  }
  public static int ascendAssistBackRightRetract() {
    return 7;
  }
  */
 
 


  


  


 
 

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
