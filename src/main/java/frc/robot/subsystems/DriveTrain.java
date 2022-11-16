package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    private TalonFX frontRight = new TalonFX(Constants.FrontRightId);
    private TalonFX frontLeft = new TalonFX(Constants.FrontLeftId);
    private TalonFX backRight = new TalonFX(Constants.BackRightId);
    private TalonFX backLeft = new TalonFX(Constants.BackLeftId);

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
