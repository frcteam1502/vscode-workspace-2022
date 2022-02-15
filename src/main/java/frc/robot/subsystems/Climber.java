// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Temp;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, armAngle, leftBaby, rightBaby;

  /** Creates a new Climber. */
  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax armAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.armAngle = armAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;

    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);

    setDefaultCommand(new Temp(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void MoveMotor() {
    leftBaby.set(0.1);
  }

  // Stops
  public void StopLongLongArms() {
    rightExtender.set(0);
    leftExtender.set(0);
  }
  public void StopLongArmRotate() {
    armAngle.set(0);
  }
  public void StopBabies() {
    leftBaby.set(0);
    rightBaby.set(0);
  }

  // Extend/Contract Arms
  public void MoveLeftArm(double speed) {
    leftExtender.set(speed);
  }
  public void MoveRightArm(double speed) {
    rightExtender.set(speed);
  }

  public void ExtendArms() {
    leftExtender.set(0.2);
    rightExtender.set(-0.2);
  }

  public void ContractArms() {
    leftExtender.set(-0.2);
    rightExtender.set(0.2);
  }

  //Rotate Arms
  public void RotateBigArmsClockwise() {
    armAngle.set(0.8);
  }
  public void RotateBigArmsCounterClockwise() {
    armAngle.set(-0.8);
  }

  // Rotate Babies
  public void RotateBabiesClockwise() {
    leftBaby.set(0.1);
    rightBaby.set(-0.1);
  }
  public void RotateBabiesCounterClockwise() {
    leftBaby.set(-0.1);
    rightBaby.set(0.1);
  }
  public void RotateLeftBaby(double speed) {
    leftBaby.set(speed);
  }
  public void RotateRightBaby(double speed) {
    rightBaby.set(speed);
  }


  public void ResetEncoders() {
    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
  }
  public RelativeEncoder GetEncoders(String motor) {
    switch (motor) {
      case "Left Extender":
        return leftExtender.getEncoder();
      case "Right Extender":
        return rightExtender.getEncoder();
      case "Arm Angle":
        return armAngle.getEncoder();
      case "Left Baby":
        return leftBaby.getEncoder();
      case "Right Baby":
        return rightBaby.getEncoder();
      default:
        return null;
    }
  }
}
