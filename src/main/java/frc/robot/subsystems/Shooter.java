// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(){

      shooterRight.set(0.75);
      shooterLeft.set(0.75);
    }
  
  public void noShoot(){

      shooterRight.set(0);
      shooterLeft.set(0);
  }

  public void indexBall(){

    indexWheel.set(0.55);
  }

  public void indexBallStop(){

    indexWheel.set(0);
  }


}
