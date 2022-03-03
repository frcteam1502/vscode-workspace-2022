package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoSimple extends CommandBase {
  Drivetrain drivetrain;
  Intake intake;

  public AutoSimple(Drivetrain drivetrain, Intake intake) {
    addRequirements(drivetrain);
    addRequirements(intake);
    this.drivetrain = drivetrain;
    this.intake = intake;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.runIntakeForward();
    drivetrain.move(0, .5, 0);
    Timer.delay(10);
    drivetrain.move(0, 0, 0);
    intake.stopIntake();
    //shoot
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
