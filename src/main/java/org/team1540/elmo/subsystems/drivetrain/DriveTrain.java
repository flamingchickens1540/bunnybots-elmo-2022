package org.team1540.elmo.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class DriveTrain extends SubsystemBase {
    private TalonFX frontRight = new TalonFX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
    private TalonFX frontLeft = new TalonFX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
    private TalonFX backRight = new TalonFX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
    private TalonFX backLeft = new TalonFX(Constants.LEFT_BACK_MOTOR_CAN_ID);

    public DriveTrain() {
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
    }

    public void stop() {
        setPercent(0, 0);
    }

    public void setPercent(double rightPercent, double leftPercent) {
        frontRight.set(ControlMode.PercentOutput, rightPercent);
        frontLeft.set(ControlMode.PercentOutput, leftPercent);
    }
}