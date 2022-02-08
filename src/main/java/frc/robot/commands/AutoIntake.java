package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoIntake extends CommandBase {
  Intake subsystem;

  public AutoIntake(Intake subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void execute() {
    subsystem.runIntakeForward();
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}