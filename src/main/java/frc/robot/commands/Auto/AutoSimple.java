package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AutoSimple extends CommandBase {
  private Intake intake;
  private Shooter shooter;
  private Climber climber;
  private Turret turret;
  private boolean IAlreadRan = false;

  public AutoSimple(Intake intake, Shooter shooter, Climber climber, Turret turret){
    addRequirements(intake, shooter, climber);
    this.intake = intake;
    this.shooter = shooter;
    this.climber = climber;
    this.turret = turret;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(IAlreadRan) return;
    /* 
    Start
    1) Start shooter motor
    2) Run intake
    3) Drop intake
    4) Move out of box / pickup ball
    5) Shoot ball 1
    6) Index 2nd ball
    7) Shoot ball 2
    End
    */

    //start shooter
    //TODO:: undo
    // shooter.shoot(.7);
    //start intake
    intake.moveIntake();
    //drop intake
    climber.RotateArmsForwards();
    Timer.delay(1.8);
    climber.StopArmsRotate();
    //move out of box and pickup 2nd ball
    drive(.2);
    Timer.delay(1.5);
    //stop driving
    drive(0);
    //Aim
    //while(!turret.turnTurret(.3));
    //shoot
    shooter.indexBall();
    Timer.delay(3);
    //stop index
    shooter.indexBallStop();
    Timer.delay(2);
    //shoot
    shooter.indexBall();
    Timer.delay(5);
    //Move forward a hair
    drive(.2);
    Timer.delay(.5);
    drive(0);
    IAlreadRan = true;
  }

  public void drive(double power) {
    Constants.Motors.DRIVE_FRONT_LEFT.set(power);
    Constants.Motors.DRIVE_FRONT_RIGHT.set(-power);
    Constants.Motors.DRIVE_BACK_LEFT.set(power);
    Constants.Motors.DRIVE_BACK_RIGHT.set(-power);
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    shooter.indexBallStop();
    shooter.noShoot();
    climber.StopArmsRotate();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}