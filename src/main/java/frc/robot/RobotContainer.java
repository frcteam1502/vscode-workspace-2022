package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PathFindingConstants.OIConstants;
import frc.robot.commands.AutoSimple;
import frc.robot.commands.DriveByJoysticks;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  //TeleOp Subsystems
  private final Drivetrain m_drive = new Drivetrain();
  public Intake intake = new Intake(Constants.INTAKE);

  //TeleOp Commands
  public DriveByJoysticks teleOpDrive = new DriveByJoysticks(m_drive);
  public RunIntake runIntake = new RunIntake(intake);

  //Autonomous Commands
  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public AutoSimple simpleAuto = new AutoSimple(m_drive, intake);

    //Blue 1
  public ParallelCommandGroup blue1_seg1 = new ParallelCommandGroup(m_drive.createTrajectoryCommand("Blue1_Seg1"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake));
  public Command blue1_seg2 = m_drive.createTrajectoryCommand("Blue1_Seg2");
  public ParallelCommandGroup blue1_seg3 = new ParallelCommandGroup(m_drive.createTrajectoryCommand("Blue1_Seg3"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake));
  public Command blue1_seg4 = m_drive.createTrajectoryCommand("Blue1_Seg4");
  public Command blue1_seg5 = m_drive.createTrajectoryCommand("Blue1_Seg5");
  // TODO: Need to add the "shooting" aspect
  public SequentialCommandGroup blue1 = new SequentialCommandGroup(blue1_seg1, blue1_seg2, blue1_seg3, blue1_seg4, blue1_seg5);

  //Controllers
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  public RobotContainer() {
    m_drive.setDefaultCommand(new DriveByJoysticks(m_drive));
    configureButtonBindings();
    setUpMChooser();
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

  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
        .whenPressed(() -> m_drive.setMaxOutput(0.5))
        .whenReleased(() -> m_drive.setMaxOutput(1));
  }
}