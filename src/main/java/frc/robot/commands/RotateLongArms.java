// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Climber;

public class RotateLongArms extends CommandBase {
  private final Climber subsystem;
  /** Creates a new RotateLongArms. */
  public RotateLongArms(Climber subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Joysticks.rightJoystick.getRawButton(5) && !Joysticks.rightJoystick.getRawButton(6)) subsystem.RotateBigArms(0.1);
    else if(Joysticks.rightJoystick.getRawButton(6) && !Joysticks.rightJoystick.getRawButton(5)) subsystem.RotateBigArms(-0.1);
    else subsystem.RotateBigArms(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.RotateBigArms(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
