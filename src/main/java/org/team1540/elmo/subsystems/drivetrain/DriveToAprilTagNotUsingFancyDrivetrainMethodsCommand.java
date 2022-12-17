package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class DriveToAprilTagNotUsingFancyDrivetrainMethodsCommand extends CommandBase {

    private int tagId;
    private double driveSpeedPercent;
    private double turnSpeedMaxPercent;
    private DriveTrain driveTrain;
    private ChickenPhotonCamera camera;

    public DriveToAprilTagNotUsingFancyDrivetrainMethodsCommand(int tagId, double driveSpeedPercent, double turnSpeedMaxPercent, DriveTrain driveTrain, ChickenPhotonCamera camera) {
        this.tagId = tagId;
        this.driveSpeedPercent = driveSpeedPercent;
        this.turnSpeedMaxPercent = turnSpeedMaxPercent;
        this.driveTrain = driveTrain;
        this.camera = camera;
    }

    @Override
    public void execute() {
        PhotonTrackedTarget target = camera.getTargetById(tagId);
        if(target==null) return;

        final double yaw = target.getYaw(); // in degrees
        double turnSpeedPercent = yaw/180*turnSpeedMaxPercent;

        double leftOutput = driveSpeedPercent;
        double rightOutput = driveSpeedPercent;
        leftOutput += turnSpeedPercent/2d;
        rightOutput -= turnSpeedPercent/2d;

        driveTrain.setPercent(leftOutput, rightOutput);
    }
}
