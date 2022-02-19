package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.Motors;
import frc.robot.Constants.XboxButtons;
import frc.robot.commands.MoveLongArms;
import frc.robot.commands.RotateBabies;
import frc.robot.commands.UpdateEncoders;
import frc.robot.subsystems.Climber;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private double leftSpeed = 0.2;
  private double rightSpeed = -0.2;

  private final Drivetrain drivetrain = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);
  private Climber climber = new Climber(
    Motors.LEFT_ARM_EXTENDER, Motors.RIGHT_ARM_EXTENDER, Motors.LEFT_ARM_ANGLE, Motors.RIGHT_ARM_ANGLE, Motors.LEFT_BABY, Motors.RIGHT_BABY);

  private UpdateEncoders updateEncoders = new UpdateEncoders(climber);
  private DriveByJoysticks driveByJoysticks = new DriveByJoysticks(drivetrain);


  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    XboxButtons.LEFT_BUMPER.toggleWhenPressed(new MoveLongArms(climber, 1, false));
    XboxButtons.RIGHT_BUMPER.toggleWhenPressed(new MoveLongArms(climber, 2, false));
    XboxButtons.BUTTON_Y.toggleWhenPressed(new MoveLongArms(climber, 3, false));
    XboxButtons.BUTTON_A.toggleWhenPressed(new MoveLongArms(climber, 4, false));
    XboxButtons.BUTTON_X.toggleWhenPressed(new RotateBabies(climber, 5, false));
    XboxButtons.BUTTON_B.toggleWhenPressed(new RotateBabies(climber, 6, false));

    XboxButtons.MODE_BUTTON.whenPressed(new InstantCommand(climber::toggleMode));

    if (!climber.individualMode) {
      XboxButtons.LEFT_BUMPER.whileHeld(new StartEndCommand(climber::ExtendArms, climber::StopLongLongArms, climber));
      XboxButtons.RIGHT_BUMPER.whileHeld(new StartEndCommand(climber::ContractArms, climber::StopLongLongArms, climber));

      XboxButtons.BUTTON_Y.whileHeld(new StartEndCommand(climber::RotateArmsForwards, climber::StopArmsRotate, climber));
      XboxButtons.BUTTON_A.whileHeld(new StartEndCommand(climber::RotateArmsBackwards, climber::StopArmsRotate, climber));

      XboxButtons.BUTTON_X.whileHeld(new StartEndCommand(climber::RotateBabyFowards, climber::StopBabies, climber));
      XboxButtons.BUTTON_B.whileHeld(new StartEndCommand(climber::RotateBabyBackwards, climber::StopBabies, climber));
    } 
    else {
      XboxButtons.LEFT_BUMPER.whileHeld(new StartEndCommand(() -> climber.MoveLeftArm(leftSpeed), climber::StopLeftArm, climber));
      XboxButtons.LEFT_JOYSTICK.whileHeld(new StartEndCommand(() -> climber.MoveLeftArm(-leftSpeed), climber::StopRightArm, climber));
      XboxButtons.RIGHT_BUMPER.whileHeld(new StartEndCommand(() -> climber.MoveRightArm(rightSpeed), climber::StopLeftArm, climber));
      XboxButtons.RIGHT_JOYSTICK.whileHeld(new StartEndCommand(() -> climber.MoveRightArm(-rightSpeed), climber::StopRightArm, climber));

      XboxButtons.DPAD_UP.whileHeld(new StartEndCommand(() -> climber.RotateLeftArm(leftSpeed), climber::StopRightArmRotate, climber));
      XboxButtons.DPAD_DOWN.whileHeld(new StartEndCommand(() -> climber.RotateRightArm(-leftSpeed), climber::StopRightArmRotate, climber));
      XboxButtons.BUTTON_Y.whileHeld(new StartEndCommand(() -> climber.RotateRightArm(rightSpeed), climber::StopLeftArmRotate, climber));
      XboxButtons.BUTTON_A.whileHeld(new StartEndCommand(() -> climber.RotateRightArm(-rightSpeed), climber::StopLeftArmRotate, climber));  
      
      XboxButtons.DPAD_RIGHT.whileHeld(new StartEndCommand(() -> climber.RotateLeftBaby(leftSpeed), climber::StopLeftBaby, climber));
      XboxButtons.DPAD_LEFT.whileHeld(new StartEndCommand(() -> climber.RotateLeftBaby(-leftSpeed), climber::StopLeftBaby, climber));
      XboxButtons.BUTTON_B.whileHeld(new StartEndCommand(() -> climber.RotateRightBaby(rightSpeed), climber::StopLeftBaby, climber));
      XboxButtons.BUTTON_X.whileHeld(new StartEndCommand(() -> climber.RotateRightBaby(-rightSpeed), climber::StopRightBaby, climber));
    }
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
