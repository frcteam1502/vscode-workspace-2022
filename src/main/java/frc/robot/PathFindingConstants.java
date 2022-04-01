package frc.robot;

public class PathFindingConstants {
    public static final class DriveConstants {
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;

        public static final double kEncoderCPR = 535.5;
        public static final double kGearRatio = 12.75;
        public static final double kWheelDiameterMeters = 0.1524; //6in
        public static final double kWheelCircumfrence = Math.PI * kWheelDiameterMeters;
        public static final double kEncoderDistancePerPulse = kWheelCircumfrence / kGearRatio;
      }
}