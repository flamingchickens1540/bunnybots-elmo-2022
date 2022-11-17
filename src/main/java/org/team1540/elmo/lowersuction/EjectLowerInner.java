package org.team1540.elmo.lowersuction;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class EjectLowerInner extends SequentialCommandGroup {

    LowerSuction lowerSuction;

    public EjectLowerInner(LowerSuction lowerSuction) {
        this.lowerSuction = lowerSuction;
        addRequirements(lowerSuction);

        addCommands(
                new InstantCommand(()->lowerSuction.setSuctionInner(false)),
                new WaitCommand(0.1), // wait for suction to fully unsucc. // TODO: Test without waiting
                new InstantCommand(()->lowerSuction.ejectInner())
        );
    }

}
