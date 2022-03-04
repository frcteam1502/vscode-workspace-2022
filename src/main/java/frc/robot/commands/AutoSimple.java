package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class AutoSimple extends CommandBase {
  private Drivetrain drivetrain;
  private Intake intake;
  private Shooter shooter;

  public AutoSimple(Drivetrain drivetrain, Intake intake, Shooter shooter) {
    addRequirements(drivetrain, intake, shooter);
    this.drivetrain = drivetrain;
    this.intake = intake;
    this.shooter = shooter;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    /* 
    Start
    1) Start shooter motor
    2) Run intake
    3) Move out of box / pickup ball
    4) Shoot ball 1
    5) Index 2nd ball
    6) Shoot ball 2
    End
    */
    
    //start shooter
    shooter.shoot();
    //start intake
    intake.runIntakeForward();
    //move out of box and pickup 2nd ball
    drivetrain.move(0, .5, 0);
    Timer.delay(3);
    //shoot
    shooter.indexBall();
    Timer.delay(4);
    //shoot
    shooter.indexBall();
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0, 0);
    intake.stopIntake();
    shooter.indexBallStop();
    shooter.noShoot();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
