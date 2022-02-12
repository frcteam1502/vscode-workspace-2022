package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.RunIntake;
import frc.robot.commands.blue1;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.AutoSimple;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  //TeleOp Subsystems
  public final Drivetrain m_drive = new Drivetrain();
  public Intake intake = new Intake(Constants.INTAKE);

  //TeleOp Commands
  public RunIntake runIntake = new RunIntake(intake);

  //Autonomous Commands
  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public AutoSimple simpleAuto = new AutoSimple(m_drive, intake);
 
  // TODO: Need to add the "shooting" aspect
  public blue1 blue1 = new blue1(m_drive, intake);

  public RobotContainer() {
    m_drive.setDefaultCommand(new RunCommand(() -> m_drive.move(
            Joysticks.rightJoystick.getX(),
            Joysticks.rightJoystick.getY(),
            Joysticks.rightJoystick.getZ()), 
          m_drive));
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

  private void configureButtonBindings() {}
}
// Change differnetial drive to mechanum
// Change move
// Change RamseteCommand to pathplanner PPSwerveControllerCommand
// /\ Use _____ instead of tankVolts
// Move sequential Command