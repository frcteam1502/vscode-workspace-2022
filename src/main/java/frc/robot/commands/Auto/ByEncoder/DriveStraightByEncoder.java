package frc.robot.commands.Auto.ByEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraightByEncoder extends CommandBase {
  private Drivetrain drive;
  private PIDController distanceController = new PIDController(5e-3, 0, 0);
  private PIDController rotationController = new PIDController(5e-3, 0, 0);
  private double initialHeading, goalDistance;

  public DriveStraightByEncoder(Drivetrain drive, double goalDistanceMeters) {
    addRequirements(drive);
    this.drive = drive;
    this.goalDistance = goalDistanceMeters;
  }

  @Override
  public void initialize() {
    drive.resetEncoders();
    drive.m_gyro.reset();
    initialHeading = drive.m_gyro.getAngle();
    distanceController.reset();
    rotationController.reset();
  }

  @Override
  public void execute() {
    double power = distanceController.calculate(drive.getAverageEncoderDistance(), goalDistance);
    rotationController.setSetpoint(drive.m_gyro.getAngle() - initialHeading);
    double offset = rotationController.getPositionError();
    drive.TankDrive(power - offset, power + offset);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopmotors();
  }

  @Override
  public boolean isFinished() {
    return goalDistance <= drive.getAverageEncoderDistance(); //false
  }
}
