// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public final class Constants {
    public static final CANSparkMax INTAKE = new CANSparkMax(5, MotorType.kBrushless);
    public static final XboxController CONTROLLER = new XboxController(4);

    //Motors
    public static final int DRIVE_FRONT_LEFT = 4;
    public static final int DRIVE_BACK_LEFT = 3;
    public static final int DRIVE_FRONT_RIGHT = 6;
    public static final int DRIVE_BACK_RIGHT = 5;
    
    //Joysticks
    public static final int RIGHT_JOYSTICK = 1;
    public static final int LEFT_JOYSTICK = 2;

    public final static class Joysticks {
        public static final Joystick rightJoystick = new Joystick(RIGHT_JOYSTICK);
        public static final Joystick leftJoystick = new Joystick(LEFT_JOYSTICK);
    }

    public static final class Cameras {
        // public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();
    }
}