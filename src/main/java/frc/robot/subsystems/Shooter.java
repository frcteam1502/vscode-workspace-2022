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
    ArrayList<Double> hoodAngle = new ArrayList<Double>();
    ArrayList<Double> sepd = new ArrayList<Double>();

    hoodAngle.add(0,0.0);
    hoodAngle.add(1,0.0);
    hoodAngle.add(2,0.0);
    hoodAngle.add(3,0.0);
    hoodAngle.add(4,0.0);
    hoodAngle.add(5,0.0);
    hoodAngle.add(6,0.0);
    hoodAngle.add(7,0.0);
    hoodAngle.add(8,0.0);
    hoodAngle.add(9,0.0);

    sepd.add(0,0.0);
    sepd.add(1,0.0);
    sepd.add(2,0.0);
    sepd.add(3,0.0);
    sepd.add(4,0.0);
    sepd.add(5,0.0);
    sepd.add(6,0.0);
    sepd.add(7,0.0);
    sepd.add(8,0.0);
    sepd.add(9,0.0);
    



    
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
