package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj.XboxController;

public abstract class DriveController {
    public XboxController controller = new XboxController(0);

    public abstract double getDriveLeft();

    public abstract double getDriveRight();
}
