// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;

public class RotateBabies extends CommandBase {
  private final Climber climber;
  private final double Max = 0;
  private double leftSpeed = 0.1;
  private double rightSpeed = -0.1;
  private final double button;
  private boolean usingEncoders;

  /** Creates a new RotateBabies. */
  public RotateBabies(Climber climber, double button, boolean usingEncoders) {
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
    
    SmartDashboard.putBoolean("Left Baby < max", EncoderValues.leftBaby < Max);
    SmartDashboard.putBoolean("Right Baby < max", EncoderValues.rightBaby < Max);

    // Rotate Clockwise
    if (button == 5) RotateForwards();
    else if (button == 6) RotateBackwards();
    else climber.StopBabies();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.StopBabies();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void RotateForwards() {
    if (EncoderValues.leftBaby < Max && EncoderValues.rightBaby < Max) {
      climber.RotateLeftBaby(leftSpeed / 2);
      climber.RotateRightBaby(rightSpeed / 2);
    } else if (EncoderValues.leftBaby < Max) {
      climber.RotateLeftBaby(leftSpeed / 2);
    } else if (EncoderValues.rightBaby < Max) {
      climber.RotateRightBaby(rightSpeed / 2);
    } else {
      climber.StopBabies();
    }
  }

  private void RotateBackwards() {
    if (EncoderValues.leftBaby > 2 && EncoderValues.rightBaby > 2) {
      climber.RotateLeftBaby(-leftSpeed / 2);
      climber.RotateRightBaby(-rightSpeed / 2);
    } else if (EncoderValues.leftBaby > 2) {
      climber.RotateLeftBaby(-leftSpeed / 2);
    } else if (EncoderValues.rightBaby > 2) {
      climber.RotateRightBaby(-rightSpeed / 2);
    } else {
      climber.StopBabies();
    }
  }
}