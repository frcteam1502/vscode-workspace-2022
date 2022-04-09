package frc.robot.commands.Auto.ByTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveByTime extends CommandBase {
  private Drivetrain drive;
  private double Lspeed, Rspeed;

  public DriveByTime(Drivetrain drive, double leftSpeed, double rightSpeed) {
    addRequirements(drive);
    this.drive = drive;
    Lspeed = leftSpeed;
    Rspeed = -rightSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drive.TankDrive(Lspeed, Rspeed);
  }

  @Override
  public void end(boolean interrupted) {
    drive.Stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
