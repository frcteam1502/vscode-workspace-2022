package frc.robot.subsystems;

import java.util.ArrayList;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Motors;
import frc.robot.PathFindingConstants.AutoConstants;
import frc.robot.PathFindingConstants.DriveConstants;

public class Drivetrain extends SubsystemBase {
  // The motors
  private final CANSparkMax frontLeft = Motors.DRIVE_FRONT_LEFT;
  private final CANSparkMax backLeft = Motors.DRIVE_BACK_LEFT;
  private final CANSparkMax frontRight = Motors.DRIVE_FRONT_RIGHT;
  private final CANSparkMax backRight = Motors.DRIVE_BACK_RIGHT;

  // The robot's drive
  public final MecanumDrive m_drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  // The Encoders
  private RelativeEncoder leftFrontEncoder;
  private RelativeEncoder leftBackEncoder;
  private RelativeEncoder rightFrontEncoder;
  private RelativeEncoder rightBackEncoder;

  // The gyro sensor
  private final Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  private final SwerveDriveOdometry m_odometry;

  private SwerveModuleState[] state;

  public Drivetrain() {    
    leftFrontEncoder= frontLeft.getEncoder();
    leftBackEncoder= backLeft.getEncoder();
    rightFrontEncoder= frontRight.getEncoder();
    rightBackEncoder= backRight.getEncoder();

    backRight.setInverted(true);
    frontRight.setInverted(true);

    // Sets the distance per pulse for the encoders
    this.leftFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.leftBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    resetEncoders();
    m_odometry = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, m_gyro.getRotation2d());
  }

  public void move(double ySpeed, double xSpeed, double zRotation) {
    ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);

    m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  @Override
  public void periodic() {
    if(state != null) m_odometry.update(m_gyro.getRotation2d(), state);
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
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    this.leftFrontEncoder.setPosition(0);
    this.leftBackEncoder.setPosition(0);
    this.rightFrontEncoder.setPosition(0);
    this.rightBackEncoder.setPosition(0);
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

  public void toWheelSpeed(SwerveModuleState... state) {
    this.state = state;
    ChassisSpeeds speed = DriveConstants.kDriveKinematics.toChassisSpeeds(state);
    move(speed.vyMetersPerSecond, speed.vxMetersPerSecond, speed.omegaRadiansPerSecond);
  }

  public Command createPPCommand(PathPlannerTrajectory trajectory) {
    PPSwerveControllerCommand PPCommand =
      new PPSwerveControllerCommand(
       trajectory,
       this::getPose,
       DriveConstants.kDriveKinematics,
       new PIDController(DriveConstants.kPDriveVel, 0, 0),
       new PIDController(DriveConstants.kPDriveVel, 0, 0),
       DriveConstants.PIDController,
       this::toWheelSpeed,
       this
       );
      return PPCommand.andThen(() -> stopmotors());
  }

  public Command createTrajectoryCommand(String name) {
    PathPlannerTrajectory path = PathPlanner.loadPath(name, AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared);
    return createPPCommand(path);
  }

  public void stopmotors() {
    m_drive.stopMotor();
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