package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.Constants;
import org.team1540.elmo.subsystems.ejectors.EjectInnerCommand;
import org.team1540.elmo.subsystems.ejectors.EjectOuterCommand;
import org.team1540.elmo.subsystems.ejectors.EjectUpperCommand;
import org.team1540.elmo.subsystems.ejectors.Ejector;
import org.team1540.elmo.utils.ChickenPhotonCamera;

public class Auto extends SequentialCommandGroup {
    public Auto(DriveTrain driveTrain, Ejector.UpperEjector upperEjector, Ejector.OuterEjector outerEjector, Ejector.InnerEjector innerEjector, ChickenPhotonCamera camera) {
        super(
                // drive forward until can see april tag
                deadline(
                        new WaitUntilCanSeeAprilTagCommand(Constants.TOWER_APRILTAG_ID,camera),
                        new DriveForwardRampedForeverCommand(-0.8,driveTrain)
                ),
                // drive fast (w/ ramping) pointing to tag until close to april tag
                deadline(
                        new WaitUntilAprilTagIsWithinXMetersCommand(Constants.TOWER_APRILTAG_ID,0.4,camera),
                        new DriveToAprilTagPIDCommand(Constants.TOWER_APRILTAG_ID,0.1,driveTrain,camera)
                ),
                // drive forward slowly for last few seconds
                deadline(
                        new WaitCommand(1),
                        new DriveForwardRampedForeverCommand(0.1,driveTrain)
                ),
                // stop drivetrain (right now no ramping, possibly add ramping?)
                new InstantCommand(driveTrain::stop,driveTrain),
                // eject all the tubes!
                parallel(
                        new EjectUpperCommand(upperEjector),
                        new EjectInnerCommand(innerEjector),
                        new EjectOuterCommand(outerEjector)
                ),
                // back up from the tower for a few seconds to give better position for driver to take over
                deadline(
                        new DriveForwardRampedForeverCommand(-0.3,driveTrain),
                        new WaitCommand(1.5)
                ),
                new InstantCommand(driveTrain::stopRamped,driveTrain)
        );
    }
}
