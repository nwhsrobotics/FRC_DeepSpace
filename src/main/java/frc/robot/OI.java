package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SlideForward;
import frc.robot.commands.SlideStop;

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
  
<<<<<<< HEAD
  int CAMERA_BUTTON = 1;
  int Lvl2DESC_BUTTON = 2;
  int Lvl2CLIMB_BUTTON = 3;
  int Lvl3CLIMB_BUTTON = 4;
    public JoystickButton xButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton backButton = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton startButton = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
    public JoystickButton playButton = new JoystickButton(joy, Lvl3CLIMB_BUTTON);
    

  Joystick joy2 = new Joystick(1);

  final int HIGHHATCH_BUTTON = 1;
  final int MIDHATCH_BUTTON = 2;
  final int LOWHATCH_BUTTON = 3;
  final int CLAMP_BUTTON = 4;
    public JoystickButton yButton = new JoystickButton(joy2, HIGHHATCH_BUTTON);
    public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
    public JoystickButton aButton = new JoystickButton(joy2, LOWHATCH_BUTTON);
    public JoystickButton bButton = new JoystickButton(joy2, CLAMP_BUTTON);

}

  public OI () {

    xButton.toggleWhenPressed();

    rightTrigger.togglewhenActive();
    rightTrigger.whenInactive();

    backButton.whenPressed();
    startButton.whenPressed();
    playButton.whenPressed();


    yButton.toggleWhenPressed();
    xButton.toggleWhenPressed();
    aButton.toggleWhenPressed();
    bButton.toggleWhenPressed();

    
    
=======
  int CLIMB_BUTTON = 1;
  public JoystickButton xButton = new JoystickButton(joy, CLIMB_BUTTON);
  JoystickButton a = new JoystickButton(joy, 1);
   JoystickButton b = new JoystickButton(joy, 2);
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
    a.whenPressed(new SlideForward());
    b.whenReleased(new SlideStop());
>>>>>>> master
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
