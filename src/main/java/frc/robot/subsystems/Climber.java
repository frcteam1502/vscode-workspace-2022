// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.Constants.XboxButtons;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  private final double extenderSpeed = 2.5;
  private final double rotateSpeed = 2.0;
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
  }
  public void ContractArms() {
    leftExtender.set(-0.5);
    rightExtender.set(-0.5);
  }

  public void MoveLeftArm(double speed) {
    leftExtender.set(speed);
  }
  public void MoveRightArm(double speed) {
    rightExtender.set(speed);
  }
  

  // Rotate Arms, Uped to .4 for testing
  public void RotateArmsForwards() {
    leftAngle.set(0.4);
    rightAngle.set(-0.4);
  }
  public void RotateArmsBackwards() {
    leftAngle.set(-0.4);
    rightAngle.set(0.4);
  }

  public void RotateLeftArm(double speed) {
    leftAngle.set(speed);
  }
  public void RotateRightArm(double speed) {
    rightAngle.set(speed);
  }


  // Rotate Babies
  public void RotateBabyFowards() {
    leftBaby.set(0.3);
    rightBaby.set(0.3);
  }
  public void RotateBabyBackwards() {
    leftBaby.set(-0.3);
    rightBaby.set(-0.3);
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
    else StopLongLongArms();
  }

  public void RetractArmsToEncoder(double value) {
    if(leftExtender.getEncoder().getPosition() > value && rightExtender.getEncoder().getPosition() > value) {
      MoveLeftArm(-extenderSpeed);
      MoveRightArm(extenderSpeed);
    } 
    else if(leftExtender.getEncoder().getPosition() > value) leftExtender.set(-extenderSpeed);
    else if(rightExtender.getEncoder().getPosition() > value) rightExtender.set(extenderSpeed);
    else StopLongLongArms();
  }

  //"Autonomously" Rotate Arms
  public void RotateArmsForwardsToEncoder(double value) {
    if(leftAngle.getEncoder().getPosition() < value && rightAngle.getEncoder().getPosition() < value)
      RotateArmsForwards();
    else if(leftAngle.getEncoder().getPosition() < value) RotateLeftArm(rotateSpeed);
    else if(rightAngle.getEncoder().getPosition() < value) RotateRightArm(-rotateSpeed);
    else StopArmsRotate();
  }

  public void RotateArmsBackwardsToEncoder(double value) {
    if(leftAngle.getEncoder().getPosition() > value && rightAngle.getEncoder().getPosition() > value)
      RotateArmsBackwards();
    else if(leftAngle.getEncoder().getPosition() > value) RotateLeftArm(-rotateSpeed);
    else if(rightAngle.getEncoder().getPosition() > value) RotateRightArm(rotateSpeed);
    else StopArmsRotate();
  }

  //"Autonomously" Rotate Babies
  public void RotateBabiesForwardsToEncoder(double value) {
    if(leftBaby.getEncoder().getPosition() < value && rightBaby.getEncoder().getPosition() < value)
      RotateArmsForwards();
    else if(leftBaby.getEncoder().getPosition() < value) RotateLeftArm(rotateSpeed);
    else if(rightBaby.getEncoder().getPosition() < value) RotateRightArm(-rotateSpeed);
    else StopBabies();
  }

  public void RotateBabiesBackwardsToEncoder(double value) {
    if(leftBaby.getEncoder().getPosition() > value && rightBaby.getEncoder().getPosition() > value)
      RotateArmsBackwards();
    else if(leftBaby.getEncoder().getPosition() > value) RotateLeftArm(-rotateSpeed);
    else if(rightBaby.getEncoder().getPosition() > value) RotateRightArm(rotateSpeed);
    else StopBabies();
  }

  // Encoder Stuff









  
  // 3/5/2022 Encoder stuff was moved here. This class is so messy I hate it and want to purge all the unused methods especially the ones for one button climb (sorry Nick)
  private double leftSpeed, rightSpeed = 0.5;

  public void ExtendArmsEncoders() {
    if (EncoderValues.leftArm < EncoderMaxes.LEFT_MAX && EncoderValues.rightArm < EncoderMaxes.RIGHT_MAX) {
      MoveLeftArm(leftSpeed);
      MoveRightArm(rightSpeed);
    } else if (EncoderValues.leftArm < EncoderMaxes.LEFT_MAX) {
      MoveLeftArm(leftSpeed);
    } else if (EncoderValues.rightArm < EncoderMaxes.RIGHT_MAX) {
      MoveRightArm(rightSpeed);
    } else {
      StopLongLongArms();
    }
  } 

  public void ContractArmsEncoder() {
    if (EncoderValues.leftArm > 0 && EncoderValues.rightArm < 2) {
      MoveLeftArm(-leftSpeed);
      MoveRightArm(-rightSpeed);
    } else if (EncoderValues.leftArm > 0) {
      MoveLeftArm(-leftSpeed);
    } else if (EncoderValues.rightArm > 0) {
      MoveRightArm(-rightSpeed);
    } else {
      StopLongLongArms();
    }
  }

  public void RotateForwardsEncoder() {
    if (EncoderValues.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX && EncoderValues.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      RotateLeftArm(leftSpeed / 2);
      RotateRightArm(rightSpeed / 2);
    } else if (EncoderValues.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX) {
      RotateLeftArm(leftSpeed / 2);
    } else if (EncoderValues.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      RotateRightArm(rightSpeed / 2);
    } else {
      StopArmsRotate();
    }
  }

  public void RotateBackwardsEncoder() {
    if (EncoderValues.leftArmAngle > 0 && EncoderValues.rightArmAngle < 0) {
      RotateLeftArm(-leftSpeed / 2);
      RotateRightArm(-rightSpeed / 2);
    } else if (EncoderValues.leftArmAngle > 0) {
      RotateLeftArm(-leftSpeed / 2);
    } else if (EncoderValues.rightArmAngle > 0) {
      RotateRightArm(-rightSpeed / 2);
    } else {
      StopArmsRotate();
    }
  }
}
