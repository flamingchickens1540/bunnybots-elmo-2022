package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectInnerCommand extends SequentialCommandGroup {

    public EjectInnerCommand(Ejectors.InnerEjector innerEjector) {
        addRequirements(innerEjector);

        addCommands(
                new InstantCommand(innerEjector::extend),
                new WaitDashboardVarCommand(0.15, "extend", this), // wait for extendermabober to start extending
                new InstantCommand(()->innerEjector.setSuction(false))
        );
    }

}
