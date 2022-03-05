package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Motors.Joysticks;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  Intake intake;
  
  public RunIntake(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
   if(Joysticks.JOYSTICK_LEFT.getTrigger()) intake.moveIntake();
   else intake.stopIntake();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}