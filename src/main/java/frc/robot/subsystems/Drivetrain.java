package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PathFindingConstants.AutoConstants;
import frc.robot.PathFindingConstants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax frontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
  private final CANSparkMax backLeft = new CANSparkMax(Constants.DRIVE_BACK_LEFT, MotorType.kBrushless);
  private final CANSparkMax frontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(Constants.DRIVE_BACK_RIGHT, MotorType.kBrushless);
  
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(frontLeft, backLeft);

  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(frontRight, backRight);

  // The robot's drive
  public final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The Encoders
  private RelativeEncoder leftFrontEncoder;
  private RelativeEncoder leftBackEncoder;
  private RelativeEncoder rightFrontEncoder;
  private RelativeEncoder rightBackEncoder;

  // The gyro sensor
  private final Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  public Drivetrain() {    
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

  public Command createRamseteCommand(Trajectory trajectory) {
    RamseteCommand ramseteCommand =
      new RamseteCommand(
        trajectory,
        this::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(
          DriveConstants.ksVolts,
          DriveConstants.kvVoltSecondsPerMeter,
          DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        this::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        this::tankDriveVolts,
        this);
        return ramseteCommand.andThen(() -> stopmotors());
  }

  public Command createTrajectoryCommand(String path) {
    var trajectoryJSON = "output/" + path + ".wpilib.json";
    /*
    var autoVoltageConstraint =
      new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(
          DriveConstants.ksVolts,
          DriveConstants.kvVoltSecondsPerMeter,
          DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        10);

    TrajectoryConfig config =
      new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(DriveConstants.kDriveKinematics)
                .addConstraint(autoVoltageConstraint);
   */
    Trajectory trajectory = new Trajectory();
    /*
    TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            // Pass config
            config); */

    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
    return createRamseteCommand(trajectory);
  }

  public void stopmotors() {
    m_leftMotors.set(0);
    m_rightMotors.set(0);
  }

  public CANSparkMax getMotor(int motor) {
    ArrayList<CANSparkMax> array = new ArrayList<>();
    array.add(frontLeft);
    array.add(frontRight);
    array.add(backLeft);
    array.add(backRight);
    if (motor >= 0 && motor <= 3) return array.get(motor);
    else return null;
    
  }
}