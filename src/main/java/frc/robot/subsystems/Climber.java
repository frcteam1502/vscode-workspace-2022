// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.XboxButtons;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  public boolean individualMode = false;

  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftArmAngle, CANSparkMax rightArmAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.leftAngle = leftArmAngle;
    this.rightAngle = rightArmAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;
  }

  @Override
  public void periodic() {}

  public void toggleMode() {
    SmartDashboard.putBoolean("Individual Mode", individualMode);
    individualMode = !individualMode;
  }

  // Stops
  public void StopLongLongArms() {
    if(XboxButtons.LEFT_BUMPER.get() || XboxButtons.RIGHT_BUMPER.get()) return;
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
    if(XboxButtons.BUTTON_Y.get() || XboxButtons.BUTTON_A.get()) return;
    leftAngle.set(0);
    rightAngle.set(0);
  }
  public void StopLeftArmRotate() {
    leftAngle.set(0);
  }
  public void StopRightArmRotate() {
    rightAngle.set(0);
  }
  public void StopBabies() {
    if(XboxButtons.BUTTON_X.get() || XboxButtons.BUTTON_B.get()) return;
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
    leftExtender.set(0.5);
    rightExtender.set(0.5);
  } // actually is contract ^
  public void ContractArms() {
    leftExtender.set(-0.65);
    rightExtender.set(-0.65);
  }
  public void MoveLeftArm(double speed) {
    leftExtender.set(speed);
  }
  public void MoveRightArm(double speed) {
    rightExtender.set(speed);
  }
  

  // Rotate Arms, Uped to .4 for testing
  public void RotateArmsForwards() {
    leftAngle.set(0.5);
    rightAngle.set(-0.5);
  }
  public void RotateArmsBackwards() {
    leftAngle.set(-0.5);
    rightAngle.set(0.5);
  }

  public void RotateLeftArm(double speed) {
    leftAngle.set(speed);
  }
  public void RotateRightArm(double speed) {
    rightAngle.set(speed);
  }

  // Rotate Babies
  public void RotateBabyFowards() {
    leftBaby.set(0.6);
    rightBaby.set(0.6);
  }
  public void RotateBabyBackwards() {
    leftBaby.set(-0.6);
    rightBaby.set(-0.6);
  }
}
