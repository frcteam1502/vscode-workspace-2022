// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class UpdateEncoders extends CommandBase {
  private Climber climber;
  /** Creates a new UpdateEncoders. */
  public UpdateEncoders(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.ResetEncoders();
    climber.encoders.leftArm = 0;
    climber.encoders.rightArm = 0;
    climber.encoders.leftArmAngle = 0;
    climber.encoders.rightArmAngle = 0;
    climber.encoders.leftBaby = 0;
    climber.encoders.rightBaby = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.encoders.leftArm = climber.GetEncoders("Left Extender").getPosition();
    climber.encoders.rightArm = -climber.GetEncoders("Right Extender").getPosition();
    climber.encoders.leftArmAngle = climber.GetEncoders("Left Arm Angle").getPosition();
    climber.encoders.rightArmAngle = -climber.GetEncoders("Right Arm Angle").getPosition();
    climber.encoders.leftBaby = climber.GetEncoders("Left Baby").getPosition();
    climber.encoders.rightBaby = -climber.GetEncoders("Right Baby").getPosition();
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
