// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.Turret;
import frc.robot.PIDController;
import frc.robot.Constants.Joysticks;
//import frc.robot.subsystems.AngleFlap;
import frc.robot.Constants.XboxButtons;
import frc.robot.subsystems.EncoderValues;


public class MoveTurret extends CommandBase {
  private boolean on = false;
  private boolean off = false;
  private final Turret turret;
 // private final AngleFlap angleFlap;

  public MoveTurret(Turret tsubsystem/*, AngleFlap fsubsystem*/) {

    addRequirements(tsubsystem/*, fsubsystem*/);
    turret = tsubsystem;
    //angleFlap = fsubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotationController.reset();
  }

  private final PIDController rotationController = new PIDController(5e-3, 0, 0);
  private final PIDController centerController = new PIDController(5e-3, 0, 0);
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {  
    Limelight.Target m_limelight = Limelight.getTarget();

    double error = m_limelight.tx;
    double offset = rotationController.getCorrection(error);
    double Cerror = EncoderValues.turret;
    double Coffset = centerController.getCorrection(Cerror);

    if(XboxButtons.START.get()) {
      on = true;
    } else if(!XboxButtons.START.get()) {
      on = false;
    }
    if(Joysticks.MANIP_CONTROLLER.getPOV() == 180) {
      off = true;
    } else if(!XboxButtons.START.get()) {
      off = false;
    }

    
    SmartDashboard.putBoolean("on", on);
    if (off){
      turret.turnTurret(Coffset);
    }
    else if(on) {
      turret.turnTurret(offset);
    }
    else{
      runManually();
    }
    //angleFlap.Moveflap();
  }

  private void runManually() {
    turret.turnTurretmanual(); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
