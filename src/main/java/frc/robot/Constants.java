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

    public final Limelight limelight = new Limelight();

    public enum Encoders {
        LeftArmAngleEncoder (Motors.LEFT_ARM_ANGLE.getEncoder()),
        RightArmAngleEncoder (Motors.RIGHT_ARM_ANGLE.getEncoder()),
        LeftExtenderEncoder (Motors.LEFT_ARM_EXTENDER.getEncoder()),
        RightExtenderEncoder (Motors.RIGHT_ARM_EXTENDER.getEncoder()),
        LeftBabyEncoder (Motors.LEFT_BABY.getEncoder()),
        RightBabyEncoder (Motors.RIGHT_BABY.getEncoder()),
        FlapEncoder (Motors.FLAP.getEncoder());
    
        public RelativeEncoder Encoder;
        Encoders(RelativeEncoder encoder) {
          this.Encoder = encoder;
        }
        
    }

     public final static class Motors {
        
        // Drivetrain
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(12, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(11, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(17, MotorType.kBrushless);
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
        
        public static final CANSparkMax TURRET = new CANSparkMax(5, MotorType.kBrushless);// TODO: change back from 16 to 5
        
        public static final CANSparkMax FLAP = new CANSparkMax(2, MotorType.kBrushless);

        public static final CANSparkMax INDEX = new CANSparkMax(6, MotorType.kBrushless);
        public static final CANSparkMax INTAKE = new CANSparkMax(1, MotorType.kBrushless);
    }

    public final static class Joysticks {
        public static final Joystick JOYSTICK_LEFT = new Joystick(0);
        public static final Joystick JOYSTICK_RIGHT = new Joystick(1);
        public static final XboxController MANIP_CONTROLLER = new XboxController(2);
        public static final XboxController DRIVE_CONTROLLER = new XboxController(3);
    }

    public static final class XboxButtons {
        public static final JoystickButton LEFT_BUMPER = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kLeftBumper.value); // Extend
        public static final JoystickButton RIGHT_BUMPER = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kRightBumper.value); // Contract
        public static final JoystickButton BUTTON_Y = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kY.value); // Rotate Clockwise
        public static final JoystickButton BUTTON_A = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kA.value); // Rotate Counter Clockwise
        public static final JoystickButton BUTTON_X = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kX.value); // Rotate babies Clockwise
        public static final JoystickButton BUTTON_B = new JoystickButton(Joysticks.MANIP_CONTROLLER, XboxController.Button.kB.value); // Rotate babies Counter Clockwise
        public static final JoystickButton BACK = new JoystickButton(Joysticks.MANIP_CONTROLLER, 7);
        public static final JoystickButton START = new JoystickButton(Joysticks.MANIP_CONTROLLER, 8);
    }

    public static final class EncoderMaxes {
        public static final double LEFT_MAX = 117;
        public static final double RIGHT_MAX = 113;
        public static final double LEFT_ANGLE_MAX = 166;
        public static final double RIGHT_ANGLE_MAX = 155;
        public static final double LEFT_BABY_MAX = 0;
        public static final double RIGHT_BABY_MAX = 0;
    }
}