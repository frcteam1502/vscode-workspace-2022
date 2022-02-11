// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  Intake subsystemL;
  /** Creates a new RunIntake. */
  public RunIntake(Intake subsystemP) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystemP);
    subsystemL = subsystemP;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Constants.CONTROLLER.getAButton() == true) {
      if (Constants.RIGHT_JOYSTICK.getY() < 0.3 && Constants.RIGHT_JOYSTICK.getY() > 0) {
        subsystemL.runIntakeForward(0.3);
      }
      else {
        subsystemL.runIntakeForward(Constants.RIGHT_JOYSTICK.getY());
      }
      
    }
    else if(Constants.CONTROLLER.getBButton() == true) {
      if (Constants.RIGHT_JOYSTICK.getY() > -0.3 && Constants.RIGHT_JOYSTICK.getY() < 0) {
        subsystemL.runIntakeBackward(-0.3);}
      else{
        subsystemL.runIntakeBackward(Constants.RIGHT_JOYSTICK.getY());
      }
    }
    else{
      subsystemL.stopIntake();
    }
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystemL.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
