package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class ResetEjectorCommand extends SequentialCommandGroup {
    public ResetEjectorCommand(Ejector ejector) {
        addRequirements(ejector);
        addCommands(
                parallel(
                        new InstantCommand(()->ejector.setExtended(false)),
                        new InstantCommand(()->ejector.setSuction(false))
                        // new InstantCommand(()->ejector.setSuction(true))
                ),
                new WaitDashboardVarCommand(0.5,"retract",this)
        );
    }
}
