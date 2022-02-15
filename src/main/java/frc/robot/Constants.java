// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
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
    public final static class Motors {
        // Drivetrain
        public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(4, MotorType.kBrushless); // ID = 1
        public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(9, MotorType.kBrushless);
        public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(5, MotorType.kBrushless);
        
        // Climber
        public static final CANSparkMax ARM_ANGLE = new CANSparkMax(8, MotorType.kBrushless);
        public static final CANSparkMax LEFT_ARM_EXTENDER = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_EXTENDER = new CANSparkMax(1, MotorType.kBrushless);
        /** 
         *  Left Baby reverses while B button is held
         *  Right Baby reverses on an alternating toggle between X button and B button
         *  NOTE: These motors weren't tested at the same time
         *  Fix the thing. Dance monkey dance!
        */
        public static final CANSparkMax LEFT_BABY = new CANSparkMax(21, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_BABY = new CANSparkMax(14, MotorType.kBrushless); // 14
    }

    public final static class Joysticks {
        public static final Joystick JOYSTICK = new Joystick(0);
        public static final XboxController CONTROLLER = new XboxController(1);
    }

    public static final class Buttons {
        public static final JoystickButton J_BUTTON_ONE = new JoystickButton(Joysticks.JOYSTICK, 8);
        public static final JoystickButton J_BUTTON_TWO = new JoystickButton(Joysticks.JOYSTICK, 7);
        public static final JoystickButton J_BUTTON_THREE = new JoystickButton(Joysticks.JOYSTICK, 10);
        public static final JoystickButton J_BUTTON_FOUR = new JoystickButton(Joysticks.JOYSTICK, 9);
        public static final JoystickButton J_BUTTON_FIVE = new JoystickButton(Joysticks.JOYSTICK, 3);
        public static final JoystickButton J_BUTTON_SIX = new JoystickButton(Joysticks.JOYSTICK, 11);
        
        // public static final JoystickButton XBOX_BUTTON_ONE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kLeftBumper.value);
        
        public static final JoystickButton X_START_BUTTON = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kStart.value);

        public static final JoystickButton X_BUTTON_ONE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kLeftBumper.value); // Extend
        public static final JoystickButton X_BUTTON_TWO = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kRightBumper.value); // Contract
        public static final JoystickButton X_BUTTON_THREE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kY.value); // Rotate
        public static final JoystickButton X_BUTTON_FOUR = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kA.value); // Rotate
        public static final JoystickButton X_BUTTON_FIVE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kX.value); // Rotate babies
        public static final JoystickButton X_BUTTON_SIX = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kB.value); // Rotate babies
    }
}