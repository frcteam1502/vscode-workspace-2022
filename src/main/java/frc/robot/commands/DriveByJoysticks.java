package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.Drivetrain;

public class DriveByJoysticks extends CommandBase {
  private Drivetrain drivetrain;
  
  // Creates a new DriveByJoysticks. 
  public DriveByJoysticks(Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    drivetrain = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.move(
      Joysticks.rightJoystick.getX(),
      Joysticks.rightJoystick.getY(),
      Joysticks.rightJoystick.getZ());
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