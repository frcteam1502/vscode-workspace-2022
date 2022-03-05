package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    SmartDashboard.putNumber("Front Left Motor", m_robotContainer.m_drive.getMotor(0).get());
    SmartDashboard.putNumber("Front Right Motor", m_robotContainer.m_drive.getMotor(1).get());
    SmartDashboard.putNumber("Back Left Motor", m_robotContainer.m_drive.getMotor(2).get());
    SmartDashboard.putNumber("Back Right Motor", m_robotContainer.m_drive.getMotor(3).get());

    SmartDashboard.putNumber("Left Enc", m_robotContainer.m_drive.getMotor(0).getEncoder().getPosition());
    SmartDashboard.putNumber("Right Enc", m_robotContainer.m_drive.getMotor(2).getEncoder().getPosition());

    SmartDashboard.putNumber("Odo X", m_robotContainer.m_drive.m_odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("Odo Y", m_robotContainer.m_drive.m_odometry.getPoseMeters().getY());
    SmartDashboard.putNumber("Odo H", m_robotContainer.m_drive.m_odometry.getPoseMeters().getRotation().getDegrees());
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
  public void autonomousPeriodic() {
    m_robotContainer.m_drive.m_drive.setSafetyEnabled(false);
  }

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
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}