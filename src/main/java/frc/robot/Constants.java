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
        // public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(10, MotorType.kBrushless);
        // public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(10, MotorType.kBrushless);
        // public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(10, MotorType.kBrushless);
        // public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(10, MotorType.kBrushless);
        
        // Climber
        public static final CANSparkMax LEFT_ARM_ANGLE = new CANSparkMax(1, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_ANGLE = new CANSparkMax(5, MotorType.kBrushless);
        public static final CANSparkMax LEFT_ARM_EXTENDER = new CANSparkMax(2, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_ARM_EXTENDER = new CANSparkMax(3, MotorType.kBrushless);
        public static final CANSparkMax LEFT_BABY = new CANSparkMax(4, MotorType.kBrushless);
        public static final CANSparkMax RIGHT_BABY = new CANSparkMax(6, MotorType.kBrushless);
        /** 
         *  Left Baby reverses while B button is held
         *  Right Baby reverses on an alternating toggle between X button and B button
         *  NOTE: These motors weren't tested at the same time
         *  TODO: Fix the thing. Dance monkey dance!
        */
    }

    public final static class Joysticks {
        public static final XboxController CONTROLLER = new XboxController(0);
    }

    public static final class Buttons {        
        public static final JoystickButton X_START_BUTTON = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kStart.value);

        public static final JoystickButton X_BUTTON_ONE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kLeftBumper.value); // Extend
        public static final JoystickButton X_BUTTON_TWO = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kRightBumper.value); // Contract
        public static final JoystickButton X_BUTTON_THREE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kY.value); // Rotate Clockwise
        public static final JoystickButton X_BUTTON_FOUR = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kA.value); // Rotate Counter Clockwise
        public static final JoystickButton X_BUTTON_FIVE = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kX.value); // Rotate babies Clockwise
        public static final JoystickButton X_BUTTON_SIX = new JoystickButton(Joysticks.CONTROLLER, XboxController.Button.kB.value); // Rotate babies Counter Clockwise
    }
}