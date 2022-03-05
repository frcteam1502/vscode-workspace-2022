package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AutoSimple extends CommandBase {
  private Drivetrain drivetrain;
  private Intake intake;
  private Shooter shooter;
  private Climber climber;
  private Turret turret;

  public AutoSimple(Drivetrain drivetrain, Intake intake, Shooter shooter, Climber climber, Turret turret){
    addRequirements(drivetrain, intake, shooter, climber);
    this.drivetrain = drivetrain;
    this.intake = intake;
    this.shooter = shooter;
    this.climber = climber;
    this.turret = turret;
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
    intake.moveIntake();
    //drop intake
    climber.RotateArmsForwards();
    Timer.delay(2);
    climber.StopArmsRotate();
    //move out of box and pickup 2nd ball
    drive(.2);
    Timer.delay(1.2);
    //stop driving
    drive(0);
    //Aim
    while(!turret.turnTurret(.3));
    //shoot
    shooter.indexBall();
    Timer.delay(3);
    //stop index
    shooter.indexBallStop();
    Timer.delay(2);
    //shoot
    shooter.indexBall();
  }

  public void drive(double power) {
    Constants.Motors.DRIVE_FRONT_LEFT.set(power);
    Constants.Motors.DRIVE_FRONT_RIGHT.set(-power);
    Constants.Motors.DRIVE_BACK_LEFT.set(power);
    Constants.Motors.DRIVE_BACK_RIGHT.set(-power);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stopmotors();
    intake.stopIntake();
    shooter.indexBallStop();
    shooter.noShoot();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
