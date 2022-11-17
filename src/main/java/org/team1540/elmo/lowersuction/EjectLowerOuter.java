package org.team1540.elmo.lowersuction;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class EjectLowerOuter extends SequentialCommandGroup {

    LowerSuction lowerSuction;

    public EjectLowerOuter(LowerSuction lowerSuction) {
        this.lowerSuction = lowerSuction;
        addRequirements(lowerSuction);

        addCommands(
                new InstantCommand(lowerSuction::ejectOuter),
                new WaitCommand(0.15), // wait for outer to start moving. // TODO: Test without waiting
                new InstantCommand(()->lowerSuction.setSuctionOuter(false))
        );
    }

}
