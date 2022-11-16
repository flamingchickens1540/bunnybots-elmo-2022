package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
    XboxController controller;

    DriveTrain driveTrain;

    double deadlock = .1;

    public TankDrive(DriveTrain driveTrain, XboxController controller) {
        addRequirements(driveTrain);
        this.controller = controller;
        this.driveTrain = driveTrain;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }

    public void execute() {
        double rightPercent =  controller.getRightY();
        double leftPercent =  controller.getLeftY();
        driveTrain.setPercent(
            Math.abs(rightPercent) > deadlock ? rightPercent : 0, 
            Math.abs(leftPercent) > deadlock ? leftPercent : 0
        );
    }
}
