// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;

import frc.robot.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.commands.Shoot;


public class Shooter extends SubsystemBase {

  private CANSparkMax shooterRight, shooterLeft, indexWheel, angle;
  double[] hoodAngle;
  
  


  public Shooter(CANSparkMax shooterRight, CANSparkMax shooterLeft, CANSparkMax indexWheel, CANSparkMax angle) {
    setDefaultCommand(new Shoot(this));
    this.shooterRight = shooterRight;
    this.shooterLeft = shooterLeft;
    this.indexWheel = indexWheel;
    this.angle = angle;
    hoodAngle = new double[8];
    
    

    hoodAngle[0] = 0.1; 
    hoodAngle[1] = 18.785; 
    hoodAngle[2] = 32.499; 
    hoodAngle[3] = 33.786;
    hoodAngle[4] = 55.263;
    hoodAngle[5] = 21.86;
    hoodAngle[6] = 7.3;
    hoodAngle[7] = 29.4;

   

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

    indexWheel.set(0.5);
  }

  public void indexBallStop(){

    indexWheel.set(0);
  }

  public void moveHoodAutomatically(){
    Limelight.Target m_limelight = Limelight.getTarget();
    if(m_limelight.ta > 0){
      if(m_limelight.ty >= 12.9){
        moveHoodToTarget(0);
        shooterRight.set(0.7);
        shooterLeft.set(0.7);
      } else if (m_limelight.ty < 10.9 && m_limelight.ty >= 12.9) {
        moveHoodToTarget(6);
       shooterRight.set(0.7);
        shooterLeft.set(0.7);
     } else if (m_limelight.ty < 10.9 && m_limelight.ty >= 6.3) {
        moveHoodToTarget(1);
       shooterRight.set(0.75);
        shooterLeft.set(0.75);
      } else if (m_limelight.ty < 6.3 && m_limelight.ty >= 2.65) {
        moveHoodToTarget(2);
        shooterRight.set(0.8);
        shooterLeft.set(0.8);
      } else if (m_limelight.ty < 2.65 && m_limelight.ty >= 0.9) {
        moveHoodToTarget(3);
        shooterRight.set(0.8);
        shooterLeft.set(0.8);
      } else if (m_limelight.ty < 0.9 && m_limelight.ty >= -0.6) {
        moveHoodToTarget(7);
        shooterRight.set(0.85);
        shooterLeft.set(0.85);
      } else if (m_limelight.ty < -0.6 && m_limelight.ty >= 0.0) {
        moveHoodToTarget(5);
        shooterRight.set(0.97);
        shooterLeft.set(0.97);
      } else {
        shooterRight.set(0.6);
        shooterLeft.set(0.6);
        moveHoodToTarget(4);
      }
    } else {
      moveHoodToTarget(4);
      shooterRight.set(0.4);
       shooterLeft.set(0.4);
       SmartDashboard.putNumber("limelight in code", m_limelight.ty);
    }
  }
  double dummy;
  private final PIDController angleController = new PIDController(6e-3, 0, 0);

 

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
    dummy = angle.get();
    SmartDashboard.putNumber("angle motor power", dummy);
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
}