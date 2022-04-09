// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunActiveIndex;

public class ActiveIndex extends SubsystemBase {
  private CANSparkMax activeIndex;
  public ActiveIndex(CANSparkMax activeIndex) {
    setDefaultCommand(new RunActiveIndex(this));
    this.activeIndex = activeIndex;
  }

  @Override
  public void periodic() {}

  public void runIndex() {
    activeIndex.set(-0.4);
   
 }
 public void stopIndex(){
    activeIndex.set(0);
 }
}
