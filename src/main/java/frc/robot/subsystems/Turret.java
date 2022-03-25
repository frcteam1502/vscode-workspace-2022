// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.RobotContainer;
import frc.robot.Constants.Joysticks;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret extends SubsystemBase {

  private CANSparkMax turretMotor;

  public Turret(CANSparkMax turretMotor) {
    this.turretMotor = turretMotor;
  }

  private DigitalInput rightlimitSwitch = new DigitalInput(0);
  private DigitalInput leftlimitSwitch = new DigitalInput(1);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  double m_s_seepd = 0.4;
  String breek = "no"; //this helps breek free form the hub/target on left and right side
  
  public boolean TurretCenterd;

  public void turretSet(double speed) {
    turretMotor.set(speed);
  }

  public void turnTurret(double m_t_seepd){
    TurretCenterd = false;
    RobotContainer.TurretCenterd = false;
    
    Limelight.Target m_limelight = Limelight.getTarget();

    if (m_limelight.tv == 1){

      if ( (m_limelight.tx >= -0.1) && (m_limelight.tx <= 0.1)){ // THIS THING CHANGED IT WAS 0.75
        turretMotor.set(0);
        breek = "no";
        TurretCenterd = true;
        RobotContainer.TurretCenterd = true;
      }

      else if (!rightlimitSwitch.get()){//trys to go to left 
        breek = "right";
        turretMotor.set(m_s_seepd);
      }

      else if (!leftlimitSwitch.get()){//trys to go to right
        breek = "left";
        turretMotor.set(-m_s_seepd);
      }

      else if (m_limelight.tx > 0.1){//change to right side of camera screen // THIS THING CHANGED IT WAS 0.75
        turretMotor.set(m_t_seepd);
        SmartDashboard.putString("Turret status", "turning right");
      }  

      else if (m_limelight.tx < -0.1){//change to left side of camera screen // THIS THING CHANGED IT WAS 0.75
        turretMotor.set(m_t_seepd);
        SmartDashboard.putString("Turret status", "turning left");
      }
    }
    else {
      
      if (!rightlimitSwitch.get()){//trys to go to left 
        breek = "right";
        turretMotor.set(m_s_seepd);
      }
      else if (!leftlimitSwitch.get()){//trys to go to right
        breek = "left";
        turretMotor.set(-m_s_seepd);
      }
      else if(breek == "no" || breek == "right"){
        turretMotor.set(m_s_seepd);
      }
      else{
        turretMotor.set(-m_s_seepd);
      }
    }

    SmartDashboard.putNumber("Turretmotor", turretMotor.get());
    SmartDashboard.putNumber("m_t_seepd", m_t_seepd);
    SmartDashboard.putBoolean("Turret Centerd", TurretCenterd);
  }

  public void turnTurretmanual(){

    if(Joysticks.MANIP_CONTROLLER.getRightTriggerAxis() > 0.8 && rightlimitSwitch.get()){
      turretMotor.set(-0.3);
    } else if(Joysticks.MANIP_CONTROLLER.getLeftTriggerAxis() > 0.8  && leftlimitSwitch.get()){
      turretMotor.set(0.3);
    } else {
      turretMotor.set(0);
    }
    
  }
  public void centerturret(double centerspeed, boolean notatcenter){
    if (notatcenter){
    turretMotor.set(centerspeed);
    }
    else{
      turretMotor.set(0);
    }
  }

  public double TurretClimbMax = -100;
  public static boolean climbMode = false;

  public void RotateToClimbMode(double speed) {
    if(EncoderValues.turret > TurretClimbMax) {
      turretMotor.set(speed);
      climbMode = true;
    }
    else {
      turretMotor.set(0);
      climbMode = false;
    }
  }
} 
