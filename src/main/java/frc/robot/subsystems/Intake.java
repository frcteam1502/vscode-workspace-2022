package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunIntake;

public class Intake extends SubsystemBase {
  private CANSparkMax intake;

  public Intake(CANSparkMax intake) {
    this.intake = intake;
    setDefaultCommand(new RunIntake(this) );
    
  }

  @Override
  public void periodic() {}

  public void moveIntake(){ 
    intake.set(0.5);
  }
  public void stopIntake(){
    intake.set(0);
  }
}
