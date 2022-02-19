// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  Intake subsystemL;
  boolean intakedeployed = false;
  boolean intakeretracted = false;
  public RunIntake(Intake subsystemP) {
    addRequirements(subsystemP);
    subsystemL = subsystemP;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Constants.CONTROLLER.getAButton() == true) {
      if (Constants.RIGHT_JOYSTICK.getY() < 0.3 && Constants.RIGHT_JOYSTICK.getY() > 0) {
        subsystemL.runIntakeForward(0.3);
      }
      else {
        subsystemL.runIntakeForward(Constants.RIGHT_JOYSTICK.getY());
      }
      
    }
    else if(Constants.CONTROLLER.getBButton() == true) {
      if (Constants.RIGHT_JOYSTICK.getY() > -0.3 && Constants.RIGHT_JOYSTICK.getY() < 0) {
        subsystemL.runIntakeBackward(-0.3);}
      else{
        subsystemL.runIntakeBackward(Constants.RIGHT_JOYSTICK.getY());
      }
    }
    else{
      subsystemL.stopIntake();
    }
    /* 
      Will run the climber arm to deploy intake based on the deployed and retracted variables (timing will be done in auto)
      Auto sets deployed to false for a while than sets it to true, then does the same with retraction.
    */
    if (!intakedeployed) {
      subsystemL.deployIntake(intakedeployed, intakeretracted);
    }
    else if (!intakeretracted) {
      subsystemL.deployIntake(intakedeployed, intakeretracted);
    }

}

  @Override
  public void end(boolean interrupted) {
    subsystemL.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
