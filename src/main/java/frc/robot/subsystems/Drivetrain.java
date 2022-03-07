// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax frontLeft, frontRight, backLeft, backRight;
  /** Creates a new Drivetrain. */
  public Drivetrain(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax backLeft, CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.frontRight = frontRight;
    this.backLeft = backLeft;
    this.backRight = backRight;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double xSpeed, double ySpeed, double zRotation) {
    if (Math.abs(xSpeed) < 0.01) xSpeed = 0;
    if (Math.abs(ySpeed) < 0.01) ySpeed = 0;
    if (Math.abs(zRotation) < 0.01) zRotation = 0;

    xSpeed *= 0.1;
    ySpeed *= 0.1;
    zRotation *= 0.1;

    SmartDashboard.putNumber("xSpeed", xSpeed);
    SmartDashboard.putNumber("ySpeed", ySpeed);
    SmartDashboard.putNumber("zRotation", zRotation);

    SmartDashboard.putNumber("Front Left", (ySpeed + xSpeed + zRotation));
    frontLeft.set((ySpeed + xSpeed + zRotation));

    SmartDashboard.putNumber("Back Left", (ySpeed - xSpeed + zRotation));
    backLeft.set((ySpeed - xSpeed + zRotation));
    
    SmartDashboard.putNumber("Front Right", (ySpeed - xSpeed - zRotation));
    frontRight.set(-(ySpeed - xSpeed - zRotation));
    
    SmartDashboard.putNumber("Back Right", (ySpeed + xSpeed - zRotation));
    backRight.set(-(ySpeed + xSpeed - zRotation));

    // MathUtil.applyDeadband(xSpeed, 0.02);
    // MathUtil.applyDeadband(ySpeed, 0.02);
  }
}
