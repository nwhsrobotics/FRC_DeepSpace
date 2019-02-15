package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import java.lang.Math;

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
  
  private final double TURNMODIFIER = 0.6;
  private final double STRAIGHMODIFIER = 0.9;
  private final int CAMERA_BUTTON = 2;
  private final int Lvl2DESC_BUTTON = 7;
  private final int Lvl2CLIMB_BUTTON = 8;
  private final int Lvl3CLIMB_BUTTON = 3;//cant find button
  private final int CLIMBNEXT_BUTTON = 6;
  private final int CLIMBPREV_BUTTON = 5;

  public JoystickButton bButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton backButton1 = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton startButton1 = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
  public JoystickButton leftBumper1 = new JoystickButton(joy, CLIMBPREV_BUTTON);
  public JoystickButton rightBumper1 = new JoystickButton(joy, CLIMBNEXT_BUTTON);
  public JoystickButton xButton1 = new JoystickButton(joy, Lvl3CLIMB_BUTTON);

  Joystick joy2 = new Joystick(1);


  private final int HIGHHATCH_BUTTON = 4;
  private final int MIDHATCH_BUTTON = 3;
  private final int LOWHATCH_BUTTON = 1;
  private final int CLAMP_BUTTON = 2;
  private final int BLIND = 6;
  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);
  public JoystickButton leftBumper2 = new JoystickButton(joy2, BLIND);



  public OI () {

    //bButton1.toggleWhenPressed(new CameraToggle);

    backButton1.whenPressed(new DescendCommandGroup()); //initiate lvl 2 descent
    startButton1.whenPressed(new L2AscendCommandGroup()); //initiate lvl 2 climb
    xButton1.whenPressed(new L3AscendCommandGroup()); // intiate lvl 3 climb
    
    leftBumper2.whenPressed(new BlindCommand());

    leftBumper1.whenPressed(new ClimbPrevCommand());
    rightBumper1.whenPressed(new ClimbNextCommand());

    
    yButton2.toggleWhenPressed(new LiftCommand()); //move lift to high hatch position
    xButton2.toggleWhenPressed(new LiftCommand()); //move lift to mid hatch position
    aButton2.toggleWhenPressed(new LiftCommand()); //move lift to low hatch position
    
    bButton2.whenActive(new GrabberExtend()); //toggle for clamp
    bButton2.whenReleased(new GrabberRetract()); //toggle for clamp
    

    
  }
 



  public double getForwardValue() {
    if (Math.abs(joy.getRawAxis(1)) < .1) {
      return 0;
    } else {
      return joy.getRawAxis(1) * STRAIGHMODIFIER;
    }
  

  }
  public double getTurnValue() {
    if (Math.abs(joy.getRawAxis(4)) < .1) {
      return 0;
    } else {
      return joy.getRawAxis(4) * TURNMODIFIER;
    }
  }

  public double getLiftValue() {
    return joy2.getRawAxis(1);
  }

  public double getSlideValue() {
    return joy2.getRawAxis(3) - joy2.getRawAxis(2);
  }


  


}
