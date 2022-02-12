// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  
  // TODO: Find and save max ecoder positions
  private final double extenderMax = 30; // 100 
  private final double armRotateMax = 0;
  private final double babyMax = 0;

  /** Creates a new Climber. */
  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftAngle, CANSparkMax rightAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.leftAngle = leftAngle;
    this.rightAngle = rightAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;

    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ResetEncoders() {
    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
  }

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

  // Manually
  public void ExtendLongLongArmsManual() {
    leftExtender.set(0.2);
    // leftExtender.set(0.2);
    rightExtender.set(-0.2);
  }
  public void ContractLongLongArmsManual() {
    leftExtender.set(-0.2);
    // leftExtender.set(-0.2);
    rightExtender.set(0.2);
  }

  public void RotateBigArmsManualClockwise() {
    leftAngle.set(0.1);
    rightAngle.set(0.1);
  }

  public void RotateBigArmsManualCounterClockwise() {
    leftAngle.set(-0.1);
    rightAngle.set(-0.1);
  }

  public void RotateBabiesManualClockwise() {
    leftBaby.set(0.1);
    rightBaby.set(0.1);
  }

  public void RotateBabiesManualCounterClockwise() {
    leftBaby.set(-0.1);
    rightBaby.set(-0.1);
  }

  // Using encoders
  public void ExtendLongLongArmsEncoder() {
    boolean leftGreaterThanTarget = leftExtender.getEncoder().getPosition() > extenderMax;
    boolean rightGreaterThanTarget = -rightExtender.getEncoder().getPosition() > extenderMax;

    SmartDashboard.putNumber("leftExtender X", leftExtender.getEncoder().getPosition());
    SmartDashboard.putNumber("rightExtender X", -rightExtender.getEncoder().getPosition());

    SmartDashboard.putBoolean("Left less than target", leftGreaterThanTarget);
    SmartDashboard.putBoolean("Right less than target", rightGreaterThanTarget);

    if (!leftGreaterThanTarget && !rightGreaterThanTarget) {
      leftExtender.set(0.2);
      rightExtender.set(-0.2);
    } else if (!leftGreaterThanTarget) {
      leftExtender.set(-0.2);
    } else if (!rightGreaterThanTarget) {
      rightExtender.set(-0.2);
    } else {
      
    }
  }
  public void ContractLongLongArmsEncoder() {
    leftExtender.setInverted(true);

    if (leftExtender.getEncoder().getPosition() > extenderMax && rightExtender.getEncoder().getPosition() > extenderMax) {
      leftExtender.set(0.2);
      rightExtender.set(0.2);
    } else if (leftExtender.getEncoder().getPosition() > extenderMax && rightExtender.getEncoder().getPosition() < extenderMax) {
      leftExtender.set(0.2);
    } else if (leftExtender.getEncoder().getPosition() < extenderMax && rightExtender.getEncoder().getPosition() > extenderMax) {
      rightExtender.set(0.2);
    }
  }

  public void RotateBigArmsEcoder(double speed) {
    if(speed > 0 && leftAngle.getEncoder().getPosition() < armRotateMax && rightAngle.getEncoder().getPosition() < armRotateMax) {
      leftAngle.set(speed);
      rightAngle.set(speed);
    }
    else if(speed < 0 && leftAngle.getEncoder().getPosition() > 0 && rightAngle.getEncoder().getPosition() > 0) {
      leftAngle.set(speed);
      rightAngle.set(speed);
    }
  }

  public void RotateBabiesEncoder(double speed) {
    if(speed > 0 && leftBaby.getEncoder().getPosition() < babyMax && rightBaby.getEncoder().getPosition() < babyMax) {
      leftBaby.set(speed);
      rightBaby.set(speed);
    }
    else if(speed < 0 && leftBaby.getEncoder().getPosition() > 0 && rightBaby.getEncoder().getPosition() > 0) {
      leftBaby.set(speed);
      rightBaby.set(speed);
    }
  }

  /**
   * 
   * @param motor leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby
   * @return Encoder position
   */
  public void GetEncoders() {
    SmartDashboard.putNumber("Left Extender", leftExtender.getEncoder().getPosition());
    SmartDashboard.putNumber("Right Extender", -rightExtender.getEncoder().getPosition());
    SmartDashboard.putNumber("Left Angle", leftAngle.getEncoder().getPosition());
    SmartDashboard.putNumber("Right Angle", rightAngle.getEncoder().getPosition());
    SmartDashboard.putNumber("Left Baby", leftBaby.getEncoder().getPosition());
    SmartDashboard.putNumber("Right Baby", rightBaby.getEncoder().getPosition());
  }
}
