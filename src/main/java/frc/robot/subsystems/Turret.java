package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret extends SubsystemBase {

  private CANSparkMax turretMotor;

  public Turret(CANSparkMax turretMotor) {
    this.turretMotor = turretMotor;
  }

  DigitalInput rightlimitSwitch = new DigitalInput(0);
  DigitalInput leftlimitSwitch = new DigitalInput(1);

  @Override
  public void periodic() {}

  double m_s_seepd = 0.2;
  String breek = "no"; //this helps breek free form the hub/target on left and right side
  

  public boolean turnTurret(double m_t_seepd){
    Limelight.Target m_limelight = Limelight.getTarget();

    if (m_limelight.tv == 1){

      if ( (m_limelight.tx >= -0.75) && (m_limelight.tx <= 0.75)){
        turretMotor.set(0);
        breek = "no";
        SmartDashboard.putString("Turret status", "centered");
        return true;
      }

      else if (!rightlimitSwitch.get()){//trys to go to left 
        breek = "right";
        turretMotor.set(m_s_seepd);
      }

      else if (!leftlimitSwitch.get()){//trys to go to right
        breek = "left";
        turretMotor.set(-m_s_seepd);
      }

      else if (m_limelight.tx > 0.75){//change to right side of camera screen
        turretMotor.set(m_t_seepd);
        SmartDashboard.putString("Turret status", "turning right");
      }  

      else if (m_limelight.tx < -0.75){//change to left side of camera screen
        turretMotor.set(-m_t_seepd);
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
      else if(breek == "no"){
        turretMotor.set(m_s_seepd);
      }
    }

    SmartDashboard.putNumber("Turretmotor", turretMotor.get());
    SmartDashboard.putNumber("m_t_seepd", m_t_seepd);

    return false;
  }

  
} 