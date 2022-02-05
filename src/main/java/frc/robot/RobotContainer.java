// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.Motors;
import frc.robot.PathFindingConstants.OIConstants;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.getAutonomousCommand;
import frc.robot.subsystems.AutoDriveSubsystem;
import frc.robot.subsystems.Drivetrain;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //TeleOp Subsystems
  private final Drivetrain m_drive = new Drivetrain(Motors.frontLeft, Motors.frontRight, Motors.backLeft, Motors.backRight);
  public Intake intake = new Intake(Constants.INTAKE);

  //TeleOp Commands
  public DriveByJoysticks teleOpDrive = new DriveByJoysticks(m_drive);
  public RunIntake runIntake = new RunIntake(intake);

  //Autonomous Subsystems
  private final AutoDriveSubsystem m_robotDrive = new AutoDriveSubsystem();

  //Autonomous Commands
  public getAutonomousCommand blue1_seg1 = new getAutonomousCommand(m_robotDrive, "Blue1_Seg1");
  public getAutonomousCommand blue1_seg2 = new getAutonomousCommand(m_robotDrive, "Blue1_Seg2");
  public getAutonomousCommand blue1_seg3 = new getAutonomousCommand(m_robotDrive, "Blue1_Seg3");
  public getAutonomousCommand blue1_seg4 = new getAutonomousCommand(m_robotDrive, "Blue1_Seg4");
  public getAutonomousCommand blue1_seg5 = new getAutonomousCommand(m_robotDrive, "Blue1_Seg5");

  //Controllers
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

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
  }
}
