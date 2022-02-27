// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Limelight;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import frc.robot.commands.UpdateEncoders;

public class AngleFlap extends SubsystemBase {
  /** Creates a new AngleFlap. */

  private CANSparkMax flap;
  private Limelight m_limelight;

  



  public AngleFlap(CANSparkMax flap) {
    //setDefaultCommand(new Shoot(this));
    this.flap = flap;
    this.m_limelight = new Limelight();
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //TODO: test shoots at diffrent limelight y levels
  private double angle = -1;

  private double Angle(){
    if (!m_limelight.target){// no target
      double angle = -1;
    }
    else if(m_limelight.y <= -5){//shoot zone 1

    }
    else if(m_limelight.y <= 5 && m_limelight.y > -5){//shoot zone 2

    }
    else if(m_limelight.y <= 15 && m_limelight.y > 5){//shoot zone 3
      
    }
    else if(m_limelight.y <= 25 && m_limelight.y > 15){//shoot zone 4
      
    }
    return angle;
  }

  public void Moveflap(){
    if (Angle() < 0){
      flap.set(0);
    }
    //else if (Angle() >=  && Angle() <= -3){
      //TODO: make encoders work

    }
   

  }

  public void fResetEncoders() {
    this.flap.getEncoder().setPosition(0);
  }

  public static class fEncoderValues {
    public static double flap;
  }

}
