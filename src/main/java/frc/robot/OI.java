package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
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
=======

import frc.robot.commands.*;





public class OI{


  Joystick joy = new Joystick(0);
  
  private final int CAMERA_BUTTON = 2;
  private final int Lvl2DESC_BUTTON = 7;
  private final int Lvl2CLIMB_BUTTON = 8;
  private final int Lvl3CLIMB_BUTTON = 3;//cant find button
  private final int CLIMBNEXT_BUTTON = 6;
  private final int CLIMBPREV_BUTTON = 5;
  public JoystickButton xButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton backButton1 = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton startButton1 = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
  public JoystickButton leftBumper1 = new JoystickButton(joy, CLIMBPREV_BUTTON);
  public JoystickButton rightBumper1 = new JoystickButton(joy, CLIMBNEXT_BUTTON);
    

  Joystick joy2 = new Joystick(1);


  private final int HIGHHATCH_BUTTON = 4;
  private final int MIDHATCH_BUTTON = 3;
  private final int LOWHATCH_BUTTON = 1;
  private final int CLAMP_BUTTON = 2;

  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);

  public double turnModifier = .4;
  public double driveModifier = .9;


  public OI () {

    //bButton1.toggleWhenPressed(new CameraToggle);

    //rightTrigger2.togglewhenActive(new SlideForward()); //slide moves right
    //rightTrigger2.whenInactive(new SlideStop()); //slide stops at current place

    //leftTrigger2.togglewhenActive(new SlideForward()); //slide moves left
    //leftTrigger2.whenInactive(new SlideStop()); //slide stops at current place

    backButton1.whenPressed(new DescendCommandGroup()); //initiate lvl 2 descent
    startButton1.whenPressed(new L2AscendCommandGroup()); //initiate lvl 2 climb
    xButton1.whenPressed(new L3AscendCommandGroup()); // intiate lvl 3 climb

    leftBumper1.whenPressed(new ClimbPrevCommand());
    rightBumper1.whenPressed(new ClimbNextCommand());

    yButton2.whenPressed(new LiftHighCommand()); //move lift to high hatch position
    xButton2.whenPressed(new LiftMidCommand()); //move lift to mid hatch position
    aButton2.whenPressed(new LiftLowCommand()); //move lift to low hatch position

    bButton2.whenActive(new GrabberExtend()); //toggle for clamp
    bButton2.whenInactive(new GrabberRetract()); //toggle for clamp

    
>>>>>>> b393bda422fc1b0324a7d5544c2f8f58fd646cab
  }
  public double readPositionDeg() {
    double input = m_controller.getRawAxis(POS_HOLD_AXIS);  // get joystick position, in range -1.0 to 1.0
    double holdPos = input * MAX_POS_DEG;     // convert to degrees

    return holdPos;
  } 
 



  public double getForwardValue() {
    return joy.getRawAxis(1) * driveModifier;

  }
  public double getTurnValue() {
    return joy.getRawAxis(4) * turnModifier;
  }

  public double getLiftValue() {
    return joy2.getRawAxis(1);
  }

  public double getSlideValue() {
    return joy2.getRawAxis(3) - joy2.getRawAxis(2);
  }


  


}
