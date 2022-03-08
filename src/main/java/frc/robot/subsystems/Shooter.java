// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.commands.Shoot;


public class Shooter extends SubsystemBase {

  private CANSparkMax shooterRight, shooterLeft, indexWheel, angle;
  double[] hoodAngle;
  double[] speed;
  


  public Shooter(CANSparkMax shooterRight, CANSparkMax shooterLeft, CANSparkMax indexWheel, CANSparkMax angle) {
    setDefaultCommand(new Shoot(this));
    this.shooterRight = shooterRight;
    this.shooterLeft = shooterLeft;
    this.indexWheel = indexWheel;
    this.angle = angle;
    hoodAngle = new double[7];
    speed = new double[7];
    

    hoodAngle[0] = 7.595; 
    hoodAngle[1] = 28.785; 
    hoodAngle[2] = 45.499; 
    hoodAngle[3] = 59.786; 
    hoodAngle[4] = 55.64; 
    hoodAngle[5] = 57.19; 
    hoodAngle[6] = 70.263; 

    speed[0] = 0.8;
    speed[1] = 0.8;
    speed[2] = 0.8;
    speed[3] = 0.8;
    speed[4] = 0.85;
    speed[5] = 0.95;
    speed[6] = 0.95;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double speed){

      shooterRight.set(0.95);
      shooterLeft.set(0.95);
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

  public void moveHoodAutomatically(){
    Limelight.Target m_limelight = Limelight.getTarget();
    if(m_limelight.ty >= 11.9){
      moveHoodToTarget(0);
      shoot(0.8);
    } else if (m_limelight.ty < 11.9 && m_limelight.ty >= 6.3) {
      moveHoodToTarget(1);
      shoot(0.8);
    } else if (m_limelight.ty < 6.3 && m_limelight.ty >= 2.65) {
      moveHoodToTarget(2);
      shoot(0.8);
    } else if (m_limelight.ty < 2.65 && m_limelight.ty >= -0.28) {
      moveHoodToTarget(3);
      shoot(0.8);
    } else if (m_limelight.ty < -0.28 && m_limelight.ty >= -2.99) {
      moveHoodToTarget(4);
      shoot(0.85); 
    } else if (m_limelight.ty < -2.99 && m_limelight.ty >= -5.23) {
      moveHoodToTarget(5);
      shoot(0.95);
    } else if (m_limelight.ty < -5.23) {
      moveHoodToTarget(6);
      shoot(0.95);
    }
  }

  private void moveHoodToTarget(int target) {
    if(EncoderValues.angle < hoodAngle[target]) {
      angle.set(0.01);
    } else if (EncoderValues.angle < hoodAngle[target]) {
      angle.set(-0.01);
    } else {
      angle.set(0);
    }
  }

  public void angleUp() {
    angle.set(0.05);
  }

  public void angleDown() {
    angle.set(-0.05);
  }
  
  public void stopAngle() {
    angle.set(0);
  }
}
