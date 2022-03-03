






// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Encoders;
import frc.robot.Constants.Motors;
import frc.robot.commands.UpdateEncoders;

public class EncoderValues extends SubsystemBase {
  private UpdateEncoders updateEncoders;

  /** Creates a new EncoderValues. */
  public EncoderValues(UpdateEncoders updateEncoders) {
    this.updateEncoders = updateEncoders;
    setDefaultCommand(updateEncoders);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

    public static double leftArm, rightArm, leftArmAngle, rightArmAngle, leftBaby, rightBaby, flap = 0;



    public void ResetEncoders() {
      Motors.LEFT_ARM_EXTENDER.getEncoder().setPosition(0);
      Motors.RIGHT_ARM_EXTENDER.getEncoder().setPosition(0);
      Motors.LEFT_ARM_ANGLE.getEncoder().setPosition(0);
      Motors.RIGHT_ARM_ANGLE.getEncoder().setPosition(0);
      Motors.LEFT_BABY.getEncoder().setPosition(0);
      Motors.RIGHT_BABY.getEncoder().setPosition(0);
      Motors.FLAP.getEncoder().setPosition(0);
    }


}
