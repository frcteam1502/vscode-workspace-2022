// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PathFindingConstants.DriveConstants;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax frontLeft, frontRight, backLeft, backRight;
  
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(frontLeft, backLeft);

  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(frontRight, backRight);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The Encoders
  private RelativeEncoder leftFrontEncoder;
  private RelativeEncoder leftBackEncoder;
  private RelativeEncoder rightFrontEncoder;
  private RelativeEncoder rightBackEncoder;

  // The gyro sensor
  private final Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  public Drivetrain(CANSparkMax driveFrontLeft, CANSparkMax driveFrontRight, CANSparkMax driveBackLeft, CANSparkMax driveBackRight ) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = driveFrontLeft;
    this.frontRight = driveFrontRight;
    this.backLeft = driveBackLeft;
    this.backRight = driveBackRight;
    
    leftFrontEncoder= frontLeft.getEncoder();
    leftBackEncoder= backLeft.getEncoder();
    rightFrontEncoder= frontRight.getEncoder();
    rightBackEncoder= backRight.getEncoder();

    m_rightMotors.setInverted(true);

    // Sets the distance per pulse for the encoders
    this.leftFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.leftBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
  }

  public void move(double ySpeed, double xSpeed, double zRotation) {
    ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);

    frontLeft.set(xSpeed + ySpeed + zRotation);
    frontRight.set(xSpeed - ySpeed - zRotation);
    backLeft.set(xSpeed - ySpeed + zRotation);
    backRight.set(xSpeed + ySpeed - zRotation);
  }

  @Override
  public void periodic() {
    m_odometry.update(
        m_gyro.getRotation2d(), this.leftFrontEncoder.getPosition(), this.rightFrontEncoder.getPosition());
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(this.leftFrontEncoder.getVelocity(), this.rightFrontEncoder.getVelocity());
  } 

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(rightVolts);
    m_drive.feed();
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    this.leftFrontEncoder.setPosition(0);
    this.leftBackEncoder.setPosition(0);
    this.rightFrontEncoder.setPosition(0);
    this.rightBackEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (leftFrontEncoder.getPosition() + rightFrontEncoder.getPosition()) / 2.0;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getRotation2d().getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
}
