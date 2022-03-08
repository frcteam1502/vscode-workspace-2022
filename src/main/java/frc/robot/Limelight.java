
package frc.robot;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {
    double distance;
   
    public void findDistance(){
        Target target = getTarget();
        distance = 81 / Math.tan(60 + target.ty);
        SmartDashboard.putNumber("Distance", distance);
    }

    public static class Target {
        public double tx;
        public double ty;
        public double tv;
        public double ta;
        public double ts;

        Target(double x, double y, double v, double a, double s) {
            tx = x;
            ty = y;
            tv = v;
            ta = a;
            ts = s;
        }
  }

  public static Target getTarget() {
    double tx = getTableEntry("tx").getDouble(0);
    double ty = getTableEntry("ty").getDouble(0);
    ty = 2;
    double ta = getTableEntry("ta").getDouble(0);
    double tv = getTableEntry("tv").getDouble(0);
    double ts = getTableEntry("ts").getDouble(0);
    return new Target(tx, ty, tv, ta, ts);
  }

  private static NetworkTableEntry getTableEntry(String entry) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(entry);
  }
}