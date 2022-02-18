// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.subsystems.Climber;

public class MoveLongArms extends CommandBase {
  private final Climber climber;
  private double leftSpeed = 0.5;
  private double rightSpeed = -0.5;
  private double button;
  private boolean usingEncoders;

  /** Creates a new MoveLongArms. */
  public MoveLongArms(Climber climber, double button, boolean usingEncoders) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    this.button = button;
    this.usingEncoders = usingEncoders;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!usingEncoders) return;

    SmartDashboard.putBoolean("Left Extender < max", climber.encoders.leftArm < EncoderMaxes.LEFT_MAX);
    SmartDashboard.putBoolean("Right Extender < max", climber.encoders.rightArm < EncoderMaxes.RIGHT_MAX);
    SmartDashboard.putBoolean("Left Angle < max", climber.encoders.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX);
    SmartDashboard.putBoolean("Right Angle < max", climber.encoders.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX);

    if(button == 1) ExtendArmsEncoders();
    else if (button == 2) ContractArmsEncoder();
    else climber.StopLongLongArms();

    if (button == 3) RotateForwards();
    else if (button == 4) RotateBackwards();
    else button = 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.StopLongLongArms();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void ExtendArmsEncoders() {
    if (climber.encoders.leftArm < EncoderMaxes.LEFT_MAX && climber.encoders.rightArm < EncoderMaxes.RIGHT_MAX) {
      climber.MoveLeftArm(leftSpeed);
      climber.MoveRightArm(rightSpeed);
    } else if (climber.encoders.leftArm < EncoderMaxes.LEFT_MAX) {
      climber.MoveLeftArm(leftSpeed);
    } else if (climber.encoders.rightArm < EncoderMaxes.RIGHT_MAX) {
      climber.MoveRightArm(rightSpeed);
    } else {
      climber.StopLongLongArms();
    }
  }

  private void ContractArmsEncoder() {
    if (climber.encoders.leftArm > 0 && climber.encoders.rightArm < 2) {
      climber.MoveLeftArm(-leftSpeed);
      climber.MoveRightArm(-rightSpeed);
    } else if (climber.encoders.leftArm > 0) {
      climber.MoveLeftArm(-leftSpeed);
    } else if (climber.encoders.rightArm > 0) {
      climber.MoveRightArm(-rightSpeed);
    } else {
      climber.StopLongLongArms();
    }
  }

  private void RotateForwards() {
    if (climber.encoders.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX && climber.encoders.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      climber.RotateLeftArm(leftSpeed / 2);
      climber.RotateRightArm(rightSpeed / 2);
    } else if (climber.encoders.leftArmAngle < EncoderMaxes.LEFT_ANGLE_MAX) {
      climber.RotateLeftArm(leftSpeed / 2);
    } else if (climber.encoders.rightArmAngle < EncoderMaxes.RIGHT_ANGLE_MAX) {
      climber.RotateRightArm(rightSpeed / 2);
    } else {
      climber.StopArmsRotate();
    }
  }

  private void RotateBackwards() {
    if (climber.encoders.leftArmAngle > 0 && climber.encoders.rightArmAngle < 0) {
      climber.RotateLeftArm(-leftSpeed / 2);
      climber.RotateRightArm(-rightSpeed / 2);
    } else if (climber.encoders.leftArmAngle > 0) {
      climber.RotateLeftArm(-leftSpeed / 2);
    } else if (climber.encoders.rightArmAngle > 0) {
      climber.RotateRightArm(-rightSpeed / 2);
    } else {
      climber.StopArmsRotate();
    }
  }
}
