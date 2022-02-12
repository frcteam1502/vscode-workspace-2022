// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.Motors;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  ;

  private final Shooter m_robotShooter = new Shooter(Motors.SHOOTER_RIGHT, Motors.SHOOTER_LEFT);
  private final Shoot m_robotShoot = new Shoot(m_robotShooter);

  private final Turret m_robotTurret = new Turret(Motors.TURRET);
  private final MoveTurret m_robotMoveTurret = new MoveTurret(m_robotTurret);

  private final Drivetrain m_drive = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);

  

  
  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  
    // Drive at half speed when the right bumper is held
    

  //TeleOp Commands
  public DriveByJoysticks teleOpDrive = new DriveByJoysticks(m_drive);

  //Autonomous Commands
 

 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @param List 
   *
   * @return the command to run in autonomous
   */

 
}
