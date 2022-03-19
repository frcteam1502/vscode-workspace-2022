package frc.robot.commands.Auto.ByEncoder;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class FourBall extends SequentialCommandGroup {
  public FourBall(Drivetrain drive, Intake intake) {
    addCommands(
      new InstantCommand(intake::moveIntake, intake),//start intake
      new DriveStraightByEncoder(drive, 2),//drive out of box
      //shoot 2 ball
      new DriveStraightByEncoder(drive, 4),//move close to human player
      new TurnByGyro(drive, -45),//turn towards human player
      new DriveStraightByEncoder(drive, .5),//drive to human player and pickup waiting ball
      new WaitCommand(3),//wait for human player to deposit 2nd ball
      new DriveStraightByEncoder(drive, -4)//backup away from human player towards hub
      //shoot
    );
  }
}
