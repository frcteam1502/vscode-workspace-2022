package frc.robot.subsystems;

import java.util.ArrayList;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
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
import frc.robot.PathFindingConstants.DriveConstants;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;

public class Drivetrain extends SubsystemBase {
  // The motors
  private final CANSparkMax frontLeft = Motors.DRIVE_FRONT_LEFT;
  private final CANSparkMax backLeft = Motors.DRIVE_BACK_LEFT;
  private final CANSparkMax frontRight = Motors.DRIVE_FRONT_RIGHT;
  private final CANSparkMax backRight = Motors.DRIVE_BACK_RIGHT;

  // The robot's drive
  public final MecanumDrive m_drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  // The Encoders
  private RelativeEncoder leftFrontEncoder = frontLeft.getEncoder();
  private RelativeEncoder leftBackEncoder = backLeft.getEncoder();
  private RelativeEncoder rightFrontEncoder = frontRight.getEncoder();
  private RelativeEncoder rightBackEncoder = backRight.getEncoder();

  // The gyro sensor
  private final Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  public final SwerveDriveOdometry m_odometry;

  //Swerve module
  private SwerveModuleState[] state = null;

  public Drivetrain() {    
    this.leftFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.leftBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    resetEncoders();
    m_odometry = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, m_gyro.getRotation2d());

    m_drive.setMaxOutput(.3);
  }

  public void move(double ySpeed, double xSpeed, double zRotation) {
    frontLeft.set(xSpeed + ySpeed + zRotation);
    frontRight.set(xSpeed - ySpeed - zRotation);
    backLeft.set(xSpeed - ySpeed + zRotation);
    backRight.set(xSpeed + ySpeed - zRotation);
  }

  @Override
  public void periodic() {
    if (state != null) m_odometry.update(m_gyro.getRotation2d(), state);
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

  public void toWheelSpeed(SwerveModuleState... state) {
    ChassisSpeeds speed = DriveConstants.kDriveKinematics.toChassisSpeeds(state);
    MecanumDriveWheelSpeeds wheelSpeeds = DriveConstants.kMecanumKinematics.toWheelSpeeds(speed);
    this.state = state;
    frontLeft.set(wheelSpeeds.frontLeftMetersPerSecond);
    frontRight.set(-wheelSpeeds.frontRightMetersPerSecond);
    backLeft.set(wheelSpeeds.rearLeftMetersPerSecond);
    backRight.set(-wheelSpeeds.rearRightMetersPerSecond);
    this.m_drive.feed();
  }

  public Command createTrajectoryCommand(PathPlannerTrajectory trajectory) {
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