package org.team1540.elmo.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class DriveTrain extends SubsystemBase {
    private TalonSRX frontRight = new TalonSRX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
    private TalonSRX frontLeft = new TalonSRX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
    private TalonSRX backRight = new TalonSRX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
    private TalonSRX backLeft = new TalonSRX(Constants.LEFT_BACK_MOTOR_CAN_ID);

    public DriveTrain() {
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
        frontLeft.setInverted(true);
        backLeft.setInverted(true);
        frontRight.setInverted(false);
        backRight.setInverted(false);

        /*
        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        */
    }

    public void stop() {
        setPercent(0, 0);
    }

    public void setBrake(boolean brake) {
        NeutralMode neutralMode = brake ? NeutralMode.Brake : NeutralMode.Coast;
        frontRight.setNeutralMode(neutralMode);
        frontLeft.setNeutralMode(neutralMode);
    }

    public void setPercent(double leftPercent, double rightPercent) {
        frontLeft.set(ControlMode.PercentOutput, leftPercent);
        frontRight.set(ControlMode.PercentOutput, rightPercent);
    }
}