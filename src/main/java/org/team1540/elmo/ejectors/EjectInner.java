package org.team1540.elmo.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectInner extends SequentialCommandGroup {

    BunnyEjectors.InnerEjector innerEjector;

    public EjectInner(BunnyEjectors.InnerEjector innerEjector) {
        this.innerEjector = innerEjector;
        addRequirements(innerEjector);

        addCommands(
                new InstantCommand(()->innerEjector.setSuction(false)),
                new WaitDashboardVarCommand(innerEjector,"waitToUnsuck",0.1), // wait for suction to fully unsucc. // TODO: Test without waiting
                new InstantCommand(innerEjector::eject)
        );
    }

}
