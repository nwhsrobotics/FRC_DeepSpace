/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClimbNextCommand;
import frc.robot.commands.ClimbPrevCommand;
import frc.robot.commands.DescendCommandGroup;
import frc.robot.commands.L2AscendCommandGroup;
import frc.robot.commands.L3AscendCommandGroup;
import frc.robot.commands.startDescendCommand;
import frc.robot.commands.startL2AscendCommand;
import frc.robot.commands.startL3AscendCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static final double MAX_POS_DEG = 90.0;
  private static final int POS_HOLD_AXIS = 0;
  private static final int POS_HOLD_TOGGLE_BUTTON = 1;
  private static final int POS_HOLD_UPDATE_BUTTON = 2;

  private static final Joystick m_controller = new Joystick(0);
  private static final JoystickButton m_x = new JoystickButton(m_controller, POS_HOLD_TOGGLE_BUTTON);
  private static final JoystickButton m_y = new JoystickButton(m_controller, POS_HOLD_UPDATE_BUTTON);
  
  
  
  public double readPositionDeg() {
    double input = m_controller.getRawAxis(POS_HOLD_AXIS);
    double holdPos = input * MAX_POS_DEG;

    return holdPos;
	}
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  Joystick joy = new Joystick(0);
  

  int ClimbNext_BUTTON = 1;
  int ClimbPrev_BUTTON = 2;
  int Lvl2CLIMB_BUTTON = 3;
  int Lvl3CLIMB_BUTTON = 4;
  int CAMERA_BUTTON = 7;
  int Lvl2DESC_BUTTON = 8;
  public JoystickButton selectButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton startButton1 = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton xButton1 = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
  public JoystickButton yButton1 = new JoystickButton(joy, Lvl3CLIMB_BUTTON);
  public JoystickButton aButton1 = new JoystickButton(joy, ClimbNext_BUTTON);
  public JoystickButton bButton1 = new JoystickButton(joy, ClimbPrev_BUTTON);
    

  Joystick joy2 = new Joystick(1);
  
  final int HIGHHATCH_BUTTON = 1;
  final int MIDHATCH_BUTTON = 2;
  final int LOWHATCH_BUTTON = 3;
  final int CLAMP_BUTTON = 4;
  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);
  Button b1 = new JoystickButton(joy2, 1);
  Button b4 = new JoystickButton(joy2, 4);
  


  public OI () {

   // xButton1.toggleWhenPressed(new CameraToggle);

   // rightTrigger2.togglewhenActive(new SlideRightToggle);
   // rightTrigger2.whenInactive(new SlideLeftStop);

    startButton1.whenPressed(new DescendCommandGroup());
    xButton1.whenPressed(new L2AscendCommandGroup());
    yButton1.whenPressed(new L3AscendCommandGroup());
    aButton1.whenPressed(new ClimbNextCommand());
    bButton1.whenPressed(new ClimbPrevCommand());
   
   /* b1.whenPressed(new DownLiftCommand());
    b1.whenReleased(new StopLiftCommand());
    m_x.whenPressed(new PositionCommand());
    m_y.whenPressed(new PositionUpdateCommand());
    b4.whenPressed(new UpLiftCommand());
    b4.whenReleased(new StopLiftCommand());
    */


  //  yButton2.toggleWhenPressed(new HighHatchInitiate);
   // xButton2.toggleWhenPressed(new MidHatchInitiate);
   // aButton2.toggleWhenPressed(new LowHatchInitiate);
   // bButton2.toggleWhenPressed(new ClampToggle); 

    
    
  }
 



  public double getForwardValue() {
    return joy.getRawAxis(1);

  }
  public double getTurnValue() {
    return joy.getRawAxis(4);
  }

  public double getLiftValue(){
    return joy2.getRawAxis(1);
  }

  public double getLeftSlideValue() {
    return joy2.getRawAxis(2);
  }

  public double getRightSlideValue() {
    return joy2.getRawAxis(3);
  }


  


}
