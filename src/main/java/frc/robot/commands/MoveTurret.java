// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.Turret;
import frc.robot.PIDController;
import frc.robot.Constants.Joysticks;
//import frc.robot.subsystems.AngleFlap;
import frc.robot.Constants.XboxButtons;


public class MoveTurret extends CommandBase {
  private boolean on = false;
  private final Turret turret;
  private boolean hasBeenReleased = true;
 // private final AngleFlap angleFlap;

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

  private final PIDController rotationController = new PIDController(5e-3, 0, 0);
  private static final double SPEED = 0.1;
  protected double getVelocity() {
    return -SPEED;
  }
  private boolean manual = true;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {  
    Limelight.Target m_limelight = Limelight.getTarget();

    double error = m_limelight.tx;
    double offset = rotationController.getCorrection(error);

    if(XboxButtons.START.get() && hasBeenReleased) {
      on = !on;
      hasBeenReleased = false;
    } else if(!XboxButtons.START.get()) {
      hasBeenReleased = true;
    }

    SmartDashboard.putBoolean("on", on);
    SmartDashboard.putBoolean("Has Been Released", hasBeenReleased);
    if(Joysticks.MANIP_CONTROLLER.getRightTriggerAxis() > 0.1){
      turret.turretRight(Joysticks.MANIP_CONTROLLER.getRightTriggerAxis());
    }
    else if(Joysticks.MANIP_CONTROLLER.getLeftTriggerAxis() > 0.1){
      turret.turretLeft(Joysticks.MANIP_CONTROLLER.getLeftTriggerAxis());
    }
    else if(on) {
      turret.turnTurret(getVelocity() - offset);
    }
    else{
      runManually();
    }
    //angleFlap.Moveflap();
  }

  private void runManually() {
    if(Joysticks.MANIP_CONTROLLER.getRightTriggerAxis() > 0.1){
      turret.turretRight(Joysticks.MANIP_CONTROLLER.getRightTriggerAxis());
    }
    else if(Joysticks.MANIP_CONTROLLER.getLeftTriggerAxis() > 0.1){
      turret.turretLeft(Joysticks.MANIP_CONTROLLER.getLeftTriggerAxis());
    }
    else{
      turret.turretStop();
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
