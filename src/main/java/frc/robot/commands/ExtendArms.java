// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Climber;

public class ExtendArms extends CommandBase {
  private Climber subsystem;
  /** Creates a new Climb. */
  public ExtendArms(Climber subsystem) {
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
    if(Joysticks.rightJoystick.getRawButtonPressed(1) && !Joysticks.rightJoystick.getRawButtonPressed(2)) subsystem.ExtendLongLongArms();
    else subsystem.StopLongLongArms();
    SmartDashboard.putNumber("Left Extender Encoder", subsystem.GetEncoder("leftExtender"));
    SmartDashboard.putNumber("Right Extender Encoder", subsystem.GetEncoder("rightExtender"));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.StopLongLongArms();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
