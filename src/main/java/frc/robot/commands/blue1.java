package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class blue1 extends SequentialCommandGroup {
  public blue1(Drivetrain drive, Intake intake) {
        addCommands(
            new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg1"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake)),
            new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg2"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake)),
            drive.createTrajectoryCommand("blue1_seg3")
        );
  }
}
