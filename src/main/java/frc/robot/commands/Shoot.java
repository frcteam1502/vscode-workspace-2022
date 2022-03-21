// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.Shooter;


public class Shoot extends CommandBase {
  private Shooter shooter;
  private boolean backButtonHasBeenReleased, shoot = true;
 
  public Shoot(Shooter subsystem) {
    addRequirements(subsystem);
    shooter = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (XboxButtons.BACK.get() && backButtonHasBeenReleased) {
      backButtonHasBeenReleased = false;
      shoot = !shoot;
    } else if (!XboxButtons.BACK.get()) {
      backButtonHasBeenReleased = true;
    }

    if (shoot) {
      shooter.shoot(0.8);
    } else shooter.noShoot();

    if (Joysticks.MANIP_CONTROLLER.getRightY() < -0.9) shooter.indexBall();
    else shooter.indexBallStop();

shooter.moveHoodAutomatically();

    
    
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