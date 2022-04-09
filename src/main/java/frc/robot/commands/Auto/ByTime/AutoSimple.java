package frc.robot.commands.Auto.ByTime;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Limelight;
import frc.robot.PIDController;
import frc.robot.Constants.Motors;
import frc.robot.subsystems.ActiveIndex;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AutoSimple extends CommandBase {
  private Intake intake;
  private Shooter shooter;
  private Climber climber;
  private Turret turret;
  private ActiveIndex activeIndex;
  private boolean IAlreadRan, iMissed = false;
  private boolean TurretCenterd;
  String breek = "no";
  double m_s_seepd = 0.4;

  public AutoSimple(Intake intake, Shooter shooter, Climber climber, Turret turret, ActiveIndex activeIndex){
    addRequirements(intake, shooter, climber, turret, activeIndex);
    this.intake = intake;
    this.shooter = shooter;
    this.climber = climber;
    this.turret = turret;
    this.activeIndex = activeIndex;
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
    shooter.shoot(.71);

    //drop intake
    climber.RotateArmsForwards();
    Timer.delay(1.3);
    climber.StopArmsRotate();

    //Start intake and active indexer
    intake.moveIntake();
    activeIndex.runIndex();

    //move out of box and pickup 2nd ball
    drive(.2);
    Timer.delay(1.5);

    //stop driving
    drive(0);
    while(!TurretCenterd && !iMissed) moveTurret();
    turret.turretSet(0);

    //shoot
    shooter.indexBall();
    Timer.delay(4);

    //Drive further out
    drive(0.2);
    Timer.delay(.75);
    drive(0);
    IAlreadRan = true;
  }

  

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    shooter.indexBallStop();
    shooter.noShoot();
    climber.StopArmsRotate();
    activeIndex.stopIndex();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
  public void drive(double power) {
    Constants.Motors.DRIVE_FRONT_LEFT.set(power);
    Constants.Motors.DRIVE_FRONT_RIGHT.set(-power);
    Constants.Motors.DRIVE_BACK_LEFT.set(power);
    Constants.Motors.DRIVE_BACK_RIGHT.set(-power);
  }

    private final PIDController rotationController = new PIDController(6e-3, 0, 0);
    public double TurretClimbMax = -100;
    
    private void moveTurret() {
      iMissed = (Motors.TURRET.getEncoder().getPosition() < -50 || Motors.TURRET.getEncoder().getPosition() > 50);
  
      if(TurretCenterd || iMissed) return; 
  
      Limelight.Target m_limelight = Limelight.getTarget();
  
      double error = m_limelight.tx;
      double offset = rotationController.getCorrection(error);
  
      turnTurret(-offset);
    }
  
    public void turnTurret(double m_t_seepd) {
      Limelight.Target m_limelight = Limelight.getTarget();
  
      if (m_limelight.tv == 1){
  
        if ( (m_limelight.tx >= -0.5) && (m_limelight.tx <= 0.5)){ // THIS THING CHANGED IT WAS 0.75
          turret.turretSet(0);
          breek = "no";
          TurretCenterd = true;
        }
  
        else if (m_limelight.tx > 0.5){//change to right side of camera screen // THIS THING CHANGED IT WAS 0.75
          turret.turretSet(m_t_seepd);
        }  
  
        else if (m_limelight.tx < -0.5){//change to left side of camera screen // THIS THING CHANGED IT WAS 0.75
          turret.turretSet(m_t_seepd);
        }
      }
      else {
        if(breek == "no" || breek == "right"){
          turret.turretSet(m_s_seepd);
        }
        else{
          turret.turretSet(-m_s_seepd);
        }
      }
  }
}
