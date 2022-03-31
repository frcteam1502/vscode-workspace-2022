package frc.robot.commands.Auto.ByTime;

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
      /** Deploy intake */
      new ParallelRaceGroup(
        new WaitCommand(1.5), 
        new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      ),

      /** Drive out of box and pickup 2nd ball */
      new ParallelRaceGroup(
        new WaitCommand(1.9),
        new DriveByTime(drive, .6, .6), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Aim the Turret */
      new ParallelCommandGroup(
        new AutoAim(turret, shooter),
        new WaitCommand(1.2)
      ),

      /** Shoot 2 balls */
      new ParallelRaceGroup(
        new WaitCommand(3),
        new StartEndCommand(shooter::runInAuto, shooter::stopInAuto, shooter)
      ),

      /** Drive to human player and pickup waiting ball */
      new ParallelRaceGroup(
        new WaitCommand(1),
        new DriveByTime(drive, .7, .6), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      new ParallelRaceGroup(
        new WaitCommand(3),
        new DriveByTime(drive, .65, .7), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Wait for human player to deposit 2nd ball */
      new WaitCommand(2),

      /** Get into shooting position */
      new ParallelRaceGroup(
        new WaitCommand(2),
        new DriveByTime(drive, -.7, -.8)
      ),

      /** Aim the Turret */
      new ParallelCommandGroup(
        new AutoAim(turret, shooter),
        new WaitCommand(1.2)
      ),

      /** Shoot 2 balls */
      new ParallelRaceGroup(
        new WaitCommand(3), 
        new StartEndCommand(shooter::runInAuto, shooter::stopInAuto, shooter)
      )
    );
  }
}