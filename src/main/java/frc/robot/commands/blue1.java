package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class blue1 extends SequentialCommandGroup {
  private Command runIntake;
  public blue1(Drivetrain drive, Intake intake) {
    runIntake =  new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake);
        addCommands(
            new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg1"), runIntake),
            drive.createTrajectoryCommand("blue1_seg2"),
            new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg3"), runIntake),
            drive.createTrajectoryCommand("blue1_seg4")
        );
  }
}
