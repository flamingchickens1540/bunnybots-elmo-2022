package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class WaitUntilCanSeeAprilTag extends WaitUntilCommand {

    public WaitUntilCanSeeAprilTag(int apriltagId, ChickenPhotonCamera camera) {
        super(()->camera.getTargetById(apriltagId)!=null);
    }
}
