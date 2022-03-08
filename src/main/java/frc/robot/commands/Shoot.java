// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.Shooter;


public class Shoot extends CommandBase {
  private Shooter shooter;
  private boolean autoHood = true;
 
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
    if (XboxButtons.BACK.get() == true){
      shooter.shoot(0.8);
    } else {
      shooter.noShoot();
    }

    if (Joysticks.CONTROLLER2.getRightBumper()){
      shooter.indexBall();
    }else{
      shooter.indexBallStop();
    }
shooter.moveHoodAutomatically();
    if (autoHood) {
      
    } else {
        if(Joysticks.CONTROLLER.getYButton()) {
        shooter.angleUp();
      } else if (Joysticks.CONTROLLER.getAButton()){
        shooter.angleDown();
      } else {
        shooter.stopAngle();
      }
    }
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
