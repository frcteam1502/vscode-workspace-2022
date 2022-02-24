package frc.robot.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.PathFindingConstants.AutoConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class blue1 extends SequentialCommandGroup {
  public blue1(Drivetrain drive, Intake intake) {
      PathPlannerTrajectory Forward = PathPlanner.loadPath("Forward", AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared);
      PathPlannerTrajectory Backward = PathPlanner.loadPath("Backward", AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared);
      addCommands(
          new InstantCommand(() -> drive.resetOdometry(Forward.getInitialPose())),
          /*new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg1"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake)),
          new ParallelCommandGroup(drive.createTrajectoryCommand("blue1_seg2"), new StartEndCommand(intake::runIntakeForward, intake::stopIntake, intake)),
          drive.createTrajectoryCommand("blue1_seg3")*/
          drive.createTrajectoryCommand(Forward),
          drive.createTrajectoryCommand(Backward)
      );
  }
}
