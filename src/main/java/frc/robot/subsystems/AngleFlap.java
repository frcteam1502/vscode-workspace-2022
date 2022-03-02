// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import frc.robot.Limelight;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import frc.robot.commands.UpdateEncoders;

// public class AngleFlap extends SubsystemBase {
//   /** Creates a new AngleFlap. */

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
//   private double angle = -1;
//   private double flapmax = 50;
//   private double bound1 = -5;
//   private double bound2 = 5;
//   private double bound3 = 15;
  
//   private double Angle(){
//     if (!Limelight.target){// no target
//       angle = -1;
//     }
//     else if(Limelight.y <= bound1){//shoot zone 1
//       angle = Limelight.y - bound1;
//     }
//     else if(Limelight.y <= bound2 && Limelight.y > bound1){//shoot zone 2

//     }
//     else if(Limelight.y <= bound3 && Limelight.y > bound2){//shoot zone 3
      
//     }
//     return angle;
//   }

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
   



//   public void fResetEncoders() {
//     this.flap.getEncoder().setPosition(0);
//   }



// }
