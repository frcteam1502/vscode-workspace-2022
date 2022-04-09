package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Encoders;
import frc.robot.subsystems.EncoderValues;

public class UpdateEncoders extends CommandBase {
  private EncoderValues encoderValues;
  public UpdateEncoders(EncoderValues encoderValues) {
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
    EncoderValues.babies = 0;
    EncoderValues.angle = 0;
    EncoderValues.turret = 0;
  }

  @Override
  public void execute() {
    EncoderValues.leftArm = -Encoders.LeftExtenderEncoder.Encoder.getPosition();
    EncoderValues.rightArm = Encoders.RightExtenderEncoder.Encoder.getPosition();
    EncoderValues.leftArmAngle = -Encoders.LeftArmAngleEncoder.Encoder.getPosition();
    EncoderValues.rightArmAngle = Encoders.RightArmAngleEncoder.Encoder.getPosition();
    EncoderValues.babies = Encoders.BabiesEncoder.Encoder.getPosition();
    EncoderValues.turret = Encoders.TurretEncoder.Encoder.getPosition();
    
    EncoderValues.angle = Encoders.AngleEncoder.Encoder.getPosition();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
