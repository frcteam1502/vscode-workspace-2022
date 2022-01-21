// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  
  private final CANSparkMax frontLeft, frontRight, backLeft, backRight;
  /** Creates a new ExampleSubsystem. */
  public Drivetrain(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax backLeft, CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.frontRight = frontRight;
    this.backLeft = backLeft;
    this.backRight = backRight;
  }

  public void move(double xSpeed, double ySpeed, double zRotation) {
    // multiple front left motor speed by -1

    // If speed is greater than or less than 1 speed will be set to equal 1 or -1 respectfully    
    ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);
    ySpeed = applyDeadband(ySpeed, deadband);

    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
    xSpeed = applyDeadband(xSpeed, deadband);

    // double denominator = Math.max(Math.abs(ySpeed) + Math.abs(xSpeed) + Math.abs(zRotation), 1);
    // frontLeft.set(truncateDecimal(((((xSpeed + ySpeed + zRotation)/ denominator))/2)));
    // frontRight.set(truncateDecimal(((((-xSpeed + ySpeed - zRotation)/ denominator))/2)));
    // backLeft.set(truncateDecimal((((((-xSpeed + ySpeed + zRotation)/ denominator))/2))));
    // backRight.set(truncateDecimal((((((xSpeed + ySpeed - zRotation)/ denominator))/2))));

    frontLeft.set(xSpeed + ySpeed + zRotation);
    frontRight.set(xSpeed - ySpeed - zRotation);
    backLeft.set(xSpeed - ySpeed + zRotation);
    backRight.set(xSpeed + ySpeed - zRotation);
    
    // frontLeft.set (0.2); // change to front right
    // frontRight.set(0.2); // good
    // backLeft.set(0.2); // good
    // backRight.set(0.2); 
  }

  private final double truncateDecimal(double number){

    number = (((int)(number*100))/100.0);

    return number;
  }

  // Returns 0.0 if the given value is within the specified range around zero.
  // The remaining range between the deadband and 1.0 is scaled from 0.0 to 1.0.
  private final double deadband = 0.09;
  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }
}
