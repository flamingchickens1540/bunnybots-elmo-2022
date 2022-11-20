package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class EjectAllLowerCommand extends ParallelCommandGroup {

    public EjectAllLowerCommand(Ejectors.InnerEjector innerEjector, Ejectors.OuterEjector outerEjector) {
        addRequirements(innerEjector,outerEjector); // test this

        addCommands(
            new EjectInnerCommand(innerEjector),
            new EjectOuterCommand(outerEjector)
        );
    }
}
