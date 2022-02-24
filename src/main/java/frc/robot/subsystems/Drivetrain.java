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
  public Drivetrain(CANSparkMax driveFrontLeft, CANSparkMax driveFrontRight, CANSparkMax driveBackLeft, CANSparkMax driveBackRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = driveFrontLeft;
    this.frontRight = driveFrontRight;
    this.backLeft = driveBackLeft;
    this.backRight = driveBackRight;
  }

  @Override
  public void periodic() {}

  public void TankDrive(double leftSpeed, double rightSpeed) {
    frontLeft.set(leftSpeed);
    backLeft.set(leftSpeed);
    frontRight.set(rightSpeed);
    backRight.set(rightSpeed);
  }

  public void MecanumDrive(double xSpeed, double ySpeed, double zRotation) {
    frontLeft.set(ySpeed + xSpeed + zRotation);
    backLeft.set(ySpeed - xSpeed + zRotation);
    frontRight.set((ySpeed - xSpeed - zRotation));
    backRight.set((ySpeed + xSpeed - zRotation));

    MathUtil.applyDeadband(xSpeed, 0.02);
    MathUtil.applyDeadband(ySpeed, 0.02);
  }
}