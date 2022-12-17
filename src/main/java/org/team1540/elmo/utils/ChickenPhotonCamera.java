package org.team1540.elmo.utils;

import edu.wpi.first.networktables.NetworkTableInstance;
import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.Optional;

public class ChickenPhotonCamera extends PhotonCamera {

    public ChickenPhotonCamera(String cameraName) {
        super(cameraName);
        this.setLED(VisionLEDMode.kBlink);
        this.setDriverMode(true);
    }

    public PhotonTrackedTarget getTargetById(int id) {
//        this.takeInputSnapshot();
        PhotonPipelineResult result = getLatestResult();
        var allTags = result.getTargets();
        System.out.println(allTags.toString());

        Optional<PhotonTrackedTarget> target = result.getTargets().stream().filter((targ)->targ.getFiducialId() == id).findFirst();
        return target.orElseGet(()->null);
//        return getLatestResult().getBestTarget(); // testing
    }
}
