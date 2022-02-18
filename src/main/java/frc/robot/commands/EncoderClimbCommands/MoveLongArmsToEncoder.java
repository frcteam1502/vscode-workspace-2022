package frc.robot.commands.EncoderClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class MoveLongArmsToEncoder extends CommandBase {
  private Climber climber;
  //Value is used to decide where the motors will run to using encoders
  private double value;
  //Extend denotes whether the arms will extend or retract. A "true" extend denotes an extension while a "false" extend denotes a retract
  private boolean extend;

  public MoveLongArmsToEncoder(Climber climber, double value, boolean extend) {
    addRequirements(climber);
    this.climber = climber;
    this.value = value;
    this.extend = extend;
  }

  @Override
  public void execute() {
    if(extend) {
      climber.ExtendArmsToEncoder(value);
      if(climber.GetEncoders("Left Extender").getPosition() >= value && climber.GetEncoders("Left Extender").getPosition() >= value) end(true);
    }
    else if(!extend) {
      climber.RetractArmsToEncoder(value);
      if(climber.GetEncoders("Left Extender").getPosition() <= value && climber.GetEncoders("Left Extender").getPosition() <= value) end(true);
    } 
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {return false;}
}