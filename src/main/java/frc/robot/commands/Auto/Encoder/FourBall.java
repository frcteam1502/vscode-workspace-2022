package frc.robot.commands.Auto.Encoder;


import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Auto.AutoCenter;
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
        new WaitCommand(1.3), 
        new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      ),

      /** Drive out of box and pickup 2nd ball */
      new ParallelRaceGroup(
        new DriveStraightByEncoder(drive, 1.5),
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Aim the Turret */
      new ParallelRaceGroup(
        new WaitCommand(1.3),
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake),
        new AutoCenter(turret)
      ),

      /** Shoot 2 balls */
      new ParallelRaceGroup(
        new StartEndCommand(shooter::shootInAuto, shooter::stopInAuto, shooter),
        new WaitCommand(3)
      ),

      /** Drive to human player and pickup waiting ball */
      new ParallelRaceGroup(
        new DriveStraightByEncoder(drive, 4.64), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Wait for human player to deposit 2nd ball */
      new ParallelRaceGroup(
        new WaitCommand(1.6),
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake)
      ),

      /** Get into shooting position */
      new DriveStraightByEncoder(drive, -4.1),

      /** Aim the Turret */
      new ParallelRaceGroup(
        new AutoCenter(turret),
        new WaitCommand(1.3)
      ),

      /** Shoot 2 balls */
      new ParallelRaceGroup(
        new WaitCommand(3), 
        new StartEndCommand(shooter::shootInAuto, shooter::stopInAuto, shooter)
      )
    );
  }
}