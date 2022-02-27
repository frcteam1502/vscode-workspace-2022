
package frc.robot;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;

public class Limelight {
    private static Limelight inst;

    private final NetworkTable subtable;

    NetworkTableEntry tx = getEntry("tx");
    NetworkTableEntry ty = getEntry("ty");
    NetworkTableEntry ta = getEntry("ta");
    NetworkTableEntry tv = getEntry("tv");

    // read values periodically
    public double x = tx.getDouble(0.0);
    public double y = ty.getDouble(0.0);
    public double area = ta.getDouble(0.0);
    public boolean target = tv.getFlags() == 1;



    public Limelight() {
        subtable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * <p>gets the <code>Limelight</code> instance
     * @return the <code>Limelight</code> instance
     */
    public static Limelight getInstance() {
        if (inst == null)
            return inst = new Limelight();
        else
            return inst;
    }

    /**
     * <p>logs limelight data to the shuffleboard
     * 
     * @return void, so don't use it in an expression!
     */
    public void logToShuffleboard() {
        NetworkTableEntry tx = getEntry("tx");
        NetworkTableEntry ty = getEntry("ty");
        NetworkTableEntry ta = getEntry("ta");
        NetworkTableEntry tv = getEntry("tv");

        // read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        boolean target = tv.getFlags() == 1;

        // post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putBoolean("LimelightTargetExistence", target);
    }

    /**
     * <p>gets the limelight network table
     * @return limelight table
     */
    public NetworkTable getTable() {
        return subtable;
    }

    /**
     * <p>gets four entries from the limelight network table
     * <p>edit <code>getEntry(String)</code> to access more values than provided
     * 
     * @return array of table entries [tx, ty, ta, tv]
     */
    public NetworkTableEntry[] getEntries() {
        return new NetworkTableEntry[] { subtable.getEntry("tx"), subtable.getEntry("ty"), subtable.getEntry("ta"),
                subtable.getEntry("tv") };
    }

    /**
     * <p>gets an entry from the limelight network table
     * 
     * <p><em>use THIS NOT ANYTHING ELSE if you want to access more properties than the 4 already implemented</em>
     * 
     * @param entry String specifying table entry
     * @return the entry: NetworkTableEntry
     */
    public NetworkTableEntry getEntry(String entry) {
        switch (entry) {
        case "tx":
            break;
        case "ty":
            break;
        case "ta":
            break;
        case "tv":
            break;
        default:
            return null;
        }

        return subtable.getEntry(entry);
    }

    /**
     * <p>gets an entry from the limelight network table
     * <p>edit <code>getEntry(String)</code> if you need more values than those already implemented
     * 
     * @param entry int representing the entry
     * @return the entry: NetworkTableEntry
     */
    public NetworkTableEntry getEntry(int entry) {
        if(entry < 0 || entry > 3) return null;
        return getEntries()[entry];
    }

}