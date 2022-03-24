package frc.robot.commands.Auto.ByEncoder;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class FourBall extends SequentialCommandGroup {
  public FourBall(Drivetrain drive, Intake intake, Shooter shooter, Climber climb) {
    addCommands(
      /** Deploy intake */
      new ParallelCommandGroup(
        new WaitCommand(1.8), 
        new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      ),

      /** Drive out of box and pickup 2nd ball */
      new ParallelCommandGroup(
        new DriveStraightByEncoder(drive, 1.2), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Shoot 2nd ball */
      new ParallelCommandGroup(
        new WaitCommand(4), 
        new StartEndCommand(shooter::indexBall, shooter::indexBallStop, shooter)
      ),

      /** Drive and turn to human player */
      new TurnByGyro(drive, -21),
      new DriveStraightByEncoder(drive, 3.75),
      new TurnByGyro(drive, 40),

      /** Drive to human player and pickup waiting ball */
      new ParallelCommandGroup(
        new DriveStraightByEncoder(drive, .52), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Wait for human player to deposit 2nd ball */
      new WaitCommand(3),

      /** Get into shooting position */
      new TurnByGyro(drive, -40),
      new DriveStraightByEncoder(drive, -3.1),//Backup away from human player towards hub

      /** Shoot 2 balls */
      new ParallelCommandGroup(
        new WaitCommand(4), 
        new StartEndCommand(shooter::indexBall, shooter::indexBallStop, shooter)
      )
    );
  }
}
