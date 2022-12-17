package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.team1540.elmo.utils.SignedSlewRateLimiter;

/**
 * Standard drive, left stick controls left side of drivetrain, right stick controls right side.
 */
public class BobaTankDrive extends CommandBase {

    private final DriveTrain drivetrain;
    private final XboxController controller;
    private final double deadzone = 0.15;

    private final SignedSlewRateLimiter speedRateLimiter = new SignedSlewRateLimiter(2,2);
    private final SignedSlewRateLimiter spinRateLimiter = new SignedSlewRateLimiter(3,3);

    public BobaTankDrive(DriveTrain drivetrain, XboxController controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double left = Math.abs(controller.getLeftY()) > deadzone ? controller.getLeftY() : 0;
        double right = Math.abs(controller.getRightY()) > deadzone ? controller.getRightY() : 0;
//        double forward = Math.abs(controller.getRightTriggerAxis()) > deadzone ? Math.pow(controller.getRightTriggerAxis(), 3) : 0;
//        double backward = Math.abs(controller.getLeftTriggerAxis()) > deadzone ? Math.pow(controller.getLeftTriggerAxis(), 3) : 0;
//        double multiplier = 0.5 - ((0.3 * elevator.getRotations())/ElevatorConstants.ELEVATOR_ROTS_TO_TOP);
//        speedRateLimiter.setAccelerationLimit(2-(elevatorPercentHeight*1)); // at bottom = 2-0%*1, at top: 2-100%*1

        double speed = (left + right) / 2;
        double spin = left - speed; // this changes sign lol
        ///
        speed = Math.pow(speed,2) * Math.signum(speed);

        spin = Math.pow(spin,2) * Math.signum(spin);
        spin = spin/2;
        ///
        double rateLimitedSpeed = speedRateLimiter.calculate(speed);
        double rateLimitedSpin = spinRateLimiter.calculate(spin);

//        double rateLimitedLeft = speed + spin;
//        double rateLimitedRight = speed - spin;
        double rateLimitedLeft = rateLimitedSpeed + rateLimitedSpin;
        double rateLimitedRight = rateLimitedSpeed - rateLimitedSpin;

//        drivetrain.setPercent(multiplier*(-1*left + forward - backward), multiplier*(-1*right + forward - backward));
        drivetrain.setPercent(rateLimitedLeft,rateLimitedRight);
    }

    @Override
    public void end(boolean isInterrupted) {
        drivetrain.stop();
    }
}
