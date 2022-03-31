package frc.robot.commands.Auto.Encoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraightByEncoder extends CommandBase {
  private Drivetrain drive;
  private PIDController distanceController = new PIDController(5.3e-1, 0, 0);
  private PIDController rotationController = new PIDController(5e-2, 0, 0);
  private double goalDistance;

  public DriveStraightByEncoder(Drivetrain drive, double goalDistanceMeters) {
    addRequirements(drive);
    this.drive = drive;
    this.goalDistance = goalDistanceMeters;
  }

  @Override
  public void initialize() {
    //reset encoders
    drive.resetEncoders();
    //reset gyro to 0 degrees
    drive.m_gyro.reset();
    //set up distance PID
    distanceController.reset();
    distanceController.setSetpoint(goalDistance);
    distanceController.setTolerance(.1);
    distanceController.setIntegratorRange(-.9, .9);
    //set up rotation controlling PID
    rotationController.reset();
    rotationController.setSetpoint(drive.m_gyro.getAngle());
  }

  @Override
  public void execute() {
    double power = distanceController.calculate(drive.getAverageEncoderDistance());
    double offset = rotationController.calculate(drive.m_gyro.getAngle());
    drive.TankDrive(power - offset, -power - offset);
  }

  @Override
  public void end(boolean interrupted) {
    drive.Stop();
  }

  @Override
  public boolean isFinished() {
    return distanceController.atSetpoint();
  }
}