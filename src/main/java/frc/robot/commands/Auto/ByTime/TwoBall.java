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

public class TwoBall extends SequentialCommandGroup {
  public TwoBall(Climber climb, Drivetrain drive, Intake intake, Turret turret, Shooter shooter) {
    addCommands(
      /** Deploy intake */
      new ParallelRaceGroup(
        new WaitCommand(1.5),
        new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      ),

      /** Drive out and pickup ball */
      new ParallelRaceGroup(
        new WaitCommand(2.1), 
        new StartEndCommand(intake::moveIntake, intake::stopIntake, intake),
        new StartEndCommand(drive::Forward, drive::Stop, drive)
      ),

      /** Aim the Turret */
      new ParallelCommandGroup(
        new AutoAim(turret, shooter),
        new WaitCommand(1.4)
      ),

      /** Shoot 2 balls */
      new ParallelRaceGroup(
        new WaitCommand(4), 
        new StartEndCommand(shooter::runInAuto, shooter::stopInAuto, shooter)
      )
    );
  }
}
