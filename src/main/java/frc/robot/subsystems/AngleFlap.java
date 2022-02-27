// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import com.revrobotics.RelativeEncoder;

public class AngleFlap extends SubsystemBase {
  /** Creates a new AngleFlap. */

  private CANSparkMax angle;
  private Limelight m_limelight;

  public AngleFlap(CANSparkMax angle) {
    //setDefaultCommand(new Shoot(this));
    this.angle = angle;
    this.m_limelight = new Limelight();
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ResetEncoders() {
    this.angle.getEncoder().setPosition(0);
  }

  public static class fEncoderValues {
    public static double angle;
  }

}
