package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends SubsystemBase {

  private CANSparkMax shooterRight, shooterLeft, indexWheel;


  public Shooter(CANSparkMax shooterRight, CANSparkMax shooterLeft, CANSparkMax indexWheel) {
    this.shooterRight = shooterRight;
    this.shooterLeft = shooterLeft;
    this.indexWheel = indexWheel;
  }

  @Override
  public void periodic() {}

  public void shoot(){
      shooterRight.set(0.8);
      shooterLeft.set(0.8);
    }
  
  public void noShoot(){
      shooterRight.set(0);
      shooterLeft.set(0);
  }

  public void indexBall(){
    indexWheel.set(0.5);
  }

  public void indexBallStop(){
    indexWheel.set(0);
  }
}