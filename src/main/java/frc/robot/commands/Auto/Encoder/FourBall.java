package frc.robot.commands.Auto.Encoder;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Auto.AutoAim;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class FourBall extends SequentialCommandGroup {
  public FourBall(Drivetrain drive, Intake intake, Shooter shooter, Climber climb, Turret turret) {
    addCommands(
      // /** Deploy intake */
      // new ParallelRaceGroup(
      //   new WaitCommand(1.5), 
      //   new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      // ),

      // /** Drive out of box and pickup 2nd ball */
      // new ParallelRaceGroup(
      //   new DriveStraightByEncoder(drive, 1.5),
      //   new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      // ),

      // /** Aim the Turret */
      // new ParallelRaceGroup(
      //   new AutoAim(turret, shooter),
      //   new WaitCommand(1.4)
      // ),

      // /** Shoot 2 balls */
      // new ParallelRaceGroup(
      //   new WaitCommand(4), 
      //   new StartEndCommand(shooter::runInAuto, shooter::stopInAuto, shooter)
      // ),

      //TODO: Up until here is solid
      // /** Drive and turn to human player */
      // new TurnByGyro(drive, -21),
      new DriveStraightByEncoder(drive, 3.85)//,
      // new TurnByGyro(drive, 40),

      // /** Drive to human player and pickup waiting ball */
      // new ParallelRaceGroup(
      //   new DriveStraightByEncoder(drive, .52), 
      //   new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      // ),

      // /** Wait for human player to deposit 2nd ball */
      // new WaitCommand(3),

      // /** Get into shooting position */
      // new TurnByGyro(drive, -40),
      // new DriveStraightByEncoder(drive, -3.1),//Backup away from human player towards hub

      // /** Aim the Turret */
      // new ParallelRaceGroup(
      //   new AutoAim(turret, shooter),
      //   new WaitCommand(1.4)
      // ),

      // /** Shoot 2 balls */
      // new ParallelRaceGroup(
      //   new WaitCommand(4), 
      //   new StartEndCommand(shooter::runInAuto, shooter::stopInAuto, shooter)
      // )
    );
  }
}