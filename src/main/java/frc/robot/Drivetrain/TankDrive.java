package frc.robot.Drivetrain;

public class TankDrive extends DriveController {
    public double deadLock = 0.1;

    @Override
    public double getDriveLeft() {
        double percent = controller.getLeftY();
        return Math.abs(percent) < 0.1 ? 0.0 : percent;
    }

    @Override
    public double getDriveRight() {
        double percent = controller.getRightY();
        return Math.abs(percent) < 0.1 ? 0.0 : percent;
    }
}
