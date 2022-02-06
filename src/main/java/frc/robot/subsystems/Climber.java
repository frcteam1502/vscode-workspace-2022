// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.GetEncoderValues;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  
  // TODO: Find and save max ecoder positions
  private final double extenderMax = 0;
  private final double armRotateMax = 0;
  private final double babyMax = 0;

  /** Creates a new Climber. */
  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftAngle, CANSparkMax rightAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    setDefaultCommand(new GetEncoderValues(this));
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.leftAngle = leftAngle;
    this.rightAngle = rightAngle;
    this.leftBaby = leftBaby;
    this.rightBaby = rightBaby;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void StopLongLongArms() {
    leftExtender.set(0);
    rightExtender.set(0);
  }

  // Manually
  public void ExtendLongLongArmsManual() {
    leftExtender.set(0.2);
    rightExtender.set(0.2);
  }
  public void ContractLongLongArmsManual() {
    leftExtender.set(-0.2);
    rightExtender.set(-0.2);
  }

  public void RotateBigArmsManual(double speed) {
    leftAngle.set(speed);
    rightAngle.set(speed);
  }
  public void RotateBabiesManual(double speed) {
    leftBaby.set(speed);
    rightBaby.set(speed);
  }

  // Using encoders
  public void ExtendLongLongArmsEncoder() {
    leftExtender.getEncoder().setPosition(extenderMax);
    rightExtender.getEncoder().setPosition(extenderMax);
  }
  public void ContractLongLongArmsEncoder() {
    leftExtender.getEncoder().setPosition(0);
    rightExtender.getEncoder().setPosition(0);
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
    SmartDashboard.putNumber("Right Extender", rightExtender.getEncoder().getPosition());
    SmartDashboard.putNumber("Left Angle", leftAngle.getEncoder().getPosition());
    SmartDashboard.putNumber("Right Angle", rightAngle.getEncoder().getPosition());
    SmartDashboard.putNumber("Left Baby", leftBaby.getEncoder().getPosition());
    SmartDashboard.putNumber("Right Baby", rightBaby.getEncoder().getPosition());
  }
}
