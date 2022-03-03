package frc.robot;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public class PathFindingConstants {
    public static final class DriveConstants {
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
        public static final double kPDriveVel = 0.21646;
        
        public static final Translation2d frontLeft = new Translation2d(0.2286, 0.257175);
        public static final Translation2d frontRight = new Translation2d(0.2286, -0.257175);
        public static final Translation2d backLeft = new Translation2d(-0.2286, 0.257175);
        public static final Translation2d backRight = new Translation2d(-0.2286, -0.257175);
        public static final MecanumDriveKinematics kMecanumKinematics = new MecanumDriveKinematics(frontLeft, frontRight, backLeft, backRight);
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(frontLeft, frontRight, backLeft, backRight);
        public static final ProfiledPIDController PIDController = 
            new ProfiledPIDController(kPDriveVel, 0, 0, new Constraints(AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared));    
    
        public static final double kEncoderCPR = 535.5;
        public static final double kGearRatio = 12.75;
        public static final double kWheelDiameterMeters = 0.2;
        public static final double kWheelCircumfrence = Math.PI * kWheelDiameterMeters;
        public static final double kEncoderDistancePerPulse =
            kWheelCircumfrence / kGearRatio;
      }
      
      //WE CAN CHANGE THESE ON THE FLY!!! Good for us
      public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxAccelerationMetersPerSecondSquared = .5;
      }
}
