package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class WaitUntilCanSeeAprilTagCommand extends WaitUntilCommand {

    public WaitUntilCanSeeAprilTagCommand(int apriltagId, ChickenPhotonCamera camera) {
        super(()->camera.getTargetById(apriltagId)!=null);
    }
}
