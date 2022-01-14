package frc.robot;

import java.lang.Math;

public class MathChild {

    //Truncates the variable "value" to the decimal place described by "decimalPlaces"
    public static double truncate(double value, int decimalPlaces) {
        value -= value % (1 / Math.pow(10, decimalPlaces));
        return ((int)(value * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces);
    }

    //Shorthand assumes you want 2 decimals
    public static double truncate(double value) {
        return MathChild.truncate(value, 2);
    }

    //Refer to previous function
    public static float truncate(float value, int decimalPlaces) {
        value -= value % (1 / Math.pow(10, decimalPlaces));
        return (float) (((int) (value * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces));
    }

    //Shorthand assumes you want 2 decimals
    public static float truncate(float value) {
        return MathChild.truncate(value, 2);
    }
}