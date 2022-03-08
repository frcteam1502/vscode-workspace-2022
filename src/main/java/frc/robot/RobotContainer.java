package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Auto.AutoSimple;
import frc.robot.commands.Auto.blue1;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.PathFindingConstants.AutoConstants;
import frc.robot.PathFindingConstants.DriveConstants;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.Shoot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Constants.Motors;
import frc.robot.Constants.XboxButtons;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.Shoot;
import frc.robot.commands.UpdateEncoders;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {
  //TeleOp Subsystems
  private final Intake intake = new Intake(Motors.INTAKE);

  private final Shooter shooter = new Shooter(Motors.SHOOTER_RIGHT, Motors.SHOOTER_LEFT, Motors.INDEX);

  private final Turret m_robotTurret = new Turret(Motors.TURRET);

  public final Drivetrain m_drive = new Drivetrain();
  
  private final Climber climber = new Climber(Motors.LEFT_ARM_EXTENDER, Motors.RIGHT_ARM_EXTENDER, Motors.LEFT_ARM_ANGLE, Motors.RIGHT_ARM_ANGLE, Motors.LEFT_BABY, Motors.RIGHT_BABY);  

  //TeleOp Commands
  private final RunIntake runIntake = new RunIntake(intake);

  private final MoveTurret m_robotMoveTurret = new MoveTurret(m_robotTurret);

  private final Shoot m_robotShoot = new Shoot(shooter);

  private UpdateEncoders updateEncoders = new UpdateEncoders(climber);

  private DriveByJoysticks driveByJoysticks = new DriveByJoysticks(m_drive);

  //Autonomous Commands
  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public AutoSimple simpleAuto = new AutoSimple(m_drive, intake, shooter, climber, m_robotTurret);
 
  // TODO: Need to add the "shooting" aspect
  public blue1 blue1 = new blue1(m_drive, intake);

  public RobotContainer() {
    configureButtonBindings();
    setUpMChooser();
  }
  
  private void configureButtonBindings() {
    XboxButtons.LEFT_BUMPER.whileHeld(new StartEndCommand(climber::ExtendArms, climber::StopLongLongArms, climber));
    XboxButtons.RIGHT_BUMPER.whileHeld(new StartEndCommand(climber::ContractArms, climber::StopLongLongArms, climber));

    XboxButtons.BUTTON_Y.whileHeld(new StartEndCommand(climber::RotateArmsForwards, climber::StopArmsRotate, climber));
    XboxButtons.BUTTON_A.whileHeld(new StartEndCommand(climber::RotateArmsBackwards, climber::StopArmsRotate, climber));

    XboxButtons.BUTTON_X.whileHeld(new StartEndCommand(climber::RotateBabyFowards, climber::StopBabies, climber));
    XboxButtons.BUTTON_B.whileHeld(new StartEndCommand(climber::RotateBabyBackwards, climber::StopBabies, climber));
  }

  //Sets up the sendable chooser for Autonomous
  private void setUpMChooser() {
    m_chooser.setDefaultOption("Simple Auto", simpleAuto);
    m_chooser.addOption("Blue 1", blue1);
    SmartDashboard.putData(m_chooser);
  }
  
  //An accessor method to be used in Robot to find the selected Auto
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}  
