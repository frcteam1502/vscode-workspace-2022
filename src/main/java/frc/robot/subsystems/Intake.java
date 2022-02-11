// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunIntake;

public class Intake extends SubsystemBase {
  private CANSparkMax intakeL;
  private double ySpeedD;
  /** Creates a new Intake. */
  public Intake(CANSparkMax intakeP) {
    setDefaultCommand(new RunIntake(this));
    intakeL = intakeP;
    // Joysticks.RIGHT_JOYSTICK.getY();
  }

  public void runIntakeForward(double ySpeed){
    ySpeedD = ySpeed/5;
    ySpeed = MathUtil.clamp(ySpeedD, -1.0, 1.0);
    
    intakeL.set(ySpeedD);
  }

  public void runIntakeBackward(double ySpeed){
    ySpeedD = ySpeed/-5;
    ySpeed = MathUtil.clamp(ySpeedD, -1.0, 1.0);

    intakeL.set(ySpeedD);
  }

  public void stopIntake(){
    intakeL.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
