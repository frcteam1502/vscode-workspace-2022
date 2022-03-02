// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.Constants;
//import frc.robot.Constants.Joysticks;s
import edu.wpi.first.wpilibj.DigitalInput;

public class Turret extends SubsystemBase {
  private static Limelight m_limelight = Constants.limelight;

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

  double m_seepd = 0.05;
  String breek = "no"; //this helps breek free form the hub/target on left and right side
  
  public void turnTurret(){
    
    if (!rightlimitSwitch.get()){//trys to go to left 
      breek = "right";
      turretMotor.set(m_seepd);
    }
    else if (!leftlimitSwitch.get()){//trys to go to right
      breek = "left";
      turretMotor.set(-m_seepd);
    }
    else if (m_limelight.target){
      if ( (m_limelight.x >= 3) && (m_limelight.x <= -3)){
        turretMotor.set(0);
        breek = "no";
      }
      else if (m_limelight.x > 3)//change to right side of camera screen
      {
        if(breek == "left"){
          turretMotor.set(m_seepd);
        }
        else{
          turretMotor.set(-m_seepd);
        }
      }  
      else if (m_limelight.x < -3){//change to left side of camera screen
        if(breek == "right"){
          turretMotor.set(-m_seepd);
        } 
        else{
          turretMotor.set(m_seepd);
        }
      }
    }
    
  }
} 
