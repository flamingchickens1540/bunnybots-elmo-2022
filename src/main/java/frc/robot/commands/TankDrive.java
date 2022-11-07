package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
    XboxController controller = new XboxController(0);

    DriveTrain driveTrain;

    public TankDrive(DriveTrain driveTrain) {
        addRequirements(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    public void initialize() {
        driveTrain.arrète();
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.arrète();
    }

    public void execute() {
        driveTrain.setPercent(controller.getRightY(), controller.getLeftY());
    }
}
