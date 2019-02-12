package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;





public class OI{


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
  private final int BLIND = 5;
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
    return -joy.getRawAxis(1) * STRAIGHMODIFIER;

  }
  public double getTurnValue() {
    return joy.getRawAxis(4) * TURNMODIFIER;
  }

  public double getLiftValue() {
    return joy2.getRawAxis(1);
  }

  public double getSlideValue() {
    return joy2.getRawAxis(3) - joy2.getRawAxis(2);
  }


  


}
