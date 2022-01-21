package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public class PathFindingConstants {
    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 1;
        public static final int kLeftMotor2Port = 3;
        public static final int kRightMotor1Port = 2;
        public static final int kRightMotor2Port = 4;
    
        public static final int[] kLeftEncoderPorts = new int[] {0, 1};
        public static final int[] kRightEncoderPorts = new int[] {2, 3};
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
    
        public static final double kTrackwidthMeters = 0.54483;
        public static final DifferentialDriveKinematics kDriveKinematics =
            new DifferentialDriveKinematics(kTrackwidthMeters);
    
        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterMeters = 0.15;
        public static final double kEncoderDistancePerPulse =
            // Assumes the encoders are directly mounted on the wheel shafts
            (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
    
        public static final double ksVolts = 0.1652;
        public static final double kvVoltSecondsPerMeter = 0.19123;
        public static final double kaVoltSecondsSquaredPerMeter = 0.016202;
    
        public static final double kPDriveVel = 0.21646;
      }
    
      public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
      }
      
      //WE CAN CHANGE THESE ON THE FLY!!! Good for us
      public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxAccelerationMetersPerSecondSquared = 1;
    
        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        // If things don't work https://docs.wpilib.org/pt/latest/docs/software/advanced-controls/trajectories/ramsete.html#constructing-the-ramsete-controller-object
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
      }
}
