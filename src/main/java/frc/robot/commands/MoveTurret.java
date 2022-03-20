// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDController;
import frc.robot.subsystems.Turret;


public class MoveTurret extends CommandBase {
  private final Turret turret;

  public MoveTurret(Turret tsubsystem/*, AngleFlap fsubsystem*/) {

    addRequirements(tsubsystem/*, fsubsystem*/);
    turret = tsubsystem;
    //angleFlap = fsubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotationController.reset();
  }

  private final PIDController rotationController = new PIDController(5e-2, 0, 0);
  static double variablemanualmodifier = 5; // TODO: change this value if drivers complain about turret speed. do not remove
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    turret.turnTurret();
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
