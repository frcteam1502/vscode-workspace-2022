// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunIntake;


public class Intake extends SubsystemBase {
  private CANSparkMax intakeL;
  private double ySpeedD;
  private CANSparkMax deployL;
  private CANSparkMax indexL;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  /** Creates a new Intake. */
  public Intake(CANSparkMax intakeP, CANSparkMax deployP, CANSparkMax indexP) {
    setDefaultCommand(new RunIntake(this));
    intakeL = intakeP;
    deployL = deployP;
    indexL = indexP;
  }

  public void runIntakeForward(double ySpeed){
    ySpeedD = ySpeed/5;
    ySpeed = MathUtil.clamp(ySpeedD, -1.0, 1.0);
    
    intakeL.set(ySpeedD);
  }

  public void runIntakeBackward(double ySpeed){
    ySpeedD = ySpeed/-5;
    ySpeed = MathUtil.clamp(ySpeedD, -1.0, 1.0);

    intakeL.set(ySpeedD);
  }

  public void stopIntake(){
    intakeL.set(0);
  }

  public void deployIntake(boolean deployed, boolean retracted){
    if (deployed == false){
//      deployL.set(0.25);
    }
    else if (retracted == false){
//      deployL.set(-0.25);
    }
    else{
//      deployL.set(0);
    }
  }

  public void runIndexForward(){
    indexL.set(0.2);
  }

  public void runIndexBackwards(){
    indexL.set(0.2);
  }

  public String getIndexColor(){
    Color detectedColor = m_colorSensor.getColor();
    if (detectedColor.red > detectedColor.blue){
      return("red");
    }
    else {
      return("blue");
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


  



