package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax frontLeft, frontRight, backLeft, backRight;

  public Drivetrain(CANSparkMax driveFrontLeft, CANSparkMax driveFrontRight, CANSparkMax driveBackLeft, CANSparkMax driveBackRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = driveFrontLeft;
    this.frontRight = driveFrontRight;
    this.backLeft = driveBackLeft;
    this.backRight = driveBackRight;
  }

  @Override
  public void periodic() {}

  public void move(double ySpeed, double xSpeed, double zRotation) {
    ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);

    frontLeft.set(xSpeed + ySpeed + zRotation);
    frontRight.set(xSpeed - ySpeed - zRotation);
    backLeft.set(xSpeed - ySpeed + zRotation);
    backRight.set(xSpeed + ySpeed - zRotation);
    
    SmartDashboard.putNumber("Y Speed", ySpeed);
    SmartDashboard.putNumber("X Speed", xSpeed);
    SmartDashboard.putNumber("Z Rotation", zRotation);
  }

  // Temporary motor test methods
  public void testMotorOn() {
    frontLeft.set(0.2);
  }

  public void testMotorOff() {
    frontLeft.set(0);
  }
}