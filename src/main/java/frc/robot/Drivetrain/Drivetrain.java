package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Drivetrain extends CommandBase {
    public DriveController controller;

    public void setDriveController(DriveController driveController) {
        this.controller = driveController;
    }

    @Override
    public void execute() {
        updateDriveLeft();
        updateDriveRight();
    }

    public void updateDriveLeft() {
        
    }

    public void updateDriveRight() {
        
    }
}
