// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  Intake intake;
  
  public RunIntake(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Joysticks.CONTROLLER.getAButton()) intake.runIntakeForward();
    else if(Joysticks.CONTROLLER.getBButton()) intake.runIntakeBackward();
    else intake.stopIntake();
}

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
