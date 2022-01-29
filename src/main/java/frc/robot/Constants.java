// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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
    public final static class Motors {
        // Drivetrain
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(1, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(4, MotorType.kBrushless);
        
        // Climber
        public static final CANSparkMax LEFT_ARM_ANGLE = new CANSparkMax(5, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_ANGLE = new CANSparkMax(6, MotorType.kBrushless);
        public static final CANSparkMax LEFT_ARM_EXTENDER = new CANSparkMax(7, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_EXTENDER = new CANSparkMax(8, MotorType.kBrushless);
        public static final CANSparkMax LEFT_BABY_ARM = new CANSparkMax(9, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_BABY_ARM = new CANSparkMax(10, MotorType.kBrushless);
    }

    public final static class Joysticks {
        public static final Joystick rightJoystick = new Joystick(1);
        public static final Joystick leftJoystick = new Joystick(2);
    }
}