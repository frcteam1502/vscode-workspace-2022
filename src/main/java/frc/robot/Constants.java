// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public enum Encoders {
        LeftArmAngleEncoder (Motors.LEFT_ARM_ANGLE.getEncoder()),
        RightArmAngleEncoder (Motors.RIGHT_ARM_ANGLE.getEncoder()),
        LeftExtenderEncoder (Motors.LEFT_ARM_EXTENDER.getEncoder()),
        RightExtenderEncoder (Motors.RIGHT_ARM_EXTENDER.getEncoder()),
        LeftBabyEncoder (Motors.LEFT_BABY.getEncoder()),
        RightBabyEncoder (Motors.RIGHT_BABY.getEncoder());
    
        public RelativeEncoder Encoder;
        Encoders(RelativeEncoder encoder) {
          this.Encoder = encoder;
        }
    }
    
    public final static class Motors {
        // Drivetrain
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(12, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(11, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(16, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(15, MotorType.kBrushless);
        
        // Climber
        public static final CANSparkMax LEFT_ARM_ANGLE = new CANSparkMax(4, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_ANGLE = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax LEFT_ARM_EXTENDER = new CANSparkMax(10, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_EXTENDER = new CANSparkMax(9, MotorType.kBrushless);
        public static final CANSparkMax LEFT_BABY = new CANSparkMax(8, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_BABY = new CANSparkMax(7, MotorType.kBrushless);

        // Shooter
        public static final CANSparkMax SHOOTER_RIGHT = new CANSparkMax(13, MotorType.kBrushless);
        public static final CANSparkMax SHOOTER_LEFT = new CANSparkMax(14, MotorType.kBrushless);
        public static final CANSparkMax TURRET = new CANSparkMax(5, MotorType.kBrushless);
        public static final CANSparkMax ANGLE = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax INDEX = new CANSparkMax(6, MotorType.kBrushless);
    }

    public final static class Joysticks {
        public static final Joystick JOYSTICK_LEFT = new Joystick(0);
        public static final Joystick JOYSTICK_RIGHT = new Joystick(1);
        public static final XboxController CONTROLLER = new XboxController(2);
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
        // I dont know the values for these
        public static final JoystickButton LEFT_JOYSTICK = new JoystickButton(Joysticks.CONTROLLER, 12);
        public static final JoystickButton RIGHT_JOYSTICK = new JoystickButton(Joysticks.CONTROLLER, 13);
        public static final JoystickButton LEFT_STICK = new JoystickButton(Joysticks.CONTROLLER, 14);
        public static final JoystickButton RIGHT_STICK = new JoystickButton(Joysticks.CONTROLLER, 15);
    }

    public static final class EncoderMaxes {
        public static final double LEFT_MAX = 0;
        public static final double RIGHT_MAX = 0;
        public static final double LEFT_ANGLE_MAX = 0;
        public static final double RIGHT_ANGLE_MAX = 0;
        public static final double LEFT_BABY_MAX = 0;
        public static final double RIGHT_BABY_MAX = 0;
    }
}