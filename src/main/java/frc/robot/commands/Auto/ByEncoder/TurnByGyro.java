package frc.robot.commands.Auto.ByEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnByGyro extends CommandBase {
  private Drivetrain drive;
  private double turnAngle;
  private PIDController turnController = new PIDController(7.5e-3, 0, 0);
  
  public TurnByGyro(Drivetrain drive, double turnAngle) {
    addRequirements(drive);
    this.drive = drive;
    this.turnAngle = turnAngle;
  }

  @Override
  public void initialize() {
    drive.m_gyro.reset();
    //set up turnController
    turnController.reset();
    turnController.setSetpoint(turnAngle);
    turnController.setTolerance(3);
    turnController.setIntegratorRange(-.2, .2);
  }

  @Override
  public void execute() {
    double offset = turnController.calculate(drive.m_gyro.getAngle());
    drive.TankDrive(-offset, -offset);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopmotors();
  }

  @Override
  public boolean isFinished() {
    SmartDashboard.putBoolean("Done", turnController.atSetpoint());
    return turnController.atSetpoint();
  }
}
