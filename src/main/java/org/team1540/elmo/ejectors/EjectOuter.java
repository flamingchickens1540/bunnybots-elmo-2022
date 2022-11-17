package org.team1540.elmo.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class EjectOuter extends SequentialCommandGroup {

    BunnyEjectors.OuterEjector outerEjector;

    public EjectOuter(BunnyEjectors.OuterEjector outerEjector) {
        this.outerEjector = outerEjector;
        addRequirements(outerEjector);

        addCommands(
                new InstantCommand(outerEjector::eject),
                new WaitCommand(0.15), // wait for outer to start moving. // TODO: Test without waiting
                new InstantCommand(()->outerEjector.setSuction(false))
        );
    }

}
