package org.team1540.elmo.subsystems.drivetrain;

import org.team1540.elmo.utils.SignedSlewRateLimiter;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
    private XboxController controller;

    DriveTrain driveTrain;

    private double deadlock = .1;
    private double defaultAccLimit = 5;
    private double defaultDecLimit = 3;
    private SignedSlewRateLimiter leftRateLimiter = new SignedSlewRateLimiter(
        SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
        SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
    );
    private SignedSlewRateLimiter rightRateLimiter = new SignedSlewRateLimiter(
        SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
        SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
    );

    public TankDrive(DriveTrain driveTrain, XboxController controller) {
        addRequirements(driveTrain);
        this.controller = controller;
        this.driveTrain = driveTrain;

        SmartDashboard.putNumber(getName() + "/AccelerateLimit",SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit));
        SmartDashboard.putNumber(getName() + "/DecelerateLimit",SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit));
    }

    @Override
    public void initialize() {
        leftRateLimiter = new SignedSlewRateLimiter(
            SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
            SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
        );
        rightRateLimiter = new SignedSlewRateLimiter(
            SmartDashboard.getNumber(getName() + "/AccelerateLimit", defaultAccLimit),
            SmartDashboard.getNumber(getName() + "/DecelerateLimit", defaultDecLimit)
        );
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }

    public void execute() {
        double leftJoystickPercent = controller.getLeftY();
        double rightJoystickPercent = controller.getRightY();
        double leftPercent = Math.abs(leftJoystickPercent) > deadlock ? leftJoystickPercent : 0;
        double rightPercent = Math.abs(rightJoystickPercent) > deadlock ? rightJoystickPercent : 0;
        // double totalPercent = Math.abs(rightPercent) + Math.abs(leftPercent);
        // // todo: limit ramping to total percent = 1. After that, it should ramp linearly
        // double totalOutputPercent = rateLimiter.calculate(totalPercent);
        // double leftOutputPercent = totalOutputPercent * leftPercent / totalPercent; // proportion of left to total
        // double rightOutputPercent = totalOutputPercent * rightPercent / totalPercent; // proportion of 

//        double leftOutPercent = leftRateLimiter.calculate(leftPercent);
//        double rightOutPercent = rightRateLimiter.calculate(rightPercent);
//        driveTrain.setPercentRamped(leftOutPercent,rightOutPercent);
//        SmartDashboard.putNumber(getName()+"/leftPercent", leftOutPercent);
//        SmartDashboard.putNumber(getName()+"/rightPercent", rightOutPercent);

        driveTrain.setPercentRamped(leftPercent,rightPercent);

    }
}