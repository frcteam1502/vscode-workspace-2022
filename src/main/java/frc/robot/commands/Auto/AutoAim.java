package frc.robot.commands.Auto;

import frc.robot.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AutoAim extends CommandBase {
  private Turret turret;
  private Shooter shooter;
  private final PIDController rotationController = new PIDController(30e-3, 0, 0);

  public AutoAim(Turret turret, Shooter shooter) {
    addRequirements(turret, shooter);
    this.turret = turret;
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    rotationController.reset();
  }

  @Override
  public void execute() {
    Limelight.Target m_limelight = Limelight.getTarget();

    double error = m_limelight.tx;
    double offset = rotationController.getCorrection(error);

    turret.turnTurret(-offset);

    shooter.moveHoodAutomatically();
  }

  @Override
  public void end(boolean interrupted) {
    turret.turretSet(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
