package frc.robot.commands.Auto.ByEncoder;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class TestAuto extends SequentialCommandGroup {
  public TestAuto(Drivetrain drive, Intake intake) {
    addCommands(
      new InstantCommand(intake::moveIntake, intake)//,

      //new WaitCommand(3),

      //new DriveStraightByEncoder(drive, 2),

      //new TurnByGyro(drive, -45)

      
    );
  }
}