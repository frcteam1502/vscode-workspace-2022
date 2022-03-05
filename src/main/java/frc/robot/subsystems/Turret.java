// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.MoveTurret;

public class Turret extends SubsystemBase {

  private static CANSparkMax turretMotor;
  
  public Turret(CANSparkMax turretMotor) {
    setDefaultCommand(new MoveTurret(this));
    this.turretMotor = turretMotor;
    //setDefaultCommand(new Shoot(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void turnTurret(){
    
    if ((Limelight.area > 0) && (Limelight.x < 5) && (Limelight.x > -5)){
      turretMotor.set(0);
    }

    if ((Joysticks.CONTROLLER.getAButton() == true)){
      turretMotor.set(0.1);
    } else if ((Joysticks.CONTROLLER.getBButton() == true)){
      turretMotor.set(-0.1);
    }

  }

}
