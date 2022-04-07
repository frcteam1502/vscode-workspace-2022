package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.Motors;

import com.revrobotics.CANSparkMax;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private Timer timer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Gyro", m_robotContainer.drive.m_gyro.getAngle());
    SmartDashboard.putNumber("Average Encoder Pose", ((Motors.DRIVE_FRONT_LEFT.getEncoder().getPosition() + -Motors.DRIVE_FRONT_RIGHT.getEncoder().getPosition()) / 2.0));
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
    // timer = new Timer();
    // timer.start();
  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    // if(timer.hasElapsed(1.5)) Motors.INTAKE.set(.75);
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
  public void teleopPeriodic() {
  //  Constants.Motors.LEFT_ARM_EXTENDER.getEncoder().getPosition();
    m_robotContainer.moveTurret.execute();
    SmartDashboard.putBoolean("Ready To Shoot", RobotContainer.hoodInPos && RobotContainer.TurretCenterd);
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
