// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.subsystems.Climber;

public class MoveLongArms extends CommandBase {
  private final Climber climber;
  private double leftEPos, rightEPos, leftAPos, rightAPos;
  private double leftSpeed = 0.2;
  private double rightSpeed = -0.2;
  private final double ExtendMax = 20;
  private final double AngleMax = 0;
  private boolean extending, contracting, rotatingClockwise, rotatingCounterClockwise = false;

  /** Creates a new MoveLongArms. */
  public MoveLongArms(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftEPos = climber.GetEncoders("Left Encoder").getPosition();
    rightEPos = -climber.GetEncoders("Right Encoder").getPosition();
    leftAPos = climber.GetEncoders("Left Angle").getPosition();
    rightAPos = -climber.GetEncoders("Right Angle").getPosition();

    SmartDashboard.putNumber("Left Extender Position", leftEPos);
    SmartDashboard.putNumber("Right Extender Position", rightEPos);
    SmartDashboard.putNumber("Left Angle Position", leftAPos);
    SmartDashboard.putNumber("Right Angle Position", rightAPos);

    SmartDashboard.putBoolean("Left Extender < max", leftEPos < ExtendMax);
    SmartDashboard.putBoolean("Right Extender < max", rightEPos < ExtendMax);
    SmartDashboard.putBoolean("Left Angle < max", leftAPos < ExtendMax);
    SmartDashboard.putBoolean("Right Angle < max", rightAPos < ExtendMax);

    // Extend
    if(Buttons.J_BUTTON_ONE.get() || extending) {
      if (leftEPos < ExtendMax && rightEPos < ExtendMax) {
        climber.MoveLeftArm(leftSpeed);
        climber.MoveRightArm(rightSpeed);
      } else if (leftEPos < ExtendMax) {
        climber.MoveLeftArm(leftSpeed);
      } else if (rightEPos < ExtendMax) {
        climber.MoveRightArm(rightSpeed);
      } else {
        extending = false;
        climber.StopLongLongArms();
      }
    }
    
    // Contract
    else if (Buttons.J_BUTTON_TWO.get() || contracting) {
      if (leftEPos > 2 && rightEPos < 2) {
        climber.MoveLeftArm(-leftSpeed);
        climber.MoveRightArm(-rightSpeed);
      } else if (leftEPos > 2) {
        climber.MoveLeftArm(-leftSpeed);
      } else if (rightEPos > 2) {
        climber.MoveRightArm(-rightSpeed);
      } else {
        contracting = false;
        climber.StopLongLongArms();
      }
    }
    else climber.StopLongLongArms();

    // Rotate Clockwise
    if (false && Buttons.J_BUTTON_THREE.get() || rotatingClockwise) {
      if (leftAPos < AngleMax && rightAPos < AngleMax) {
        climber.RotateLeftBigArm(leftSpeed / 2);
        climber.RotateRightBigArm(rightSpeed / 2);
      } else if (leftAPos < AngleMax) {
        climber.RotateLeftBigArm(leftSpeed / 2);
      } else if (rightAPos < AngleMax) {
        climber.RotateRightBigArm(rightSpeed / 2);
      } else {
        rotatingClockwise = false;
        climber.StopLongArmRotate();
      }
    }
    
    // Rotate Counter Clockwise
    else if (false && Buttons.J_BUTTON_FOUR.get() || rotatingCounterClockwise) {
      if (leftAPos > 2 && rightAPos < 2) {
        climber.RotateLeftBigArm(-leftSpeed / 2);
        climber.RotateRightBigArm(-rightSpeed / 2);
      } else if (leftEPos > 2) {
        climber.RotateLeftBigArm(-leftSpeed / 2);
      } else if (rightEPos > 2) {
        climber.RotateRightBigArm(-rightSpeed / 2);
      } else {
        rotatingCounterClockwise = false;
        climber.StopLongArmRotate();
      }
    }
    // else climber.StopLongArmRotate();
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
}
