// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.subsystems.Climber;

public class RotateBabies extends CommandBase {
  private final Climber climber;
  private boolean rotatingClockwise, rotatingCounterClockwise;
  private double leftPos, rightPos;
  private final double Max = 0;
  private double leftSpeed = 0.1;
  private double rightSpeed = -0.1;

  /** Creates a new RotateBabies. */
  public RotateBabies(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftPos = climber.GetEncoders("Left Baby").getPosition();
    rightPos = -climber.GetEncoders("Right Baby").getPosition();

    SmartDashboard.putBoolean("Left Baby < max", leftPos < Max);
    SmartDashboard.putBoolean("Right Baby < max", rightPos < Max);

    // Rotate Clockwise
    if (Buttons.X_BUTTON_FIVE.get() || rotatingClockwise) {
      if (leftPos < Max && rightPos < Max) {
        climber.RotateLeftBaby(leftSpeed / 2);
        climber.RotateRightBaby(rightSpeed / 2);
      } else if (leftPos < Max) {
        climber.RotateLeftBaby(leftSpeed / 2);
      } else if (rightPos < Max) {
        climber.RotateRightBaby(rightSpeed / 2);
      } else {
        rotatingClockwise = false;
        climber.StopBabies();
      }
    }
    
    // Rotate Counter Clockwise
    else if (Buttons.X_BUTTON_SIX.get() || rotatingCounterClockwise) {
      if (leftPos > 2 && rightPos < 2) {
        climber.RotateLeftBaby(-leftSpeed / 2);
        climber.RotateRightBaby(-rightSpeed / 2);
      } else if (leftPos > 2) {
        climber.RotateLeftBaby(-leftSpeed / 2);
      } else if (rightPos > 2) {
        climber.RotateRightBaby(-rightSpeed / 2);
      } else {
        rotatingCounterClockwise = false;
        climber.StopBabies();
      }
    }
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
}
