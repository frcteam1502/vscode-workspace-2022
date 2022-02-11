// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  Intake subsystemL;
  public RunIntake(Intake subsystemP) {
    addRequirements(subsystemP);
    subsystemL = subsystemP;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Constants.CONTROLLER.getAButton() == true) {
      subsystemL.runIntakeForward();
    }
    else if(Constants.CONTROLLER.getBButton() == true) {
      subsystemL.runIntakeBackward();
    }
    else{
      subsystemL.stopIntake();
    }
}

  @Override
  public void end(boolean interrupted) {
    subsystemL.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
