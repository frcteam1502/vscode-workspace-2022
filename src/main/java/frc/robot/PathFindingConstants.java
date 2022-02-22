package frc.robot;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public class PathFindingConstants {
    public static final class DriveConstants {
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
        public static final double kPDriveVel = 0.21646;
        
        public static final double kTrackwidthMeters = 0.54483;
        public static final Translation2d frontLeft = new Translation2d(0.2286, 0.257175);
        public static final Translation2d frontRight = new Translation2d(0.2286, -0.257175);
        public static final Translation2d backLeft = new Translation2d(-0.2286, 0.257175);
        public static final Translation2d backRight = new Translation2d(-0.2286, -0.257175);
        public static final SwerveDriveKinematics kDriveKinematics =
            new SwerveDriveKinematics(frontLeft, frontRight, backLeft, backRight);
        public static final ProfiledPIDController PIDController = 
            new ProfiledPIDController(kPDriveVel, 0, 0, new Constraints(AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared));    
    
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterMeters = 0.15;
        public static final double kEncoderDistancePerPulse =
            (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
      }
      
      //WE CAN CHANGE THESE ON THE FLY!!! Good for us
      public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 4;
      }
}
