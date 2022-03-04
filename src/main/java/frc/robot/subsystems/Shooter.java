// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Shoot;


public class Shooter extends SubsystemBase {

  private CANSparkMax shooterRight, shooterLeft, indexWheel;
  


  public Shooter(CANSparkMax shooterRight, CANSparkMax shooterLeft, CANSparkMax indexWheel) {
    setDefaultCommand(new Shoot(this));
    this.shooterRight = shooterRight;
    this.shooterLeft = shooterLeft;
    this.indexWheel = indexWheel;
    double[] hoodAngle =new double[10];
    

    hoodAngle[0] = 0.0; //84in
    hoodAngle[1] = 0.0; //104.5in
    hoodAngle[2] = 0.0; //125in
    hoodAngle[3] = 0.0; //145.5in
    hoodAngle[4] = 0.0; //166in
    hoodAngle[5] = 0.0; //186.5in
    hoodAngle[6] = 0.0; //207in
    hoodAngle[7] = 0.0; //227.5in
    hoodAngle[8] = 0.0; //248in
    hoodAngle[9] = 0.0; //268.5in

    
    



    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

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
