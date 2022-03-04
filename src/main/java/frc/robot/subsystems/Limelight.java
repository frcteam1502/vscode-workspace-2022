
package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LimelightDistance;
import edu.wpi.first.networktables.NetworkTable;

public class Limelight extends SubsystemBase {

    public Limelight() {
        setDefaultCommand(new LimelightDistance(this));
    }
    // get the default instance of NetworkTables
NetworkTableInstance inst = NetworkTableInstance.getDefault();
double distance;
// get a reference to the subtable called "datatable"
NetworkTable table = inst.getTable("limelight");
 
// NetworkTableEntry TeamEntry = table.getEntry("tx");
NetworkTableEntry xEntry = table.getEntry("tx");
NetworkTableEntry yEntry = table.getEntry("ty");
NetworkTableEntry aEntry = table.getEntry("ta");
NetworkTableEntry lEntry = table.getEntry("tl");
NetworkTableEntry vEntry = table.getEntry("tv");
NetworkTableEntry sEntry = table.getEntry("ts");
 
NetworkTableEntry tshortEntry = table.getEntry("tshort");
NetworkTableEntry tlongEntry = table.getEntry("tlong");
NetworkTableEntry thorEntry = table.getEntry("thor");
NetworkTableEntry tvertEntry = table.getEntry("tvert");
NetworkTableEntry getpipeEntry = table.getEntry("getpipe");
NetworkTableEntry camtranEntry = table.getEntry("camtran");
NetworkTableEntry ledModeEntry = table.getEntry("ledMode");
 
// double tx = xEntry.getDouble(0.0);
double tx = xEntry.getDouble(0.0); // Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
double ty = yEntry.getDouble(0.0); // Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
double ta = aEntry.getDouble(0.0); // Target Area (0% of image to 100% of image)
double tl = lEntry.getDouble(0.0); // The pipeline’s latency contribution (ms) Add at least 11ms for image capture
double tv = vEntry.getDouble(0.0); // Whether the limelight has any valid targets (0 or 1)
double ts = sEntry.getDouble(0.0); // Skew or rotation (-90 degrees to 0 degrees)

@Override
public void periodic() {}

public void findDistance(){
    distance = 81 / Math.tan(60 + ty);
    SmartDashboard.putNumber("Distance", distance);
}

}