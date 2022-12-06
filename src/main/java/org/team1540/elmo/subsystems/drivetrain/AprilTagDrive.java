package org.team1540.elmo.subsystems.drivetrain;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class AprilTagDrive {
    //new PhotonCamera("photonvision")
    PhotonCamera photonCamera;



    AprilTagDrive(PhotonCamera photonCamera) {
        this.photonCamera = photonCamera;
    }

    List<PhotonTrackedTarget> getApriltags() {
        PhotonPipelineResult result = photonCamera.getLatestResult();
        return result.getTargets();
    }
}
