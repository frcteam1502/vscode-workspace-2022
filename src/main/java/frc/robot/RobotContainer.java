// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.Motors;
//import frc.robot.Constants.XboxButtons;
//import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.MoveTurret;
//import frc.robot.commands.Shoot;
//import frc.robot.commands.UpdateEncoders;
//import frc.robot.subsystems.Climber;
//import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {
  
  // The robot's subsystems and commands are defined here...
  /*
  private final Drivetrain drivetrain = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);
  private Climber climber = new Climber(
  Motors.LEFT_ARM_EXTENDER, Motors.RIGHT_ARM_EXTENDER, Motors.LEFT_ARM_ANGLE, Motors.RIGHT_ARM_ANGLE, Motors.LEFT_BABY, Motors.RIGHT_BABY);
  */
  private static Turret turret = new Turret(Motors.TURRET);
  /*
  private Shooter shooter = new Shooter(Motors.SHOOTER_RIGHT, Motors.SHOOTER_LEFT, Motors.INDEX);
  

  private UpdateEncoders updateEncoders = new UpdateEncoders(climber);
  private DriveByJoysticks driveByJoysticks = new DriveByJoysticks(drivetrain);
  */
  public MoveTurret moveTurret = new MoveTurret(turret);
  
  //private Shoot shoot = new Shoot(shooter);

  /*public RobotContainer() {
    configureButtonBindings();
  }
  private final Drivetrain m_drive = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_BACK_RIGHT);*/
  

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link JoystickButton}.
   */
  /*  private void configureButtonBindings() {
    XboxButtons.LEFT_BUMPER.whileHeld(new StartEndCommand(climber::ExtendArms, climber::StopLongLongArms, climber));
    XboxButtons.RIGHT_BUMPER.whileHeld(new StartEndCommand(climber::ContractArms, climber::StopLongLongArms, climber));

    XboxButtons.BUTTON_Y.whileHeld(new StartEndCommand(climber::RotateArmsForwards, climber::StopArmsRotate, climber));
    XboxButtons.BUTTON_A.whileHeld(new StartEndCommand(climber::RotateArmsBackwards, climber::StopArmsRotate, climber));

    XboxButtons.BUTTON_X.whileHeld(new StartEndCommand(climber::RotateBabyFowards, climber::StopBabies, climber));
    XboxButtons.BUTTON_B.whileHeld(new StartEndCommand(climber::RotateBabyBackwards, climber::StopBabies, climber));

    
  }*/

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
