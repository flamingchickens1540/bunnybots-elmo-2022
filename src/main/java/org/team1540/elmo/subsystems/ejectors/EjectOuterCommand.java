package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectOuterCommand extends SequentialCommandGroup {

    public EjectOuterCommand(Ejector.OuterEjector outerEjector) {
        addRequirements(outerEjector);

        addCommands(
                new InstantCommand(()->outerEjector.setSuction(false)),
                new WaitDashboardVarCommand(0.1,"unsuck",this), // wait for suction to fully unsucc.
                new InstantCommand(outerEjector::extend)
        );
    }

}
