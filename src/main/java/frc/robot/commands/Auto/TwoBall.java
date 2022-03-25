package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class TwoBall extends SequentialCommandGroup {
  public TwoBall(Climber climb, Drivetrain drive, Intake intake, Turret turret, Shooter shooter) {
    addCommands(
      /**
       * Plan of action:
       * Re-test with arms disconnected to test what is the actual problem
       * ie: the Command Groups or the motor power
       */
      /** Deploy intake */
      new ParallelCommandGroup(
        new WaitCommand(1.2),
        new StartEndCommand(climb::RotateArmsForwards, climb::StopArmsRotate, climb)
      )//,

      // /** Drive out and pickup ball */
      // new ParallelCommandGroup(
      //   new WaitCommand(1.5), 
      //   new StartEndCommand(intake::moveIntake, intake::stopIntake, intake),
      //   new StartEndCommand(drive::Forward, drive::Stop, drive)
      // ),

      // /** Aim the Turret */
      // new AutoAim(turret, shooter),

      // /** Shoot 2 balls */
      // new ParallelCommandGroup(
      //   new WaitCommand(4), 
      //   new StartEndCommand(shooter::indexBall, shooter::indexBallStop, shooter),
      //   new StartEndCommand(shooter::moveHoodAutomatically, shooter::stopAngle, shooter)
      // ),

      // /** Drive forward out of Tarmac */
      // new ParallelCommandGroup(
      //   new WaitCommand(.5),
      //   new StartEndCommand(drive::Forward, drive::Stop, drive)
      // )
    );
  }
}
