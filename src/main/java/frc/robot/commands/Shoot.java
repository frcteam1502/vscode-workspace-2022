package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.EncoderValues;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;


public class Shoot extends CommandBase {
  private Shooter shooter;
  private boolean backButtonHasBeenReleased, shoot = true;
 
  public Shoot(Shooter subsystem) {
    addRequirements(subsystem);
    shooter = subsystem;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Turret.climbMode) {
      if(EncoderValues.angle > 2) {
        shooter.angleDown();
      } else {
        shooter.stopAngle();
      }
      shooter.noShoot();
      return;
    }

    if (XboxButtons.BACK.get() && backButtonHasBeenReleased) {
      backButtonHasBeenReleased = false;
      shoot = !shoot;
    } else if (!XboxButtons.BACK.get()) {
      backButtonHasBeenReleased = true;
    }

    if (shoot) shooter.shoot(0.8);
    else shooter.noShoot();

    if (Joysticks.MANIP_CONTROLLER.getRightY() < -0.9) shooter.indexBall();
    else shooter.indexBallStop();

    if(!Robot.inAuto) shooter.moveHoodAutomatically();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}