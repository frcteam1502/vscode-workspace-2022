// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;

public class MoveLongArms extends CommandBase {
  private final Climber climber;
  private double leftSpeed = 0.5;
  private double rightSpeed = -0.5;
  private double button;
  private boolean usingEncoders;

  public MoveLongArms(Climber climber, double button, boolean usingEncoders) {
    this.climber = climber;
    this.button = button;
    this.usingEncoders = usingEncoders;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(button == 1) ExtendArmsEncoders();
    else if (button == 2) ContractArmsEncoder();
    else climber.StopLongLongArms();

    if (button == 3) RotateForwards();
    else if (button == 4) RotateBackwards();
    else button = 0;
  }

  @Override
  public void end(boolean interrupted) {
    climber.StopLongLongArms();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private void ExtendArmsEncoders() {
    if (EncoderValues.leftArm < EncoderMaxes.LEFT_MAX && EncoderValues.rightArm < EncoderMaxes.RIGHT_MAX) {
      climber.MoveLeftArm(leftSpeed);
      climber.MoveRightArm(rightSpeed);
    } else if (EncoderValues.leftArm < EncoderMaxes.LEFT_MAX) {
      climber.MoveLeftArm(leftSpeed);
    } else if (EncoderValues.rightArm < EncoderMaxes.RIGHT_MAX) {
      climber.MoveRightArm(rightSpeed);
    } else {
      climber.StopLongLongArms();
    }
  }

  private void ContractArmsEncoder() {
    if (EncoderValues.leftArm > 0 && EncoderValues.rightArm < 2) {
      climber.MoveLeftArm(-leftSpeed);
      climber.MoveRightArm(-rightSpeed);
    } else if (EncoderValues.leftArm > 0) {
      climber.MoveLeftArm(-leftSpeed);
    } else if (EncoderValues.rightArm > 0) {
      climber.MoveRightArm(-rightSpeed);
    } else {
      climber.StopLongLongArms();
    }
  }

  private void RotateForwards() {
    if (EncoderValues.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX && EncoderValues.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      climber.RotateLeftArm(leftSpeed / 2);
      climber.RotateRightArm(rightSpeed / 2);
    } else if (EncoderValues.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX) {
      climber.RotateLeftArm(leftSpeed / 2);
    } else if (EncoderValues.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      climber.RotateRightArm(rightSpeed / 2);
    } else {
      climber.StopArmsRotate();
    }
  }

  private void RotateBackwards() {
    if (EncoderValues.leftArmAngle > 0 && EncoderValues.rightArmAngle < 0) {
      climber.RotateLeftArm(-leftSpeed / 2);
      climber.RotateRightArm(-rightSpeed / 2);
    } else if (EncoderValues.leftArmAngle > 0) {
      climber.RotateLeftArm(-leftSpeed / 2);
    } else if (EncoderValues.rightArmAngle > 0) {
      climber.RotateRightArm(-rightSpeed / 2);
    } else {
      climber.StopArmsRotate();
    }
  }
}
