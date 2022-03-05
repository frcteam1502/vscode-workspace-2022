// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.Shooter;


public class Shoot extends CommandBase {
  private Shooter shooter;
  private boolean shoot = false;
  private boolean backButtonHasBeenReleased = true;
 
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
      shooter.shoot();
    } else shooter.noShoot();

    if (Joysticks.CONTROLLER.getRightY() < -0.9) shooter.indexBall();
    else shooter.indexBallStop();

    /*
    if (XboxButtons.BACK.get() == true){
      shooter.shoot();
    } else {
      shooter.noShoot();
    }

    if (Joysticks.CONTROLLER2.getRightBumper()){
      shooter.indexBall();
    } else{
      shooter.indexBallStop();
    }
    */
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