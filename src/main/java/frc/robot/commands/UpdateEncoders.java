// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Encoders;

import frc.robot.subsystems.EncoderValues;

public class UpdateEncoders extends CommandBase {
  private EncoderValues encoderValues;
  /** Creates a new UpdateEncoders. */
  public UpdateEncoders(EncoderValues encoderValues) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(encoderValues);
    this.encoderValues = encoderValues;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    EncoderValues.angle = 0;
  }

  @Override
  public void execute() {
    
    EncoderValues.angle = Encoders.AngleEncoder.Encoder.getPosition();

    
    SmartDashboard.putNumber("Encoders.AngleEncoder.Encoder.getPosition", Encoders.AngleEncoder.Encoder.getPosition());

    
    SmartDashboard.putNumber("EncoderValues.angle", EncoderValues.angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
