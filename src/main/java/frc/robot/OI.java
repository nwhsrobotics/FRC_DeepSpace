package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;




public class OI{


  Joystick joy = new Joystick(0);
  
  private final int CAMERA_BUTTON = 1;
  private final int Lvl2DESC_BUTTON = 2;
  private final int Lvl2CLIMB_BUTTON = 3;
  private final int Lvl3CLIMB_BUTTON = 4;
  public JoystickButton xButton1 = new JoystickButton(joy, CAMERA_BUTTON);
	public JoystickButton backButton1 = new JoystickButton(joy, Lvl2DESC_BUTTON);
	public JoystickButton startButton1 = new JoystickButton(joy, Lvl2CLIMB_BUTTON);
  public JoystickButton playButton1 = new JoystickButton(joy, Lvl3CLIMB_BUTTON);
    

  Joystick joy2 = new Joystick(1);


  private final int HIGHHATCH_BUTTON = 1;
  private final int MIDHATCH_BUTTON = 2;
  private final int LOWHATCH_BUTTON = 3;
  private final int CLAMP_BUTTON = 4;

  public JoystickButton yButton2 = new JoystickButton(joy2, HIGHHATCH_BUTTON);
  public JoystickButton xButton2 = new JoystickButton(joy2, MIDHATCH_BUTTON);
  public JoystickButton aButton2 = new JoystickButton(joy2, LOWHATCH_BUTTON);
  public JoystickButton bButton2 = new JoystickButton(joy2, CLAMP_BUTTON);



  public OI () {

<<<<<<< HEAD
    xButton1.toggleWhenPressed(new CameraToggle); //toggles camera on and off
=======
  /**  xButton1.toggleWhenPressed(new CameraToggle);
>>>>>>> nwhsrobotics/integration

    rightTrigger2.togglewhenActive(new SlideRightToggle); //slide moves right
    rightTrigger2.whenInactive(new SlideRightStop); //slide stops at current place

    leftTrigger2.togglewhenActive(new SlideLeftToggle); //slide moves left
    leftTrigger2.whenInactive(new SlideLeftStop); //slide stops at current place

    backButton1.whenPressed(new Lvl2Descent); //initiate lvl 2 descent
    startButton1.whenPressed(new Lvl2Climb); //initiate lvl 2 climb
    playButton1.whenPressed(new Lvl3Climb); // intiate lvl 3 climb

<<<<<<< HEAD

    yButton2.toggleWhenPressed(new HighHatchInitiate); //move lift to high hatch position
    xButton2.toggleWhenPressed(new MidHatchInitiate); //move lift to mid hatch position
    aButton2.toggleWhenPressed(new LowHatchInitiate); //move lift to low hatch position
    bButton2.toggleWhenPressed(new ClampToggle); //toggle for clamp
=======
    yButton2.toggleWhenPressed(new HighHatchInitiate);
    xButton2.toggleWhenPressed(new MidHatchInitiate);
    aButton2.toggleWhenPressed(new LowHatchInitiate);
    */
    bButton2.whenPressed(new GrabberExtend());
    bButton2.whenReleased(new GrabberRetract());
>>>>>>> nwhsrobotics/integration

    
  }
 



  public double getForwardValue() {
    return joy.getRawAxis(1);

  }
  public double getTurnValue() {
    return joy.getRawAxis(4);
  }


  public double liftUpValue() {
    return joy2.getRawAxis(0);
  }

  public double liftDownValue() {
    //need to check axes
    return joy2.getRawAxis(1);
  }


  public double getLeftSlideValue() {
    return joy2.getRawAxis(2);
  }

  public double getRightSlideValue() {
    return joy2.getRawAxis(3);
  }


  


}
