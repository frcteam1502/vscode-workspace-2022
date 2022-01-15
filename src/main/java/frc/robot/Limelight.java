package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight {
    
    public static class Target {
        
        // Horizontal offset from crosshair to target (+-29.8)
         
        public double horizontalOffset;
        
        // Vertical offset from crosshair to target (+-24.85)
        
        public double verticalOffset;
        
        // Target area of total image (0% to 100%)
        
        public double area;
        
        // Skew or rotation (-90deg to 0deg)
        
        public double skew;
    
        Target(double h, double v, double a, double s) {
          horizontalOffset = h;
          verticalOffset = v;
          area = a;
          skew = s;
        }
      }
    
      public static Target getTarget() {
        double isVisible = getTableEntry("tv").getDouble(0);
        if (isVisible == 0)
          return null;
        double horizontalOffset = getTableEntry("tx").getDouble(0);
        double verticalOffset = getTableEntry("ty").getDouble(0);
        double area = getTableEntry("ta").getDouble(0);
        double skew = getTableEntry("ts").getDouble(0);
        return new Target(horizontalOffset, verticalOffset, area, skew);
      }
    
      private static NetworkTableEntry getTableEntry(String entry) {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry(entry);
      }
}
