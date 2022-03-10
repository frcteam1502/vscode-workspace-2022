package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import com.revrobotics.CANSparkMax;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    Constants.Motors.DRIVE_BACK_LEFT.setIdleMode(CANSparkMax.IdleMode.kBrake);
    Constants.Motors.DRIVE_FRONT_LEFT.setIdleMode(CANSparkMax.IdleMode.kBrake);
    Constants.Motors.DRIVE_FRONT_RIGHT.setIdleMode(CANSparkMax.IdleMode.kBrake);
    Constants.Motors.DRIVE_BACK_RIGHT.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    Constants.Motors.DRIVE_BACK_LEFT.setIdleMode(CANSparkMax.IdleMode.kCoast);
    Constants.Motors.DRIVE_FRONT_LEFT.setIdleMode(CANSparkMax.IdleMode.kCoast);
    Constants.Motors.DRIVE_FRONT_RIGHT.setIdleMode(CANSparkMax.IdleMode.kCoast);
    Constants.Motors.DRIVE_BACK_RIGHT.setIdleMode(CANSparkMax.IdleMode.kCoast);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  //  Constants.Motors.LEFT_ARM_EXTENDER.getEncoder().getPosition();
    m_robotContainer.moveTurret.execute();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
