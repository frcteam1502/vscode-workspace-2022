// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.UpdateEncoders;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftArmAngle, rightArmAngle, leftBaby, rightBaby;
  public boolean individualMode = false;

  /** Creates a new Climber. */
  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftArmAngle, CANSparkMax rightArmAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    setDefaultCommand(new UpdateEncoders(this));
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.leftArmAngle = leftArmAngle;
    this.rightArmAngle = rightArmAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void toggleMode() {
    SmartDashboard.putBoolean("Individual Mode", individualMode);
    individualMode = !individualMode;
  }

  // Stops
  public void StopLongLongArms() {
    rightExtender.set(0);
    leftExtender.set(0);
  }
  public void StopLeftArm() {
    leftExtender.set(0);
  }
  public void StopRightArm() {
    rightExtender.set(0);
  }
  public void StopArmsRotate() {
    leftArmAngle.set(0);
    rightArmAngle.set(0);
  }
  public void StopLeftArmRotate() {
    leftArmAngle.set(0);
  }
  public void StopRightArmRotate() {
    rightArmAngle.set(0);
  }
  public void StopBabies() {
    leftBaby.set(0);
    rightBaby.set(0);
  }
  public void StopLeftBaby() {
    leftBaby.set(0);
  }
  public void StopRightBaby() {
    rightBaby.set(0);
  }

  // Move Arms
  public void ExtendArms() {
    leftExtender.set(0.2);
    rightExtender.set(-0.2);
  }
  public void ContractArms() {
    leftExtender.set(-0.2);
    rightExtender.set(0.2);
  }

  public void MoveLeftArm(double speed) {
    leftExtender.set(speed);
  }
  public void MoveRightArm(double speed) {
    rightExtender.set(speed);
  }
  

  // Rotate Arms
  public void RotateArmsForwards() {
    leftArmAngle.set(0.1);
    rightArmAngle.set(-0.1);
  }
  public void RotateArmsBackwards() {
    leftArmAngle.set(-0.1);
    rightArmAngle.set(0.1);
  }

  public void RotateLeftArm(double speed) {
    leftArmAngle.set(speed);
  }
  public void RotateRightArm(double speed) {
    rightArmAngle.set(speed);
  }


  // Rotate Babies
  public void RotateBabyFowards() {
    leftBaby.set(0.1);
    rightBaby.set(0.1);
  }
  public void RotateBabyBackwards() {
    leftBaby.set(-0.1);
    rightBaby.set(-0.1);
  }

  public void RotateLeftBaby(double speed) {
    leftBaby.set(speed);
  }
  public void RotateRightBaby(double speed) {
    rightBaby.set(speed);
  }
  

  // Encoder Stuff
  public void ResetEncoders() {
    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
    this.leftArmAngle.getEncoder().setPosition(0);
    this.rightArmAngle.getEncoder().setPosition(0);
    this.leftBaby.getEncoder().setPosition(0);
    this.rightBaby.getEncoder().setPosition(0);
  }

  public static class EncoderValues {
    public static double leftArm, rightArm, leftArmAngle, rightArmAngle, leftBaby, rightBaby;
  }
}