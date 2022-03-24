package frc.robot.commands.Auto.ByEncoder;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class TestAuto extends SequentialCommandGroup {
  public TestAuto(Drivetrain drive, Intake intake) {
    //Drive in square
    addCommands(
      //new InstantCommand(intake::moveIntake, intake),

      new DriveStraightByEncoder(drive, 1),

      new TurnByGyro(drive, 90),

      new WaitCommand(.1),

      new DriveStraightByEncoder(drive, 1),

      new TurnByGyro(drive, 90),

      new WaitCommand(.1),

      new DriveStraightByEncoder(drive, 1),

      new TurnByGyro(drive, 90),

      new WaitCommand(.1),

      new DriveStraightByEncoder(drive, 1),

      new TurnByGyro(drive, 90)
    );
  }
}