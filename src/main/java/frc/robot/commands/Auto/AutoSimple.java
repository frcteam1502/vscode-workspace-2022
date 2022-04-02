package frc.robot.commands.Auto;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class AutoSimple extends CommandBase {
  private AHRS NavX = new AHRS();
  
  private boolean IAlreadRan = false;

  public AutoSimple(){}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(IAlreadRan) return;
    
    NavX.reset();

    SmartDashboard.putNumber("Angle", NavX.getAngle());

    // while(NavX.getAngle() < 85) {
    //   leftDrive(0.2);
    //   rightDrive(-0.2);
    // }

    leftDrive(0);
    rightDrive(0);
    
    IAlreadRan = true;
  }

  public void leftDrive(double power) {
    Constants.Motors.DRIVE_FRONT_LEFT.set(power);
    Constants.Motors.DRIVE_BACK_LEFT.set(power);
  }

  public void rightDrive(double power) {
    Constants.Motors.DRIVE_FRONT_RIGHT.set(-power);
    Constants.Motors.DRIVE_BACK_RIGHT.set(-power);
  }

  @Override
  public void end(boolean interrupted) {
    leftDrive(0);
    rightDrive(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
