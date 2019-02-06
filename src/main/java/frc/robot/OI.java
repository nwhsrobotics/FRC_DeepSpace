/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.slideUpdateParams;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
  
  int CLIMB_BUTTON = 1;
  public JoystickButton xButton = new JoystickButton(joy, CLIMB_BUTTON);
  JoystickButton a = new JoystickButton(joy, 1);
  JoystickButton b = new JoystickButton(joy, 2);
  JoystickButton x = new JoystickButton(joy, 3);
  JoystickButton y = new JoystickButton(joy, 4);
  private static final int POS_HOLD_TOGGLE_BUTTON = 2;  // B button
  private static final int POS_HOLD_UPDATE_BUTTON = 4;  // Y button

   private static final double MAX_POS_DEG = 90.0;       // how far to rotate shaft at full joystick deflection.
   private static final int POS_HOLD_AXIS = 0;           // left-right on left joystick

   private static final Joystick m_controller = new Joystick(1);
  private static final JoystickButton m_b = new JoystickButton(m_controller, POS_HOLD_TOGGLE_BUTTON);
  private static final JoystickButton m_y = new JoystickButton(m_controller, POS_HOLD_UPDATE_BUTTON);
  /*
  public JoystickButton yButton = new JoystickButton(joy, );
  public JoystickButton aButton = new JoystickButton(joy, );
  public JoystickButton bButton = new JoystickButton(joy, );
  public JoystickButton rightBumper = new JoystickButton(joy, );
  public JoystickButton leftBumper = new JoystickButton(joy, );
  public JoystickButton startButton = new JoystickButton(joy, );
  public JoystickButton selectButton = new JoystickButton(joy, );
  public JoystickButton leftStickButton = new JoystickButton(joy, );
  public JoystickButton rightStickButton = new JoystickButton(joy, );
  */
 
  public OI () {
       // Toggle position hold mode with B button.
      // Update params when Y is pressed.
  }
  public double readPositionDeg() {
    double input = m_controller.getRawAxis(POS_HOLD_AXIS);  // get joystick position, in range -1.0 to 1.0
    double holdPos = input * MAX_POS_DEG;     // convert to degrees

    return holdPos;
  } 
 
  public double getForwardValue() {
    System.out.println(joy.getRawAxis(1));
    return joy.getRawAxis(1);
  }
  
  public double getTurnValue() {
    System.out.println(joy.getRawAxis(4));
    return joy.getRawAxis(4);
  }
  
  
}
