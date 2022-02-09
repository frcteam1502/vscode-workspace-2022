// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Motors;
import frc.robot.commands.GetEncoderValues;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public Drivetrain drivetrain = new Drivetrain(
  Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);
  
  public Climber climber = new Climber(
  Motors.LEFT_ARM_EXTENDER, Motors.RIGHT_ARM_EXTENDER, Motors.LEFT_ARM_ANGLE, Motors.RIGHT_ARM_ANGLE, Motors.LEFT_BABY, Motors.RIGHT_BABY);

  public GetEncoderValues getEncoderValues = new GetEncoderValues(climber);
  

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
    
    // Buttons.BUTTON_ONE.whileHeld(new TempCommand(drivetrain));
    // Buttons.BUTTON_ONE.whileHeld(new StartEndCommand(climber::ExtendLongLongArmsManual, climber::StopLongLongArms, drivetrain));

    // Long extender
    Buttons.J_BUTTON_ONE.whileHeld(new StartEndCommand(climber::ExtendLongLongArmsManual, climber::StopLongLongArms, climber));
    Buttons.J_BUTTON_TWO.whileHeld(new StartEndCommand(climber::ContractLongLongArmsManual, climber::StopLongLongArms, climber));

    // Long rotate
    Buttons.J_BUTTON_THREE.whileHeld(new StartEndCommand(climber::RotateBigArmsManualClockwise, climber::StopLongArmRotate, climber));
    Buttons.J_BUTTON_FOUR.whileHeld(new StartEndCommand(climber::RotateBigArmsManualCounterClockwise, climber::StopLongArmRotate, climber));

    // Rotate babies
    Buttons.J_BUTTON_THREE.whileHeld(new StartEndCommand(climber::RotateBabiesManualClockwise, climber::StopBabies, climber));
    Buttons.J_BUTTON_FOUR.whileHeld(new StartEndCommand(climber::RotateBabiesManualCounterClockwise, climber::StopBabies, climber));

    // Buttons.XBOX_BUTTON_ONE.whileHeld(new StartEndCommand(drivetrain::testMotorOn, drivetrain::testMotorOff, drivetrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @param List 
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
