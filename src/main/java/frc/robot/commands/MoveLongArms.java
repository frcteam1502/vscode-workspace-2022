// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Buttons;
import frc.robot.subsystems.Climber;

public class MoveLongArms extends CommandBase {
  private final CANSparkMax leftExtender, rightExtender;
  private final Climber climber;
  private double leftPos;
  private double rightPos;
  private double leftSpeed = 0.2;
  private double rightSpeed = -0.2;
  private final double EncoderMax = 20;

  /** Creates a new MoveLongArms. */
  public MoveLongArms(Climber climber, CANSparkMax leftExtender, CANSparkMax rightExtender) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.leftExtender = leftExtender;
    this.rightExtender = rightExtender;
    this.climber = climber;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftPos = leftExtender.getEncoder().getPosition();
    rightPos = rightExtender.getEncoder().getPosition();

    SmartDashboard.putNumber("Left Extender Position", leftPos);
    SmartDashboard.putNumber("Right Extender Position", rightPos);
    SmartDashboard.putBoolean("Left < max", leftPos < EncoderMax);
    SmartDashboard.putBoolean("Right < max", rightPos < EncoderMax);

    // Extend
    if(Buttons.J_BUTTON_ONE.get()) {
      if (leftPos < EncoderMax && rightPos < EncoderMax) {
        leftExtender.set(leftSpeed);
        rightExtender.set(rightSpeed);
      } else if (leftPos < EncoderMax) {
        leftExtender.set(leftSpeed);
      } else if (rightPos < EncoderMax) {
        rightExtender.set(rightSpeed);
      } else {
        leftExtender.set(0);
        rightExtender.set(0);
      }
    }

    // Contract
    else if (Buttons.J_BUTTON_TWO.get()) {
      if (leftPos > 2 && rightPos < 2) {
        leftExtender.set(-leftSpeed);
        rightExtender.set(-rightSpeed);
      } else if (leftPos > 2) {
        leftExtender.set(-leftSpeed);
      } else if (rightPos > 2) {
        rightExtender.set(-rightSpeed);
      } else {
        leftExtender.set(0);
        rightExtender.set(0);
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
