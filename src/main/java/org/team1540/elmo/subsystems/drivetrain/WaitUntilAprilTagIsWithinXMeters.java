package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import org.photonvision.PhotonUtils;
import org.team1540.elmo.Constants;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class WaitUntilAprilTagIsWithinXMeters extends WaitUntilCommand {

    public WaitUntilAprilTagIsWithinXMeters(int apriltagId, double meters, ChickenPhotonCamera camera) {
        super(() ->
                camera.getTargetById(apriltagId) != null
             && PhotonUtils.calculateDistanceToTargetMeters(
                Constants.CAMERA_HEIGHT_METERS,
                Constants.TARGET_HEIGHT_METERS,
                Constants.CAMERA_PITCH_RADIANS,
                camera.getTargetById(apriltagId).getPitch()
        ) < meters);
    }
}
