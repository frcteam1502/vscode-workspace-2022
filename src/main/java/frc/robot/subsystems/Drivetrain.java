package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PathFindingConstants.DriveConstants;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  //Motors
  private CANSparkMax frontLeft, frontRight, backLeft, backRight;

  // The Encoders
  private RelativeEncoder leftFrontEncoder,
                          leftBackEncoder, 
                          rightFrontEncoder, 
                          rightBackEncoder;

  //The gyro sensor
  public final Gyro m_gyro = new ADXRS450_Gyro();

  public Drivetrain(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax backLeft, CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.frontRight = frontRight;
    this.backLeft = backLeft;
    this.backRight = backRight;

    this.leftFrontEncoder = frontLeft.getEncoder();
    this.leftBackEncoder = backLeft.getEncoder();
    this.rightFrontEncoder = frontRight.getEncoder();
    this.rightBackEncoder = backRight.getEncoder();

    this.leftFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.leftBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightFrontEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    this.rightBackEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    resetEncoders();
  }

  @Override
  public void periodic() {}

  public void move(double xSpeed, double ySpeed, double zRotation) {
    if (Math.abs(xSpeed) < 0.01) xSpeed = 0;
    if (Math.abs(ySpeed) < 0.01) ySpeed = 0;
    if (Math.abs(zRotation) < 0.01) zRotation = 0;

    xSpeed *= 0.6;
    ySpeed *= 0.6;
    zRotation *= 0.6;

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
  }

  public void TankDrive(double leftSpeed, double rightSpeed) {
    frontLeft.set(leftSpeed);
    backLeft.set(leftSpeed);
    frontRight.set(rightSpeed);
    backRight.set(rightSpeed);
  }

  public void Forward() {
    TankDrive(.2, -.2);
  }

  public void Stop() {
    TankDrive(0, 0);
  }

  public void resetEncoders() {
    this.leftFrontEncoder.setPosition(0);
    this.leftBackEncoder.setPosition(0);
    this.rightFrontEncoder.setPosition(0);
    this.rightBackEncoder.setPosition(0);
  }

  public double getAverageEncoderDistance() {
    return ((leftFrontEncoder.getPosition() + -rightFrontEncoder.getPosition()) / 2.0);
  }
}
