package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import org.photonvision.PhotonUtils;
import org.team1540.elmo.Constants;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class WaitUntilAprilTagIsWithinXMetersCommand extends WaitUntilCommand {

    public WaitUntilAprilTagIsWithinXMetersCommand(int apriltagId, double meters, ChickenPhotonCamera camera) {
        super(() ->
                camera.getTargetById(apriltagId) != null
             && PhotonUtils.calculateDistanceToTargetMeters(
                Constants.CAMERA_HEIGHT_METERS,
                Constants.TARGET_HEIGHT_METERS,
                Constants.CAMERA_PITCH_RADIANS,
                camera.getTargetById(apriltagId).getPitch()) < meters
        );

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ENDED!");
        super.end(interrupted);
    }
}
