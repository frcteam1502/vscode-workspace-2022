// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveByJoysticks extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain drivetrain;
  private double xSpeed;
  private double ySpeed;
  private double zRotation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveByJoysticks(Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    this.drivetrain = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.move(
      // (Constants.Joysticks.XBOX_CONTROLLER.getLeftX() / 2),
      // (Constants.Joysticks.XBOX_CONTROLLER.getLeftY() * -1) / 2,
      // (Constants.Joysticks.XBOX_CONTROLLER.getRightX()) / 4);
      Constants.Joysticks.LEFT_JOYSTICKS.getX(),
      Constants.Joysticks.LEFT_JOYSTICKS.getY() * -1,
      Constants.Joysticks.RIGHT_JOYSTICK.getX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
