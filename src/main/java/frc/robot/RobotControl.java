package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Drivetrain.DriveController;
import frc.robot.Drivetrain.Drivetrain;

public class RobotControl {
    public final Drivetrain drivetrain = new Drivetrain();

    public void bindDrivetrain(DriveController driveController) {
        drivetrain.setDriveController(driveController);
    }

    public void bindTrigger(BooleanSupplier trigger, CommandBase execute) {
        new Trigger(trigger).whileActiveOnce(execute);
    }

    public void bindTriggerWhile(BooleanSupplier trigger, CommandBase execute) {
        new Trigger(trigger).whenActive(execute);
    }
}
