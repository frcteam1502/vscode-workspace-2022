// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.Motors;
import frc.robot.Constants.XboxButtons;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.MoveHood;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shoot;
import frc.robot.commands.UpdateEncoders;
import frc.robot.subsystems.AngleFlap;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.EncoderValues;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain drivetrain = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);
  private Climber climber = new Climber(
  Motors.LEFT_ARM_EXTENDER, Motors.RIGHT_ARM_EXTENDER, Motors.LEFT_ARM_ANGLE, Motors.RIGHT_ARM_ANGLE, Motors.LEFT_BABY, Motors.RIGHT_BABY);
  private static Turret turret = new Turret(Motors.TURRET);
  private Shooter shooter = new Shooter(Motors.SHOOTER_RIGHT, Motors.SHOOTER_LEFT, Motors.INDEX);
  private Intake intake = new Intake(Motors.INTAKE);
  private AngleFlap angleFlap = new AngleFlap(Motors.FLAP);  
  private EncoderValues encoderValues = new EncoderValues();

  private UpdateEncoders updateEncoders = new UpdateEncoders(encoderValues);
  private DriveByJoysticks driveByJoysticks = new DriveByJoysticks(drivetrain);
  public MoveTurret moveTurret = new MoveTurret(turret);
  private Shoot shoot = new Shoot(shooter);
  private RunIntake runIntake = new RunIntake(intake);
  private MoveHood moveHood = new MoveHood(angleFlap);

  public RobotContainer() {
    configureButtonBindings();
  }
  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {/*
    XboxButtons.LEFT_BUMPER.whenPressed(climber::ExtendArmsEncoders);
    XboxButtons.LEFT_BUMPER.whenReleased(climber::StopLongLongArms);

    XboxButtons.RIGHT_BUMPER.whenPressed(climber::ContractArmsEncoder);
    XboxButtons.RIGHT_BUMPER.whenReleased(climber::StopLongLongArms);

    XboxButtons.BUTTON_A.whenPressed(climber::RotateForwardsEncoder);
    XboxButtons.BUTTON_A.whenReleased(climber::StopArmsRotate);

    XboxButtons.BUTTON_Y.whenPressed(climber::RotateBackwardsEncoder);
    XboxButtons.BUTTON_Y.whenReleased(climber::StopArmsRotate);
    */

    
    XboxButtons.LEFT_BUMPER.whenPressed(new InstantCommand(climber::ExtendArms));
    XboxButtons.LEFT_BUMPER.whenReleased(new InstantCommand(climber::StopLongLongArms));

    XboxButtons.RIGHT_BUMPER.whenPressed(new InstantCommand(climber::ContractArms));
    XboxButtons.RIGHT_BUMPER.whenReleased(new InstantCommand(climber::StopLongLongArms));

    XboxButtons.BUTTON_A.whenPressed(new InstantCommand(climber::RotateArmsForwards));
    XboxButtons.BUTTON_A.whenReleased(new InstantCommand(climber::StopArmsRotate));

    XboxButtons.BUTTON_Y.whenPressed(new InstantCommand(climber::RotateArmsBackwards));
    XboxButtons.BUTTON_Y.whenReleased(new InstantCommand(climber::StopArmsRotate));
    

    XboxButtons.BUTTON_X.whenPressed(new InstantCommand(climber::RotateBabyFowards));
    XboxButtons.BUTTON_X.whenReleased(new InstantCommand(climber::StopBabies));

    XboxButtons.BUTTON_B.whenPressed(new InstantCommand(climber::RotateBabyBackwards));
    XboxButtons.BUTTON_B.whenReleased(new InstantCommand(climber::StopBabies));

  /*
    XboxButtons.LEFT_BUMPER.whileHeld(new StartEndCommand(climber::ExtendArms, climber::StopLongLongArms, climber));
    XboxButtons.RIGHT_BUMPER.whileHeld(new StartEndCommand(climber::ContractArms, climber::StopLongLongArms, climber));

    XboxButtons.BUTTON_Y.whileHeld(new StartEndCommand(climber::RotateArmsForwards, climber::StopArmsRotate, climber));
    XboxButtons.BUTTON_A.whileHeld(new StartEndCommand(climber::RotateArmsBackwards, climber::StopArmsRotate, climber));

    XboxButtons.BUTTON_X.whileHeld(new StartEndCommand(climber::RotateBabyFowards, climber::StopBabies, climber));
    XboxButtons.BUTTON_B.whileHeld(new StartEndCommand(climber::RotateBabyBackwards, climber::StopBabies, climber));
  */    
  }

  /*public Command getAutonomousCommand() {
    return null;
  }
  
    // Drive at half speed when the right bumper is held
    

  //TeleOp Commands
 // public DriveByJoysticks teleOpDrive = new DriveByJoysticks(m_drive);

  //Autonomous Commands 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @param List 
   *
   * @return the command to run in autonomous
   */
}
