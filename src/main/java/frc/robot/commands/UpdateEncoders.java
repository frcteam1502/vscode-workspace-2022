// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Encoders;
import frc.robot.Constants.Motors;
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
    // climber.ResetEncoders();
    EncoderValues.leftArm = 0;
    EncoderValues.rightArm = 0;
    EncoderValues.leftArmAngle = 0;
    EncoderValues.rightArmAngle = 0;
    EncoderValues.leftBaby = 0;
    EncoderValues.rightBaby = 0;
    
    EncoderValues.angle = 0;
  }

  @Override
  public void execute() {
    EncoderValues.leftArm = -Encoders.LeftExtenderEncoder.Encoder.getPosition();
    EncoderValues.rightArm = Encoders.RightExtenderEncoder.Encoder.getPosition();
    EncoderValues.leftArmAngle = -Encoders.LeftArmAngleEncoder.Encoder.getPosition();
    EncoderValues.rightArmAngle = Encoders.RightArmAngleEncoder.Encoder.getPosition();
    EncoderValues.leftBaby = Encoders.LeftBabyEncoder.Encoder.getPosition();
    EncoderValues.rightBaby = Encoders.RightBabyEncoder.Encoder.getPosition();

    SmartDashboard.putNumber("Encoders.LeftExtenderEncoder.Encoder.getPosition", -Encoders.LeftExtenderEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightExtenderEncoder.Encoder.getPosition", Encoders.RightExtenderEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.LeftArmAngleEncoder.Encoder.getPosition", -Encoders.LeftArmAngleEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightArmAngleEncoder.Encoder.getPosition", Encoders.RightArmAngleEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.LeftBabyEncoder.Encoder.getPosition", Encoders.LeftBabyEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightBabyEncoder.Encoder.getPosition", Encoders.RightBabyEncoder.Encoder.getPosition());
    
    EncoderValues.angle = Encoders.AngleEncoder.Encoder.getPosition();

    
    SmartDashboard.putNumber("Encoders.AngleEncoder.Encoder.getPosition", Encoders.AngleEncoder.Encoder.getPosition());

    
    SmartDashboard.putNumber("EncoderValues.angle", EncoderValues.angle);

    SmartDashboard.putNumber("Left Shoot v", Motors.SHOOTER_LEFT.getEncoder().getVelocity());
    SmartDashboard.putNumber("Right Shoot v", Motors.SHOOTER_RIGHT.getEncoder().getVelocity());
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
