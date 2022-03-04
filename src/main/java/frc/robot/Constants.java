package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class Constants {
    public final static class Motors {
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(12, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(17, MotorType.kBrushless); //changed from 16 to 17 by Ian's request
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(11, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(15, MotorType.kBrushless);
        public static final CANSparkMax INTAKE = new CANSparkMax(1, MotorType.kBrushless); 

        public static final CANSparkMax SHOOTER_RIGHT = new CANSparkMax(13, MotorType.kBrushless);
        public static final CANSparkMax SHOOTER_LEFT = new CANSparkMax(14, MotorType.kBrushless);
        public static final CANSparkMax INDEX = new CANSparkMax(6, MotorType.kBrushless);
    }

    public final static class Joysticks {
        public static final Joystick RIGHT_JOYSTICK = new Joystick(0);
        public static final Joystick LEFT_JOYSTICK = new Joystick(1);
        public static final XboxController CONTROLLER = new XboxController(0);
    }

    public static final class XboxButtons {        
        public static final JoystickButton MODE_BUTTON = new JoystickButton(Joysticks.CONTROLLER, 11); // no clue which number is mode button

        public static final JoystickButton LEFT_BUMPER = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kLeftBumper.value); // Extend
        public static final JoystickButton RIGHT_BUMPER = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kRightBumper.value); // Contract
        public static final JoystickButton BUTTON_Y = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kY.value); // Rotate Clockwise
        public static final JoystickButton BUTTON_A = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kA.value); // Rotate Counter Clockwise
        public static final JoystickButton BUTTON_X = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kX.value); // Rotate babies Clockwise
        public static final JoystickButton BUTTON_B = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kB.value); // Rotate babies Counter Clockwise
        public static final JoystickButton BACK = new JoystickButton(Joysticks.CONTROLLER, 7);
        public static final JoystickButton START = new JoystickButton(Joysticks.CONTROLLER, 8);
        // I dont know the values for these
        public static final JoystickButton LEFT_JOYSTICK = new JoystickButton(Joysticks.CONTROLLER, 12);
        public static final JoystickButton RIGHT_JOYSTICK = new JoystickButton(Joysticks.CONTROLLER, 13);
        public static final JoystickButton LEFT_STICK = new JoystickButton(Joysticks.CONTROLLER, 14);
        public static final JoystickButton RIGHT_STICK = new JoystickButton(Joysticks.CONTROLLER, 15);
    }

    
    public static final class Cameras {
        // public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();
    }
}