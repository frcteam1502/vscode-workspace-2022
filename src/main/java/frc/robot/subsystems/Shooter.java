package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import frc.robot.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.commands.Shoot;


public class Shooter extends SubsystemBase {

  private CANSparkMax shooterRight, shooterLeft, indexWheel, angle;
  private final PIDController angleController = new PIDController(20e-3, 0, 0);
  double[] hoodAngle;
  
  


  public Shooter(CANSparkMax shooterRight, CANSparkMax shooterLeft, CANSparkMax indexWheel, CANSparkMax angle) {
    setDefaultCommand(new Shoot(this));
    this.shooterRight = shooterRight;
    this.shooterLeft = shooterLeft;
    this.indexWheel = indexWheel;
    this.angle = angle;
    hoodAngle = new double[9];
    
    

    hoodAngle[0] = 0.1;
    hoodAngle[1] = 9.9;
    // 1 could be 9.9, need to test
    hoodAngle[2] = 14.785; 
    hoodAngle[3] = 15.56;
    hoodAngle[4] = 21.8;
    hoodAngle[5] = 25.499; 
    hoodAngle[6] = 32.786;
    hoodAngle[7] = 55.263;
    hoodAngle[8] = 23.7;

    
    
   

   

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double speed){
      shooterRight.set(speed);
      shooterLeft.set(speed);
    }
  
  public void noShoot(){
      shooterRight.set(0);
      shooterLeft.set(0);
  }
// HELP MY LEG
  public void indexBall(){
    indexWheel.set(0.9);
  }
  

  public void indexBallStop(){
    indexWheel.set(0);
  }

  public void moveHoodAutomatically(){
    Limelight.Target m_limelight = Limelight.getTarget();
    if(m_limelight.ta > 0){
      if(m_limelight.ty >= 9.5){
        moveHoodToTarget(0);
        shooterRight.set(0.7);
        shooterLeft.set(0.7);
     } else if (m_limelight.ty < 9.5 && m_limelight.ty >= 5.7) {
        moveHoodToTarget(1);
       shooterRight.set(0.725);
        shooterLeft.set(0.725);
      } else if (m_limelight.ty < 5.7 && m_limelight.ty >= 3.2) {
        moveHoodToTarget(2);
        shooterRight.set(0.74);
        shooterLeft.set(0.74);
      } else if (m_limelight.ty < 3.2 && m_limelight.ty >= 0.9) {
        moveHoodToTarget(2);
        shooterRight.set(0.75);
        shooterLeft.set(0.75);
      } else if (m_limelight.ty < 0.9 && m_limelight.ty >= -0.4) {
        moveHoodToTarget(3);
        shooterRight.set(0.765);
        shooterLeft.set(0.765);
      } else if (m_limelight.ty < -0.4 && m_limelight.ty >= -1.9) {
        moveHoodToTarget(4);
        shooterRight.set(0.775);
        shooterLeft.set(0.775);
      } else if (m_limelight.ty < -1.9 && m_limelight.ty >= -3.1) {
        moveHoodToTarget(8);
        shooterRight.set(0.79);
        shooterLeft.set(0.79);  
      } else if (m_limelight.ty < -3.1 && m_limelight.ty >= -5.5) {
        moveHoodToTarget(5);
        shooterRight.set(0.82);
        shooterLeft.set(0.82);
      } else if (m_limelight.ty < -5.5 && m_limelight.ty >= -8.9) {
        moveHoodToTarget(6);
        shooterRight.set(0.83);
        shooterLeft.set(0.83);
      } else {
        shooterRight.set(0.6);
        shooterLeft.set(0.6);
        moveHoodToTarget(7);
      }
    } else {
      moveHoodToTarget(7);
      shooterRight.set(0.4);
       shooterLeft.set(0.4);
      //  SmartDashboard.putNumber("limelight in code", m_limelight.ty);
    }
  }
  
  
  private void moveHoodToTarget(int target) {
    double error = EncoderValues.angle - hoodAngle[target];
    double offset = angleController.getCorrection(error);

    if(EncoderValues.angle < hoodAngle[target]) {
      angle.set(-offset);
    } else if (EncoderValues.angle > hoodAngle[target]) {
      angle.set(-offset);
    } else {
      angle.set(0);
    }
    SmartDashboard.putBoolean("Hood in Position", Math.abs(error) < 2);
    SmartDashboard.putBoolean("I Am High", target != 7);
    SmartDashboard.putNumber("Zone", target);
  }

  public void setAngle(double speed) {
    angle.set(speed);
  }

  public void angleUp() {
    angle.set(0.1);
  }

  public void angleDown() {
    angle.set(-0.1);
  }
  
  public void stopAngle() {
    angle.set(0);
  }

  public void runInAuto() {
    moveHoodAutomatically();
    indexBall();
  }

  public void stopInAuto() {
    stopAngle();
    indexBallStop();
    noShoot();
  }

  public void shootInAuto() {
    shoot(.7);
    indexBall();
  }
}