// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;

public class RotateBabies extends CommandBase {
  private final Climber climber;
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
    
    SmartDashboard.putBoolean("Left Baby < max", EncoderValues.leftBaby < EncoderMaxes.BABY_MAX);
    SmartDashboard.putBoolean("Right Baby < max", EncoderValues.rightBaby < EncoderMaxes.BABY_MAX);

    // Rotate Clockwise
    if (button == 5) climber.RotateBabiesForwardsToEncoder(EncoderMaxes.BABY_MAX);
    else if (button == 6) climber.RotateBabiesBackwardsToEncoder(0);
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