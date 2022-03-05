// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import frc.robot.Limelight;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MoveHood;


public class AngleFlap extends SubsystemBase {
//   /** Creates a new AngleFlap. */
//   private static Limelight m_limelight = Constants.limelight;
  private CANSparkMax angle;

  public AngleFlap(CANSparkMax angle) {
    //setDefaultCommand(new Shoot(this));
    this.angle = angle;
    setDefaultCommand(new MoveHood(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveHood(double speed) {
    angle.set(speed);
  }
}
