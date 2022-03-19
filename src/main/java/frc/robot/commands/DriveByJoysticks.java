package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Drivetrain;

public class DriveByJoysticks extends CommandBase {
  private Drivetrain drivetrain;
  
  public DriveByJoysticks(Drivetrain subsystem) {
    addRequirements(subsystem);
    drivetrain = subsystem;
  }

  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.MecanumDrive(Joysticks.DRIVE_CONTROLLER.getLeftX(), -Joysticks.DRIVE_CONTROLLER.getLeftY(), Joysticks.DRIVE_CONTROLLER.getRightX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
