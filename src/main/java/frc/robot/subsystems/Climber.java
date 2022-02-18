// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  private final double extenderSpeed = 2.5;
  private final double rotateSpeed = 2.0;

  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftAngle, CANSparkMax rightAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.leftAngle = leftAngle;
    this.rightAngle = rightAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;

    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
    // TODO: Dear god please invert one of the motors. I'm begging you
  }

  @Override
  public void periodic() {}

  // Stops
  public void StopLongLongArms() {
    rightExtender.set(0);
    leftExtender.set(0);
  }
  public void StopLongArmRotate() {
    leftAngle.set(0);
    rightAngle.set(0);
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

  //Rotate Arms
  public void RotateBigArmsClockwise() {
    leftAngle.set(0.2);
    rightAngle.set(-0.2);
  }
  public void RotateBigArmsCounterClockwise() {
    leftAngle.set(-0.2);
    rightAngle.set(0.2);
  }
  public void RotateLeftBigArm(double speed) {
    leftAngle.set(speed);
  }
  public void RotateRightBigArm(double speed) {
    rightAngle.set(speed);
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
  // TODO: Decide if recusion is the right choice... I'm begining to think no
  //"Autonomously" Extend/Contract Arms
  public void ExtendArmsToEncoder(double value) {
    if(leftExtender.getEncoder().getPosition() < value && rightExtender.getEncoder().getPosition() < value) {
      MoveLeftArm(extenderSpeed);
      MoveRightArm(-extenderSpeed);
    } 
    else if(leftExtender.getEncoder().getPosition() < value) leftExtender.set(extenderSpeed);
    else if(rightExtender.getEncoder().getPosition() < value) rightExtender.set(-extenderSpeed);
    if(leftExtender.getEncoder().getPosition() < value || rightExtender.getEncoder().getPosition() < value) ExtendArmsToEncoder(value);
  }

  public void RetractArmsToEncoder(double value) {
    if(leftExtender.getEncoder().getPosition() > value && rightExtender.getEncoder().getPosition() > value) {
      MoveLeftArm(-extenderSpeed);
      MoveRightArm(extenderSpeed);
    } 
    else if(leftExtender.getEncoder().getPosition() > value) leftExtender.set(-extenderSpeed);
    else if(rightExtender.getEncoder().getPosition() > value) rightExtender.set(extenderSpeed);
    if(leftExtender.getEncoder().getPosition() > value || rightExtender.getEncoder().getPosition() > value) RetractArmsToEncoder(value);
  }

  //"Autonomously" Rotate Arms
  public void RotateArmsClockwiseToEncoder(double value) {
    if(leftAngle.getEncoder().getPosition() < value && rightAngle.getEncoder().getPosition() < value)
      RotateBigArmsClockwise();
    else if(leftAngle.getEncoder().getPosition() < value) RotateLeftBigArm(rotateSpeed);
    else if(rightAngle.getEncoder().getPosition() < value) RotateRightBigArm(-rotateSpeed);
    if(leftAngle.getEncoder().getPosition() < value && rightAngle.getEncoder().getPosition() < value) RotateArmsClockwiseToEncoder(value);
  }

  public void RotateArmsCounterClockwiseToEncoder(double value) {
    if(leftAngle.getEncoder().getPosition() > value && rightAngle.getEncoder().getPosition() > value)
      RotateBigArmsClockwise();
    else if(leftAngle.getEncoder().getPosition() > value) RotateLeftBigArm(-rotateSpeed);
    else if(rightAngle.getEncoder().getPosition() > value) RotateRightBigArm(rotateSpeed);
    if(leftAngle.getEncoder().getPosition() > value || rightAngle.getEncoder().getPosition() > value) RotateArmsCounterClockwiseToEncoder(value);
  }

  //"Autonomously" Rotate Babies
  // TODO: We haven't decided hhow we're going to implement baby rotation to confirm its off

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
      case "Left Angle":
        return leftAngle.getEncoder();
      case "Right Angle":
        return rightAngle.getEncoder();
      case "Left Baby":
        return leftBaby.getEncoder();
      case "Right Baby":
        return rightBaby.getEncoder();
      default:
        return null;
    }
  }
}
