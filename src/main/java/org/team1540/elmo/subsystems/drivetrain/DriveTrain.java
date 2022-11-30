//package org.team1540.elmo.subsystems.drivetrain;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import org.team1540.elmo.Constants;
//
//public class DriveTrain extends SubsystemBase {
//
//    TalonFX motorRightFront = new TalonFX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
//    TalonFX motorRightBack = new TalonFX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
//    TalonFX motorLeftFront = new TalonFX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
//    TalonFX motorLeftBack = new TalonFX(Constants.LEFT_BACK_MOTOR_CAN_ID);
//
//    public DriveTrain() {
//        motorLeftFront.setInverted(true);
//        motorLeftBack.setInverted(true);
//        motorLeftFront.setNeutralMode(NeutralMode.Brake);
//        motorRightFront.setNeutralMode(NeutralMode.Brake);
//
//        motorRightBack.follow(motorRightFront);
//        motorLeftBack.follow(motorLeftFront);
//    }
//
//    public void setPercent(double left, double right) {
//        motorLeftFront.set(ControlMode.PercentOutput, left);
//        motorRightFront.set(ControlMode.PercentOutput, right);
//    }
//
//    public void setMagicMotion(double left, double right) {
////        motorLeftFront.getPIDConfigs(new TalonFXPIDSetConfiguration());
//        motorLeftFront.set(ControlMode.MotionMagic, left);
//        motorRightFront.set(ControlMode.MotionMagic, right);
//    }
//
//}
