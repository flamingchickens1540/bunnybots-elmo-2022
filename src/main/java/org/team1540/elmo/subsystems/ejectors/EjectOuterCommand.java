package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectOuterCommand extends SequentialCommandGroup {

    public EjectOuterCommand(Ejectors.OuterEjector outerEjector) {
        addRequirements(outerEjector);

        addCommands(
                new InstantCommand(()->outerEjector.setSuction(false)),
                new WaitDashboardVarCommand(outerEjector,"unsuck",0.1), // wait for suction to fully unsucc. // TODO: Test without waiting
                new InstantCommand(outerEjector::extend)
        );
    }

}
