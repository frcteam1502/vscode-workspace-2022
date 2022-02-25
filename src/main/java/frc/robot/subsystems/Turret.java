// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.MoveTurret;
import edu.wpi.first.wpilibj.DigitalInput;

public class Turret extends SubsystemBase {

  private CANSparkMax turretMotor;

  public Turret(CANSparkMax turretMotor) {
    this.turretMotor = turretMotor;
  }

  DigitalInput rightlimitSwitch = new DigitalInput(0);
  DigitalInput leftlimitSwitch = new DigitalInput(1);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void turnTurret(){
    String breek = "no"; //this helps breek free form the hub/target on left and right side
    if (rightlimitSwitch.get()){//trys to go to left 
      breek = "right";
      turretMotor.set(.1);
    }
    else if (leftlimitSwitch.get()){//trys to go to right
      breek = "left";//might be useful lader
      turretMotor.set(-.1);
    }
    else{
      if ( (Limelight.x >= 3) && (Limelight.x <= -3)){
        turretMotor.set(0);
        breek = "no";
      }
      else if (Limelight.x > 3)//change to right side of camera screen
      {
        if(breek == "left"){
        turretMotor.set(0.1);
        }
        else{
          turretMotor.set(-0.1);
        }
      }  
      else if (Limelight.x < -3)//change to left side of camera screen
      {
        if(breek == "right"){
        turretMotor.set(-0.1);
      } 
      else{
        turretMotor.set(0.1);
      }
    }
    /*if (( (Limelight.x < 3) && (Limelight.x > -3)){
      turretMotor.set(0);
     }
     else if (Limelight.x > 0 ){
      turretMotor.set(0.1);
     } 
     else if (Limelight.x < 0){
      turretMotor.set(-0.1);
     }*/
  

  }
} 
