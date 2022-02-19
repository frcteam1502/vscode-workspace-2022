package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.EncoderMaxes;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Climber.EncoderValues;

public class RotateBabies extends CommandBase {
  private final Climber climber;
  private final double button;
  private boolean usingEncoders;

  public RotateBabies(Climber climber, double button, boolean usingEncoders) {
    this.climber = climber;
    this.button = button;
    this.usingEncoders = usingEncoders;
    addRequirements(climber);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (!usingEncoders) return;
    
    SmartDashboard.putBoolean("Left Baby < max", EncoderValues.leftBaby < EncoderMaxes.BABY_MAX);
    SmartDashboard.putBoolean("Right Baby < max", EncoderValues.rightBaby < EncoderMaxes.BABY_MAX);

    if (button == 5) climber.RotateBabiesForwardsToEncoder(EncoderMaxes.BABY_MAX);
    else if (button == 6) climber.RotateBabiesBackwardsToEncoder(0);
    else climber.StopBabies();
  }

  @Override
  public void end(boolean interrupted) {
    climber.StopBabies();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}