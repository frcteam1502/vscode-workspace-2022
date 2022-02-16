// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class GetEncoders extends CommandBase {
  private Climber climber;
  /** Creates a new GetEncoders. */
  public GetEncoders(Climber climber) {
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
    SmartDashboard.putNumber("Left Extender", climber.GetEncoders("Left Extender").getPosition());
    SmartDashboard.putNumber("Rightn Extender", climber.GetEncoders("Right Extender").getPosition());
    SmartDashboard.putNumber("Left Arm Angle", climber.GetEncoders("Left Arm Angle").getPosition());
    SmartDashboard.putNumber("Right Arm Angle", climber.GetEncoders("Right Arm Angle").getPosition());
    SmartDashboard.putNumber("Left Baby", climber.GetEncoders("Left Baby").getPosition());
    SmartDashboard.putNumber("Right Baby", climber.GetEncoders("Right Baby").getPosition());

    // Left Baby
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
