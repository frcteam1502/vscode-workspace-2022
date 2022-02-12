package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class blue1 extends SequentialCommandGroup {
  public blue1(Drivetrain drive, Intake intake) {
    /*PathPlannerTrajectory trajectory1 = PathPlanner.loadPath("5ball", 2.0, 3.0); 
        addCommands(
            new InstantCommand(() -> m_swerve.dt.setKnownPose(trajectory1.getInitialPose())),

            m_swerve.dt.createCommandForTrajectory(trajectory1, m_swerve)
        );*/
  }
}
