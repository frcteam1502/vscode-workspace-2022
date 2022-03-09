/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// robbie & treevore code

package frc.robot;

import java.util.ArrayList;

public class PIDController {
  public double P;
  public double I;
  public double D;
  ArrayList<Point> points = new ArrayList<Point>();

  private class Point {
    double millis;
    double err;

    Point(double millis, double err) {
      this.millis = millis;
      this.err = err;
    }
  }

  public PIDController(final double p, final double i, final double d) {
    P = p;
    I = i;
    D = d;
  }

  public void reset() {
    points.clear();
  }

  public void input(double err) {
    if (points.size() > 0 && ((err > 0 && latest().err < 0) || (err < 0 && latest().err > 0))) {
      points.clear();
    }
    points.add(new Point(System.currentTimeMillis(), err));
  }

  @Deprecated
  public double getCorrection() {
    return getP() + getI() + getD();
  }

  /**
   * Inputs the error and then returns the correction.
   */
  public double getCorrection(double err) {
    input(err);
    return getCorrection();
  }

  private double getP() {
    return P * latest().err;
  }

  private double getI() {
    if (points.size() < 2) {
      return 0;
    }
    double area = 0;
    for (int i = 1; i < points.size(); i++) {
      double rectWidth = points.get(i).millis - points.get(i - 1).millis;
      double rectHeight = (points.get(i - 1).err + points.get(i).err) / 2;
      area += rectHeight * rectWidth;
    }
    return I * area;
  }

  public double getD() {
    return D * getRateOfChange();
  }

  private double getRateOfChange() {
    if (points.size() < 2) {
      return 0;
    }
    double derr = latest().err - previous().err;
    double dt = latest().millis - previous().millis;
    return derr / dt;
  }

  /**
   * Checks if the controller is stable--in bounds and not about to go out of the
   * threshold within timeThresholdMs.
   * 
   * @param threshold       The threshold to check for stability
   * @param timeThresholdMs The time used for the prediction of whether or not the
   *                        system will go out of threshold bounds within this
   *                        time
   * @return
   */
  public boolean isStable(double threshold, double timeThresholdMs) {
    if (points.size() < 2)
      return false;
    double predictedErrorValue = latest().err + getRateOfChange() * timeThresholdMs;
    return Math.abs(latest().err) < threshold && Math.abs(predictedErrorValue) < threshold;
  }

  private Point previous() {
    try {
      return points.get(points.size() - 2);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  private Point latest() {
    try {
      return points.get(points.size() - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }
}