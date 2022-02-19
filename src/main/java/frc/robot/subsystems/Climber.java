package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.UpdateEncoders;

public class Climber extends SubsystemBase {
  private final CANSparkMax leftExtender, rightExtender, leftAngle, rightAngle, leftBaby, rightBaby;
  private final double extenderSpeed = 2.5;
  private final double rotateSpeed = 2.0;
  public boolean individualMode = false;

  public Climber(CANSparkMax leftExtender, CANSparkMax rightExtender, CANSparkMax leftArmAngle, CANSparkMax rightArmAngle, CANSparkMax leftBaby, CANSparkMax rightBaby) {
    setDefaultCommand(new UpdateEncoders(this));
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
    leftAngle.set(0.1);
    rightAngle.set(-0.1);
  }
  public void RotateArmsBackwards() {
    leftAngle.set(-0.1);
    rightAngle.set(0.1);
  }

  public void RotateLeftArm(double speed) {
    leftAngle.set(speed);
  }
  public void RotateRightArm(double speed) {
    rightAngle.set(speed);
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

  //"Autonomously" Extend/Contract Arms
  public void ExtendArmsToEncoder(double value) {
    if(EncoderValues.leftArm < value && EncoderValues.rightArm < value) ExtendArms();
    else if(EncoderValues.leftArm < value) MoveLeftArm(extenderSpeed);
    else if(EncoderValues.rightArm < value) MoveRightArm(-extenderSpeed);
    else StopLongLongArms();
  }

  public void RetractArmsToEncoder(double value) {
    if(EncoderValues.leftArm > value && EncoderValues.rightArm > value) ContractArms();
    else if(EncoderValues.leftArm > value) MoveLeftArm(-extenderSpeed);
    else if(EncoderValues.rightArm > value) MoveRightArm(extenderSpeed);
    else StopLongLongArms();
  }

  //"Autonomously" Rotate Arms
  public void RotateArmsForwardsToEncoder(double value) {
    if(EncoderValues.leftArmAngle < value && EncoderValues.rightArmAngle < value) RotateArmsForwards();
    else if(EncoderValues.leftArmAngle < value) RotateLeftArm(rotateSpeed);
    else if(EncoderValues.rightArmAngle < value) RotateRightArm(-rotateSpeed);
    else StopArmsRotate();
  }

  public void RotateArmsBackwardsToEncoder(double value) {
    if(EncoderValues.leftArmAngle > value && EncoderValues.rightArmAngle > value) RotateArmsBackwards();
    else if(EncoderValues.leftArmAngle > value) RotateLeftArm(-rotateSpeed);
    else if(EncoderValues.rightArmAngle > value) RotateRightArm(rotateSpeed);
    else StopArmsRotate();
  }

  //"Autonomously" Rotate Babies
  public void RotateBabiesForwardsToEncoder(double value) {
    if(EncoderValues.leftBaby < value && EncoderValues.rightBaby < value) RotateArmsForwards();
    else if(EncoderValues.leftBaby < value) RotateLeftBaby(rotateSpeed);
    else if(EncoderValues.rightBaby < value) RotateRightBaby(-rotateSpeed);
    else StopBabies();
  }

  public void RotateBabiesBackwardsToEncoder(double value) {
    if(EncoderValues.leftBaby > value && EncoderValues.rightBaby > value) RotateArmsBackwards();
    else if(EncoderValues.leftBaby > value) RotateLeftBaby(-rotateSpeed);
    else if(EncoderValues.rightBaby > value) RotateRightBaby(rotateSpeed);
    else StopBabies();
  }

  // Encoder Stuff
  public void ResetEncoders() {
    this.leftExtender.getEncoder().setPosition(0);
    this.rightExtender.getEncoder().setPosition(0);
    this.leftAngle.getEncoder().setPosition(0);
    this.rightAngle.getEncoder().setPosition(0);
    this.leftBaby.getEncoder().setPosition(0);
    this.rightBaby.getEncoder().setPosition(0);
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
  
  public static class EncoderValues {
    public static double leftArm, rightArm, leftArmAngle, rightArmAngle, leftBaby, rightBaby;
  }

  public void ContractArmsEncoder() {
  }
}