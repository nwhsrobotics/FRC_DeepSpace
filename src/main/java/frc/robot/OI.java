package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.*;
import java.lang.Math;





public class OI{


  Joystick joy = new Joystick(0);
  Joystick joy2 = new Joystick(1);

  private final double FORWARD_DEADBAND_LIMIT = .1;
  private final double TURN_DEADBAND_LIMIT = .1;

  int CLIMB_BUTTON = 1;
  public JoystickButton xButton = new JoystickButton(joy, CLIMB_BUTTON);
  JoystickButton a = new JoystickButton(joy, 1);
  JoystickButton b = new JoystickButton(joy, 2);
  JoystickButton x = new JoystickButton(joy, 3);
  JoystickButton y = new JoystickButton(joy, 4);

  public final int CAMERA_BUTTON = 2;
  private final int Lvl2DESC_BUTTON = 7;
  private final int Lvl2CLIMB_BUTTON = 8;
  private final int Lvl3CLIMB_BUTTON = 3;//cant find button
  private final int CLIMBNEXT_BUTTON = 6;
  private final int CLIMBPREV_BUTTON = 5;
  private final int SLIDERESET_BUTTON = 0;
  public JoystickButton bButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton backButton1 = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton startButton1 = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
  public JoystickButton leftBumper1 = new JoystickButton(joy, CLIMBPREV_BUTTON);
  public JoystickButton rightBumper1 = new JoystickButton(joy, CLIMBNEXT_BUTTON);
  public JoystickButton xButton1 = new JoystickButton(joy, Lvl3CLIMB_BUTTON);
  public POVButton dpadUp = new POVButton(joy2, SLIDERESET_BUTTON);
    
  


  private final int HIGHHATCH_BUTTON = 4;
  private final int MIDHATCH_BUTTON = 3;
  private final int LOWHATCH_BUTTON = 1;
  private final int CLAMP_BUTTON = 2;

  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);

  public final double TURNMODIFIER = .6;
  public final double STRAIGHTMODIFIER = -.9;
  public final double LIFTMODIFIER = -.8;


  public OI () {

    //bButton1.toggleWhenPressed(new CameraToggle);

    backButton1.whenPressed(new startDescendCommand()); //initiate lvl 2 descent
    startButton1.whenPressed(new startL2AscendCommand()); //initiate lvl 2 climb
    xButton1.whenPressed(new startL3AscendCommand()); // intiate lvl 3 climb

    leftBumper1.whenPressed(new ClimbPrevCommand());
    rightBumper1.whenPressed(new ClimbNextCommand());

    yButton2.whenPressed(new LiftHighCommand()); //move lift to high hatch position
    xButton2.whenPressed(new LiftMidCommand()); //move lift to mid hatch position
    aButton2.whenPressed(new LiftLowCommand()); //move lift to low hatch position

    bButton2.whenActive(new GrabberExtend()); //toggle for clamp
    bButton2.whenInactive(new GrabberRetract()); //toggle for clamp
    dpadUp.whenPressed(new slideResetCommand());
    

    
  }
 



  public double getForwardValue() {
    if (Math.abs(joy.getRawAxis(1)) < FORWARD_DEADBAND_LIMIT) { //creates deadband
      return 0;
    } else {
      joy.getRawAxis(1);
    }
    return joy.getRawAxis(1) * STRAIGHTMODIFIER;
  }
  public double getTurnValue() {
    if (Math.abs(joy.getRawAxis(4)) < TURN_DEADBAND_LIMIT) {
      return 0;
    } else {
      return joy.getRawAxis(4) * TURNMODIFIER;
    }
    
  }

  public double getLiftValue() {
    return joy2.getRawAxis(1) * LIFTMODIFIER;
  }

  public double getSlideValue() {
    return joy2.getRawAxis(3) - joy2.getRawAxis(2);
  }


  


}
