// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  /** Creates a new Climber. */
  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftAngle, CANSparkMax rightAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
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

  // These two/three methods could be made into one I dont know what is better
  public void ExtendLongLongArms() {
    leftExtender.set(0.2);
    rightExtender.set(0.2);
  }
  public void ContractLongLongArms() {
    leftExtender.set(-0.2);
    rightExtender.set(-0.2);
  }
  public void StopLongLongArms() {
    leftExtender.set(0);
    rightExtender.set(0);
  }

  // These two methods could be made into two methods each like the ones above I dont know what is better
  // They could also be combined into one method but I am pretty sure that would not be better
  public void RotateBigArms(double speed) {
    leftAngle.set(speed);
    rightAngle.set(speed);
  }
  public void RotateBabies(double speed) {
    leftBaby.set(speed);
    rightBaby.set(speed);
  }

  /*
  public double GetEncoder(String motor) {
    switch(motor) {
      case "leftExtender":
        return leftExtender.getEncoder().getPosition();
      case "rightExtender":
        return rightExtender.getEncoder().getPosition();
      case "leftAngle":
        return leftAngle.getEncoder().getPosition();
      case "rightAngle":
        return rightAngle.getEncoder().getPosition();
      case "leftBaby":
        return leftBaby.getEncoder().getPosition();
      case "rightBaby":
        return rightBaby.getEncoder().getPosition();
      default:
        return 0;
    }
  }
  */
}
