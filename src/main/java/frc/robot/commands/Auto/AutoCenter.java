package frc.robot.commands.Auto;

import frc.robot.PIDController;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.Turret;

public class AutoCenter extends CommandBase {
  private Turret turret;
  private final PIDController rotationController = new PIDController(30e-3, 0, 0);

  public AutoCenter(Turret turret) {
    addRequirements(turret);
    this.turret = turret;
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
  }

  @Override
  public void end(boolean interrupted) {
    turret.turretSet(0);
  }

  @Override
  public boolean isFinished() {
    Limelight.Target m_limelight = Limelight.getTarget();
    return RobotContainer.TurretCenterd || m_limelight.tv != 1;
  }
}
