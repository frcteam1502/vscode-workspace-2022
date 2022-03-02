// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Drivetrain;

public class DriveByJoysticks extends CommandBase {
  private final Drivetrain drivetrain;
  /** Creates a new DriveByJoystick. */
  public DriveByJoysticks(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.move(Joysticks.CONTROLLER2.getLeftX(), -Joysticks.CONTROLLER2.getLeftY(), Joysticks.CONTROLLER2.getRightX());
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
