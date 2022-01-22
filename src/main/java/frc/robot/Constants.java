// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
>>>>>>> 917cf093955caef38baf244b40eb2fdcfe27c873
=======
>>>>>>> da5feb1547906532dd54f9507119ba4d387b4f18
import edu.wpi.first.wpilibj.Joystick;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
<<<<<<< HEAD
    //Motors
    public static final int DRIVE_FRONT_LEFT = 1;
    public static final int DRIVE_BACK_LEFT = 3;
    public static final int DRIVE_FRONT_RIGHT = 2;
    public static final int DRIVE_BACK_RIGHT = 4;

    //Joysticks
    public static final int RIGHT_JOYSTICK = 1;

    public final static class Motors {
        public static final CANSparkMax frontLeft = new CANSparkMax(DRIVE_FRONT_LEFT, MotorType.kBrushless);
        public static final CANSparkMax backLeft = new CANSparkMax(DRIVE_BACK_LEFT, MotorType.kBrushless);
        public static final CANSparkMax frontRight = new CANSparkMax(DRIVE_FRONT_RIGHT, MotorType.kBrushless);
        public static final CANSparkMax backRight = new CANSparkMax(DRIVE_BACK_RIGHT, MotorType.kBrushless);
    }

    public final static class Joysticks {
        public static final Joystick rightJoystick = new Joystick(RIGHT_JOYSTICK);
=======
    public static final class Motors {
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(0, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(4, MotorType.kBrushless);
    }
    public static final class Joysticks {
        public static final Joystick RIGHT_JOYSTICK = new Joystick(1);
        // public static final XboxController XBOX_CONTROLLER = new XboxController(0);
        public static final Joystick LEFT_JOYSTICKS = new Joystick(0);
    }
    public static final class Cameras {
        // public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();
>>>>>>> 917cf093955caef38baf244b40eb2fdcfe27c873
    }
}