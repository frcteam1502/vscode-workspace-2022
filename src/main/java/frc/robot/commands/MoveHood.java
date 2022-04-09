package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.AngleFlap;

@Deprecated
public class MoveHood extends CommandBase {
  private AngleFlap angleFlap;

  public MoveHood(AngleFlap angleFlap) {
    this.angleFlap = angleFlap;
    addRequirements(angleFlap);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    if(Joysticks.MANIP_CONTROLLER.getYButton()) {
      angleFlap.angleUp();
    } else if (Joysticks.MANIP_CONTROLLER.getAButton()){
      angleFlap.angleDown();
    } else {
      angleFlap.stopAngle();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
