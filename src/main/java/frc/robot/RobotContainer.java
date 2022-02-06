// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Motors;
import frc.robot.commands.ContractArms;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.ExtendArms;
import frc.robot.commands.GetEncoderValues;
import frc.robot.commands.RotateBabies;
import frc.robot.commands.RotateLongArms;
import frc.robot.commands.TempCommand;
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
  
  /*
  
  public DriveByJoysticks teleOpDrive = new DriveByJoysticks(drivetrain);
  
  public ExtendArms extendArms = new ExtendArms(climber);
  
  public ContractArms contractArms = new ContractArms(climber);
  
  public RotateLongArms rotateLongArms = new RotateLongArms(climber);
  
  public RotateBabies rotateBabies = new RotateBabies(climber);

  public GetEncoderValues getEncoderValues = new GetEncoderValues(climber);
  
  */

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
    
    // TODO: Button test
    Buttons.BUTTON_ONE.whileHeld(new TempCommand(drivetrain));
    // Buttons.BUTTON_ONE.whileHeld(new StartEndCommand(drivetrain::testMotorOn, drivetrain::testMotorOff, drivetrain));


    // Buttons.BUTTON_ONE.whileHeld(new ExtendArms(climber));
    // Buttons.BUTTON_ONE.whileHeld(new StartEndCommand(climber::ExtendLongLongArmsManual, climber::StopLongLongArms, climber));

    // Buttons.BUTTON_TWO.whileHeld(new ContractArms(climber));
    // Buttons.BUTTON_TWO.whileHeld(new StartEndCommand(climber::ContractLongLongArmsManual, climber::StopLongLongArms, climber));
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
