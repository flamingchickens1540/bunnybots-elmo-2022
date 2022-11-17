package org.team1540.elmo.ejectors;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class EjectLower extends ParallelCommandGroup {

    public EjectLower(BunnyEjectors.InnerEjector innerEjector, BunnyEjectors.OuterEjector outerEjector) {
        addRequirements(innerEjector, outerEjector);

        addCommands(
            new EjectInner(innerEjector),
            new EjectOuter(outerEjector)
        );
    }
}
