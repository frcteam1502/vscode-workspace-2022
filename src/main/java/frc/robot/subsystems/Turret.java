// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.MoveTurret;

public class Turret extends SubsystemBase {

  private CANSparkMax turretMotor;
  DigitalInput rightlimitSwitch = new DigitalInput(0);
  DigitalInput leftlimitSwitch = new DigitalInput(1);
  boolean movingRight = true;
  
  public Turret(CANSparkMax turretMotor) {
    setDefaultCommand(new MoveTurret(this));
    this.turretMotor = turretMotor;
    //setDefaultCommand(new Shoot(this));
  }

  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    
    
    public void turnTurret(){

      if(Joysticks.CONTROLLER.getRightTriggerAxis() > 0.8){
        turretMotor.set(-0.3);
      } else if(Joysticks.CONTROLLER.getLeftTriggerAxis() > 0.8){
        turretMotor.set(0.3);
      } else {
        turretMotor.set(0);
      }
      
    }

   
   


}

