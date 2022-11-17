package org.team1540.elmo.lowersuction;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class EjectLower extends ParallelCommandGroup {

    LowerSuction lowerSuction;

    public EjectLower(LowerSuction lowerSuction) {
        addRequirements(lowerSuction);
        this.lowerSuction = lowerSuction;

        addCommands(
            new EjectLowerInner(lowerSuction),
            new EjectLowerOuter(lowerSuction)
        );
    }
}
