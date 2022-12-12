package org.team1540.elmo.subsystems.drivetrain;

import java.util.List;

import com.pathplanner.lib.PathPlanner;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnTowardsTag extends CommandBase {
    private final DriveTrain driveTrain;
    private final PhotonCamera camera;
    private int targetId;

    public TurnTowardsTag(DriveTrain driveTrain, PhotonCamera camera) {
        this.driveTrain = driveTrain;
        this.camera = camera;
    }

    @Override
    public void initialize() {
        PhotonPipelineResult results = camera.getLatestResult();
        if(!results.hasTargets())
            return;
        
        List<PhotonTrackedTarget> targets = results.getTargets();
        double currentTargetSize = 0;
        int currentTargetId = 0;

        for(PhotonTrackedTarget target : targets) {
            double area = target.getArea();
            if(area > currentTargetSize) {
                currentTargetSize = area;
                currentTargetId = target.getFiducialId();
            }
        }

        targetId = currentTargetId;
    }
}
