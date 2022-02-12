package frc.robot;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public class PathFindingConstants {
    public static final class DriveConstants {
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
        public static final double kPDriveVel = 0.21646;
        
        public static final double kTrackwidthMeters = 0.54483;
        public static final Rotation2d fLeft = new Rotation2d(-0.643, 0.766);
        public static final Rotation2d fRight = new Rotation2d(0.643, 0.766);
        public static final Rotation2d bLeft = new Rotation2d(-0.643, -0.766);
        public static final Rotation2d bRight = new Rotation2d(0.643, -0.766);
        public static final Translation2d frontLeft = new Translation2d(.4064, fLeft);
        public static final Translation2d frontRight = new Translation2d(.4064, fRight);
        public static final Translation2d backLeft = new Translation2d(.4064, bLeft);
        public static final Translation2d backRight = new Translation2d(.4064, bRight);
        public static final SwerveDriveKinematics kDriveKinematics =
            new SwerveDriveKinematics(frontLeft, backLeft, frontRight, backRight);
        public static final ProfiledPIDController PIDController = 
            new ProfiledPIDController(kPDriveVel, 0, 0, new Constraints(AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared));    
    
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterMeters = 0.15;
        public static final double kEncoderDistancePerPulse =
            (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
    
        public static final double ksVolts = 0.1652;
        public static final double kvVoltSecondsPerMeter = 0.19123;
        public static final double kaVoltSecondsSquaredPerMeter = 0.016202;
    
        
      }
      
      //WE CAN CHANGE THESE ON THE FLY!!! Good for us
      public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = .5;
        public static final double kMaxAccelerationMetersPerSecondSquared = .5;
    
        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        // If things don't work https://docs.wpilib.org/pt/latest/docs/software/advanced-controls/trajectories/ramsete.html#constructing-the-ramsete-controller-object
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
      }
}
