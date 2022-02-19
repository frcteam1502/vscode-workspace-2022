// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;

public class MoveLongArms extends CommandBase {
  private final Climber climber;
  private double button;
  private boolean usingEncoders;

  public MoveLongArms(Climber climber, double button, boolean usingEncoders) {
    this.climber = climber;
    this.button = button;
    this.usingEncoders = usingEncoders;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (!usingEncoders) return;

    SmartDashboard.putBoolean("Left Extender < max", EncoderValues.leftArm < EncoderMaxes.EXTEND_MAX);
    SmartDashboard.putBoolean("Right Extender < max", EncoderValues.rightArm < EncoderMaxes.EXTEND_MAX);
    SmartDashboard.putBoolean("Left Angle < max", EncoderValues.leftArmAngle < EncoderMaxes.ANGLE_MAX);
    SmartDashboard.putBoolean("Right Angle < max", EncoderValues.rightArmAngle < EncoderMaxes.ANGLE_MAX);

    if(button == 1) climber.ExtendArmsToEncoder(EncoderMaxes.EXTEND_MAX);
    else if (button == 2) climber.RetractArmsToEncoder(0);
    else climber.StopLongLongArms();

    if (button == 3) climber.RotateArmsForwardsToEncoder(EncoderMaxes.ANGLE_MAX);
    else if (button == 4) climber.RotateBabiesBackwardsToEncoder(0);
    else button = 0;
  }

  @Override
  public void end(boolean interrupted) {
    climber.StopLongLongArms();
    climber.StopArmsRotate();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
