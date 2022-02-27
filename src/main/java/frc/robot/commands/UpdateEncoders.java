// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Encoders;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;
import frc.robot.subsystems.AngleFlap;
import frc.robot.subsystems.AngleFlap.fEncoderValues;

public class UpdateEncoders extends CommandBase {
  private Climber climber;
  private AngleFlap flap;
  // Creates a new UpdateEncoders. 
  public UpdateEncoders(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
  }
  public UpdateEncoders(AngleFlap flap) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(flap);
    this.flap = flap;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.ResetEncoders();
    flap.ResetEncoders();
    EncoderValues.leftArm = 0;
    EncoderValues.rightArm = 0;
    EncoderValues.leftArmAngle = 0;
    EncoderValues.rightArmAngle = 0;
    EncoderValues.leftBaby = 0;
    EncoderValues.rightBaby = 0;
    fEncoderValues.angle = 0;
  }

  @Override
  public void execute() {
    EncoderValues.leftArm = Encoders.LeftExtenderEncoder.Encoder.getPosition();
    EncoderValues.rightArm = Encoders.RightExtenderEncoder.Encoder.getPosition();
    EncoderValues.leftArmAngle = Encoders.LeftArmAngleEncoder.Encoder.getPosition();
    EncoderValues.rightArmAngle = Encoders.RightArmAngleEncoder.Encoder.getPosition();
    EncoderValues.leftBaby = Encoders.LeftBabyEncoder.Encoder.getPosition();
    EncoderValues.rightBaby = Encoders.RightBabyEncoder.Encoder.getPosition();
    fEncoderValues.angle = Encoders.AngleEncoder.Encoder.getPosition();

    SmartDashboard.putNumber("Encoders.LeftExtenderEncoder.Encoder.getPosition", Encoders.LeftExtenderEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightExtenderEncoder.Encoder.getPosition", Encoders.RightExtenderEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.LeftArmAngleEncoder.Encoder.getPosition", Encoders.LeftArmAngleEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightArmAngleEncoder.Encoder.getPosition", Encoders.RightArmAngleEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.LeftBabyEncoder.Encoder.getPosition", Encoders.LeftBabyEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("Encoders.RightBabyEncoder.Encoder.getPosition", Encoders.RightBabyEncoder.Encoder.getPosition());
    SmartDashboard.putNumber("fEncoders.Angle.Encoder.getPosition", Encoders.AngleEncoder.Encoder.getPosition());

    SmartDashboard.putNumber("EncoderValues.leftArm", EncoderValues.leftArm);
    SmartDashboard.putNumber("EncoderValues.rightArm", EncoderValues.rightArm);
    SmartDashboard.putNumber("EncoderValues.leftArmAngle", EncoderValues.leftArmAngle);
    SmartDashboard.putNumber("EncoderValues.rightArmAngle", EncoderValues.rightArmAngle);
    SmartDashboard.putNumber("EncoderValues.leftBaby", EncoderValues.leftBaby);
    SmartDashboard.putNumber("EncoderValues.rightBaby", EncoderValues.rightBaby);
    SmartDashboard.putNumber("fEncoderValues.angle", fEncoderValues.angle);
    
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

