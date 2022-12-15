package org.team1540.elmo.utils;

import edu.wpi.first.networktables.NetworkTableInstance;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.Optional;

public class ChickenPhotonCamera extends PhotonCamera {

    public ChickenPhotonCamera(String cameraName) {
        super(cameraName);
    }

    public PhotonTrackedTarget getTargetById(int id) {
        PhotonPipelineResult result = getLatestResult();
        Optional<PhotonTrackedTarget> target = result.getTargets().stream().filter((targ)->targ.getFiducialId() == id).findFirst();
        return target.orElseGet(()->null);
    }
}
