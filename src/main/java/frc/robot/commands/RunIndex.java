// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIndex extends CommandBase {
  Intake subsystemL;
  
  public RunIndex(Intake subsystemP) {
    addRequirements(subsystemP);
    subsystemL = subsystemP;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (Constants.DPAD_UP.get() == true){
      subsystemL.runIndexForward();
    }
    else if (Constants.DPAD_DOWN.get() == true){
      subsystemL.runIndexBackwards();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
