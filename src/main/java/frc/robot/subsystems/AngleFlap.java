// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import frc.robot.Limelight;

// import java.lang.reflect.Array;
// import java.util.ArrayList;
// import java.util.Arrays;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import frc.robot.commands.UpdateEncoders;


// public class AngleFlap extends SubsystemBase {
//   /** Creates a new AngleFlap. */
//   private static Limelight m_limelight = Constants.limelight;
//   private CANSparkMax flap;

//   public AngleFlap(CANSparkMax flap) {
//     //setDefaultCommand(new Shoot(this));
//     this.flap = flap;
//   }
  

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }

//   //TODO: test shoots at diffrent limelight y levels

//   private double flapmax = 50;



//   // public double Shot(double hood, double spled){
//   //   if (!m_limelight.target){// no target
//   //     return ;
//   //   }
//   //   return spled;
    

    
//   // }

//   public void Moveflap(){

//     if (Angle() < 0){
//       flap.set(0);
//     }
//     else if ((Angle() >= (EncoderValues.flap + 5)) && (Angle() <=  (EncoderValues.flap - 5))){
//       flap.set(0);
//     }
//     else if (Angle() >= (EncoderValues.flap + 5)){
//       flap.set(0.05);
//     }
//     else if (Angle() <=  (EncoderValues.flap - 5)){
//       flap.set(-0.05);
//     }

//     }
   



//   private int Angle() {
//     return 0;
//   }


//   public void fResetEncoders() {
//     this.flap.getEncoder().setPosition(0);
//   }



// }
