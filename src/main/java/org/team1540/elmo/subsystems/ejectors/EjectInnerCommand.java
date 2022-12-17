package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectInnerCommand extends SequentialCommandGroup {

    public EjectInnerCommand(Ejector.InnerEjector innerEjector) {
        addRequirements(innerEjector);

        addCommands(
                new InstantCommand(()->innerEjector.setSuction(true)),
                new InstantCommand(()->innerEjector.extend(1.6)),
                new WaitCommand(1.3), // wait for extendermabober to start extending
                new InstantCommand(()->innerEjector.setSuction(false))
        );
    }

}
