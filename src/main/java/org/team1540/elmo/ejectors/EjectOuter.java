package org.team1540.elmo.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectOuter extends SequentialCommandGroup {

    BunnyEjectors.OuterEjector outerEjector;

    public EjectOuter(BunnyEjectors.OuterEjector outerEjector) {
        this.outerEjector = outerEjector;
        addRequirements(outerEjector);

        addCommands(
                new InstantCommand(outerEjector::eject),
                new WaitDashboardVarCommand(outerEjector,"push",0.15), // wait for suction to fully unsucc. // TODO: Test without waiting
                new InstantCommand(()->outerEjector.setSuction(false))
        );
    }

}
