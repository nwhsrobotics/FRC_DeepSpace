package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;





public class OI{


  Joystick joy = new Joystick(0);
  
  private final int CAMERA_BUTTON = 7;
  private final int ClimbNext = 1;
  private final int ClimbPrev = 2;
  private final int startL2Ascend = 3;
  private final int startL3Ascend = 4;
  private final int startDescend = 8;
  public JoystickButton xButton1 = new JoystickButton(joy, startL2Ascend);
	public JoystickButton bButton1 = new JoystickButton(joy, ClimbPrev);
	public JoystickButton aButton1 = new JoystickButton(joy, ClimbNext);
  public JoystickButton yButton1 = new JoystickButton(joy, startL3Ascend);
  public JoystickButton startButton1 = new JoystickButton(joy, startDescend);
  public JoystickButton selectButton1 = new JoystickButton(joy, CAMERA_BUTTON);
    

  Joystick joy2 = new Joystick(1);


  private final int HIGHHATCH_BUTTON = 1;
  private final int MIDHATCH_BUTTON = 2;
  private final int LOWHATCH_BUTTON = 3;
  private final int CLAMP_BUTTON = 4;
  private final int ARM_BUTTON = 8;
;

  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);
  public JoystickButton startButton2 = new JoystickButton(joy2, ARM_BUTTON);
 /* Button b1 = new JoystickButton(joy2, 1);
  Button b4 = new JoystickButton(joy2, 4);
  */
  


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
  /**  xButton1.toggleWhenPressed(new CameraToggle);

    rightTrigger2.togglewhenActive(new SlideRightToggle);
    rightTrigger2.whenInactive(new SlideLeftStop);

    backButton1.whenPressed(new Lvl2Descent);
    startButton1.whenPressed(new Lvl2Climb);
    playButton1.whenPressed(new Lvl3Climb);


    yButton2.toggleWhenPressed(new HighHatchInitiate);
    xButton2.toggleWhenPressed(new MidHatchInitiate);
    aButton2.toggleWhenPressed(new LowHatchInitiate);
    */
    bButton2.whenPressed(new GrabberExtend());
    bButton2.whenReleased(new GrabberRetract());

    
  }
 



  public double getForwardValue() {

    return joy.getRawAxis(1);

  }
  public double getTurnValue() {
    return joy.getRawAxis(4);
  }

  public double getLiftValue() {
    return joy2.getRawAxis(1);
  }

  public double getSlideValue() {
    return joy2.getRawAxis(3) - joy2.getRawAxis(2);
  }


  


}
