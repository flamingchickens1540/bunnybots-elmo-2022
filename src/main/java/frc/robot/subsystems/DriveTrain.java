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

    private double deadLock = .1;

    public DriveTrain() {
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }

    public void arrète() {
        setPercent(0, 0);
    }

    public void setPercent(double rightPercent, double leftPercent) {
        boolean coastRight =  Math.abs(rightPercent) > deadLock;
        boolean coastLeft =  Math.abs(leftPercent) > deadLock;
        frontRight.set(ControlMode.PercentOutput, coastRight ? rightPercent : 0);
        frontLeft.set(ControlMode.PercentOutput, coastLeft ? leftPercent : 0);
        frontRight.setNeutralMode(coastRight ? NeutralMode.Coast : NeutralMode.Brake);
        frontLeft.setNeutralMode(coastLeft ? NeutralMode.Coast : NeutralMode.Brake);
    }
}
