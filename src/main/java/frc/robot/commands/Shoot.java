// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDController;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.EncoderValues;
import frc.robot.subsystems.Shooter;


public class Shoot extends CommandBase {
  private Shooter shooter;
  private boolean backButtonHasBeenReleased, startButtonHasBeenReleased, shoot = true;
  private boolean override = false;
  private final PIDController climbAdjustController = new PIDController(3e-3, 0, 0);
  double climbAdjustOffset = climbAdjustController.getCorrection(EncoderValues.angle - -15);
 
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
    SmartDashboard.putNumber("HOOD THING", -climbAdjustOffset);

    

    if (XboxButtons.BACK.get() && backButtonHasBeenReleased) {
      backButtonHasBeenReleased = false;
      shoot = !shoot;
    } else if (!XboxButtons.BACK.get()) {
      backButtonHasBeenReleased = true;
    }

    if (shoot) {
      shooter.moveHoodAutomatically();
    } else shooter.noShoot();

    if (Joysticks.MANIP_CONTROLLER.getRightY() < -0.9) shooter.indexBall();
    else shooter.indexBallStop();    

    if(Joysticks.DRIVE_CONTROLLER.getBackButton() && Joysticks.DRIVE_CONTROLLER.getYButton()) {
      shooter.angleUp();
    }
    else if(Joysticks.DRIVE_CONTROLLER.getBackButton() && Joysticks.DRIVE_CONTROLLER.getAButton()) {
      shooter.angleDown();
    }
    else shooter.stopAngle();

    if (Joysticks.DRIVE_CONTROLLER.getStartButton() && startButtonHasBeenReleased) {
      startButtonHasBeenReleased = false;
      override = !override;
    } else if (!XboxButtons.BACK.get()) {
      startButtonHasBeenReleased = true;
    }

    if(override) {
      return;
    }

    if(EncoderValues.leftArm > 30 || shooter.climbMode) {
      shooter.noShoot();
      shooter.RotateToClimbMode(-climbAdjustOffset);
      return;
    }
  }

    // -15 is max
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}