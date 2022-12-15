package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class DriveToAprilTagCommand extends CommandBase {

    private int tagId;
    private double driveSpeedPercent;
    private double turnSpeedMaxPercent;
    private DriveTrain driveTrain;
    private ChickenPhotonCamera camera;

    public DriveToAprilTagCommand(int tagId, double driveSpeedPercent, double turnSpeedFactor, DriveTrain driveTrain, ChickenPhotonCamera camera) {
        this.tagId = tagId;
        this.driveSpeedPercent = driveSpeedPercent;
        this.turnSpeedMaxPercent = turnSpeedFactor;
        this.driveTrain = driveTrain;
        this.camera = camera;
    }

    @Override
    public void execute() {
        PhotonTrackedTarget target = camera.getTargetById(tagId);
        if(target==null) return;

        final double yaw = target.getYaw(); // in degrees
        double turnSpeedPercent = yaw/180*turnSpeedMaxPercent;

        // this function handles ramping and shit
        driveTrain.setSpeedAndSpinRamped(driveSpeedPercent, turnSpeedPercent);
    }
}
