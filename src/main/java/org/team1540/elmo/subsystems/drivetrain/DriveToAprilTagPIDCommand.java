package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.team1540.elmo.Constants;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class DriveToAprilTagPIDCommand extends CommandBase {

    DriveTrain driveTrain;
    int tagId;
    ChickenPhotonCamera camera;

    PIDController pid = new PIDController(0.5,0,0.05);

    public DriveToAprilTagPIDCommand(int tagId, double targetDistance, DriveTrain driveTrain, ChickenPhotonCamera camera) {
        this.tagId = tagId;
        this.driveTrain = driveTrain;
        this.camera = camera;

        pid.setSetpoint(targetDistance);

        SmartDashboard.putData(pid);
    }

    public DriveToAprilTagPIDCommand(int tagId, DriveTrain driveTrain, ChickenPhotonCamera camera) {
        this(tagId,0,driveTrain,camera);
    }

        @Override
    public void execute() {
        PhotonTrackedTarget target = camera.getTargetById(tagId);
        if(target==null) {
            System.out.println(getName() + ": NO TARGET FOUND with id " + tagId);
            return;
        }
//        var measurement = target.getBestCameraToTarget();
        double distance = PhotonUtils.calculateDistanceToTargetMeters(
                Constants.CAMERA_HEIGHT_METERS,
                Constants.TARGET_HEIGHT_METERS,
                Constants.CAMERA_PITCH_RADIANS,
                target.getPitch()
        );
        double pitch = target.getPitch();

        double speed = -pid.calculate(distance);
        double spin = pitch/180;

        driveTrain.setSpeedAndSpin(speed,spin);
    }
}
