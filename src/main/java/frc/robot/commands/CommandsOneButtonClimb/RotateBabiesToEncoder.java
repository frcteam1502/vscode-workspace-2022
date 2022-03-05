package frc.robot.commands.CommandsOneButtonClimb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RotateBabiesToEncoder extends CommandBase {
  private Climber climber;
  //Value is used to decide where the motors will run to using encoders
  private double value;
  //Extend denotes whether the arms will extend or retract. A "true" extend denotes an extension while a "false" extend denotes a retract
  private boolean forward;

  public RotateBabiesToEncoder(Climber climber, double value, boolean forward) {
    addRequirements(climber);
    this.climber = climber;
    this.value = value;
    this.forward = forward;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(forward) {
      climber.RotateBabiesForwardsToEncoder(value);
      if(climber.GetEncoders("Left Angle").getPosition() >= value && climber.GetEncoders("Right Angle").getPosition() >= value) end(true);
    } else if(!forward) {
      climber.RotateBabiesBackwardsToEncoder(value);
      if(climber.GetEncoders("Left Angle").getPosition() <= value && climber.GetEncoders("Right Angle").getPosition() <= value) end(true);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
