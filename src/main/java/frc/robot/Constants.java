package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public final class Constants {
    
    public final static class Motors {
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(1, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(4, MotorType.kBrushless);
        public static final CANSparkMax SHOOTER_RIGHT = new CANSparkMax(5, MotorType.kBrushless);
        public static final CANSparkMax SHOOTER_LEFT = new CANSparkMax(6, MotorType.kBrushless);
        public static final CANSparkMax INTAKE = new CANSparkMax(7, MotorType.kBrushless); 
    }

    public final static class Joysticks {
        public static final Joystick RIGHT_JOYSTICK = new Joystick(0);
        public static final Joystick LEFT_JOYSTICK = new Joystick(1);
        public static final XboxController CONTROLLER = new XboxController(0);
    }
    
    public static final class Cameras {
        // public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();
    }
}