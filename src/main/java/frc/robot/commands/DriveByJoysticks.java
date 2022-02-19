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
  public void initialize() {
    drivetrain.move(
      Joysticks.JOYSTICK.getY(),
      Joysticks.JOYSTICK.getX(),
      Joysticks.JOYSTICK.getZ());
  }

  @Override
  public void execute() {
    drivetrain.move(
      Joysticks.JOYSTICK.getX(),
      Joysticks.JOYSTICK.getY(),
      Joysticks.JOYSTICK.getZ());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
