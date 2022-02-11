// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoSimple extends CommandBase {
  Drivetrain drivetrain;
  Intake intake;

  public AutoSimple(Drivetrain drivetrain, Intake intake) {
    addRequirements(drivetrain);
    addRequirements(intake);
    this.drivetrain = drivetrain;
    this.intake = intake;
  }

  @Override
  public void initialize() {
    intake.runIntakeForward();
    drivetrain.move(0, .5, 0);
    new WaitCommand(1);
    drivetrain.move(0, 0, 0);
    intake.stopIntake();
    //shoot
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
