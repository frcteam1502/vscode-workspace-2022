// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunIntake;

public class Intake extends SubsystemBase {
  private CANSparkMax intake;
  /** Creates a new Intake. */
  public Intake(CANSparkMax intake) {
    this.intake = intake;
    setDefaultCommand(new RunIntake(this) );
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void moveIntake(){ 
    intake.set(0.6);
  }
  public void stopIntake(){
    intake.set(0);
  }
}