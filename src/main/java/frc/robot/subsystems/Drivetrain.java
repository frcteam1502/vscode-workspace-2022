// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax frontLeft, frontRight, backLeft, backRight;

  /** Creates a new Drivetrain. */
  public Drivetrain(CANSparkMax driveFrontLeft, CANSparkMax driveFrontRight, CANSparkMax driveBackLeft, CANSparkMax driveBackRight ) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = driveFrontLeft;
    this.frontRight = driveFrontRight;
    this.backLeft = driveBackLeft;
    this.backRight = driveBackRight;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double ySpeed, double xSpeed, double zRotation) {
    ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);

    frontLeft.set(xSpeed + ySpeed + zRotation);
    frontRight.set(xSpeed - ySpeed - zRotation);
    backLeft.set(xSpeed - ySpeed + zRotation);
    backRight.set(xSpeed + ySpeed - zRotation);
  }
}
