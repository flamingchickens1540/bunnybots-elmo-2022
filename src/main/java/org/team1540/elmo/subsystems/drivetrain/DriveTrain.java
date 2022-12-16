package org.team1540.elmo.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;
import org.team1540.elmo.utils.SignedSlewRateLimiter;

public class DriveTrain extends SubsystemBase {
    private TalonSRX frontRight = new TalonSRX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
    private TalonSRX frontLeft = new TalonSRX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
    private TalonSRX backRight = new TalonSRX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
    private TalonSRX backLeft = new TalonSRX(Constants.LEFT_BACK_MOTOR_CAN_ID);
    private AHRS navx = new AHRS(Port.kMXP);

    public DriveTrain() {
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
        frontLeft.setInverted(true);
        backLeft.setInverted(true);
        frontRight.setInverted(false);
        backRight.setInverted(false);

        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putNumber(getName()+"/navx-angle", getAngle());

        rampingPeriodic();
    }

    public void stop() {
        setPercent(0, 0);
    }
    public void stopRamped() {
        setPercentRamped(0, 0);
    }

    ///////////// RAMPING ////////////
    private enum DriveRampingMode {
        NO_RAMP_SS_SET, // no ramping but spin speed ratelimiters are reset
        NO_RAMP_D_SET, // no ramping but differential ratelimiters are reset
        SPEED_SPIN,
        DIFFERENTIAL,
    }
    private DriveRampingMode driveRampingMode = DriveRampingMode.DIFFERENTIAL;

    private void rampingPeriodic() {
        if(driveRampingMode.equals(DriveRampingMode.SPEED_SPIN)) {
            setSpeedSpinRaw(speedRateLimiter.calculate(speedRampedSetpoint),spinRateLimiter.calculate(spinRampedSetpoint));
            SmartDashboard.putNumber("DriveTrain/SpinCalced",spinRateLimiter.calculate(spinRampedSetpoint));
            SmartDashboard.putNumber("ArcadeDrive/SpinSetPoint",spinRampedSetpoint);
        }
        else if(driveRampingMode.equals(DriveRampingMode.DIFFERENTIAL)) {
            frontLeft.set(ControlMode.PercentOutput, leftRateLimiter.calculate(leftRampedSetpoint));
            frontRight.set(ControlMode.PercentOutput, rightRateLimiter.calculate(rightRampedSetpoint));
        }
    }

    ///////////////// Differential Drive Mode /////////////////
    private void setPercentRaw(double leftPercent, double rightPercent) {
        frontLeft.set(ControlMode.PercentOutput, leftPercent);
        frontRight.set(ControlMode.PercentOutput, rightPercent);
    }
    public double getLeftMotorPercent() { return frontLeft.getMotorOutputPercent(); }
    public double getRightMotorPercent() { return frontRight.getMotorOutputPercent(); }

    public void setPercent(double leftPercent, double rightPercent) {
        // set
        setPercentRaw(leftPercent, rightPercent);

        // ramping
        driveRampingMode = DriveRampingMode.NO_RAMP_D_SET;
        leftRateLimiter.reset(leftPercent);
        rightRateLimiter.reset(rightPercent);
    }

    ///////////////// Differential Drive Mode Ramping /////////////////
    private final double defaultAccLimit = 5;
    private final double defaultDecLimit = 3;
    private double leftRampedSetpoint = 0;
    private double rightRampedSetpoint = 0;
    private SignedSlewRateLimiter leftRateLimiter = new SignedSlewRateLimiter(
            SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
            SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
    );
    private SignedSlewRateLimiter rightRateLimiter = new SignedSlewRateLimiter(
            SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
            SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
    );

    public void setPercentRamped(double leftPercent, double rightPercent) {
        // if setting right after using speed-spin ramped mode, translate units
        if(driveRampingMode.equals(DriveRampingMode.SPEED_SPIN) || driveRampingMode.equals(DriveRampingMode.NO_RAMP_SS_SET)) {
            double left = speedRateLimiter.getPrevVal() + spinRateLimiter.getPrevVal();
            double right = speedRateLimiter.getPrevVal() - spinRateLimiter.getPrevVal();
            leftRateLimiter.reset(left);
            rightRateLimiter.reset(right);
        }

        leftRampedSetpoint = leftPercent;
        rightRampedSetpoint = rightPercent;
        rampingPeriodic();
    }
    public void setAccelerationRateLimit(double rateLimit) {
        leftRateLimiter.setAccelerationLimit(rateLimit);
        rightRateLimiter.setAccelerationLimit(rateLimit);
    }
    public void setDecelerationRateLimit(double rateLimit) {
        leftRateLimiter.setDecelerationLimit(rateLimit);
        rightRateLimiter.setDecelerationLimit(rateLimit);
    }

    ///////////////// Speed/Rotate Mode /////////////////
    private static Vector2d preprocessSpeedSpin(double speedPercent, double spinPercent) {
        // decrease drive percent as much as possible (to zero if needed) if max percent exceeds 1
        double maxScalarPercent = Math.abs(speedPercent)+Math.abs(spinPercent);
        speedPercent -= Math.signum(speedPercent) * Math.max(0,maxScalarPercent-1);

        // cap percents at 1 by multiplying by a scale factor if method above didn't full work
        maxScalarPercent = Math.abs(speedPercent)+Math.abs(spinPercent);
        if(maxScalarPercent > 1) {
            double coefficient = 1 / maxScalarPercent;
            speedPercent *= coefficient;
            spinPercent *= coefficient;
        }

        // x: drive percent, y: spinPercent
        return new Vector2d(speedPercent,spinPercent);
    }

    private void setSpeedSpinRaw(double speedPercent, double spinPercent) {
        double leftPercent, rightPercent;
        leftPercent = rightPercent = speedPercent;
        leftPercent += spinPercent;
        rightPercent -= spinPercent;
        setPercentRaw(leftPercent,rightPercent);
    }

    public void setSpeedAndSpin(double speedPercent, double spinPercent) {
        var processed = preprocessSpeedSpin(speedPercent,spinPercent);
        setSpeedSpinRaw(processed.x,processed.y);

        // ramping
        driveRampingMode = DriveRampingMode.NO_RAMP_SS_SET;
        speedRateLimiter.reset(speedPercent);
        spinRateLimiter.reset(spinPercent);
    }

    ///////////////// Speed/Rotate Mode Ramping /////////////////
    private final double defaultSpinAccLimit = 3.5;
    private final double defaultSpinDecLimit = 6;
    private final double defaultSpeedAccLimit = 5;
    private final double defaultSpeedDecLimit = 3;
    private double speedRampedSetpoint = 0;
    private double spinRampedSetpoint = 0;
    private SignedSlewRateLimiter speedRateLimiter = new SignedSlewRateLimiter(defaultSpeedAccLimit,defaultSpeedDecLimit
//            SmartDashboard.getNumber(getName() + "/SpeedAccLimit", defaultSpeedAccLimit),
//            SmartDashboard.getNumber(getName() + "/SpeedDecLimit", defaultSpeedDecLimit)
    );
    private SignedSlewRateLimiter spinRateLimiter = new SignedSlewRateLimiter(defaultSpinAccLimit,defaultSpinDecLimit
//            SmartDashboard.getNumber(getName() + "/SpinAccLimit", defaultSpinAccLimit),
//            SmartDashboard.getNumber(getName() + "/SpinDecLimit", defaultSpinDecLimit)
    );

    public void setSpeedAndSpinRamped(double speedPercent, double spinPercent) {
        // if setting right after using differential ramped mode, translate units
        if(driveRampingMode.equals(DriveRampingMode.DIFFERENTIAL) || driveRampingMode.equals(DriveRampingMode.NO_RAMP_D_SET)) {
            double speed = (leftRateLimiter.getPrevVal() + rightRateLimiter.getPrevVal()) / 2;
            double spin = (leftRateLimiter.getPrevVal() - speed);
            speedRateLimiter.reset(speed);
            spinRateLimiter.reset(spin);
        }

        var processed = preprocessSpeedSpin(speedPercent,spinPercent);
        speedRampedSetpoint = processed.x;
        spinRampedSetpoint = processed.y;

        driveRampingMode = DriveRampingMode.SPEED_SPIN;
        rampingPeriodic();
    }

    /////// SENSORS //////
    public double getAngle() {
        return navx.getAngle();
    }
    public void resetGyro() {
        navx.reset();
    }
}