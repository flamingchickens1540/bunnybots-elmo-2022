package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class ResetEjectorAndWaitForeverCommand extends SequentialCommandGroup {
    public ResetEjectorAndWaitForeverCommand(Ejector ejector, boolean sucking) {
        addRequirements(ejector);
        addCommands(
                parallel(
                        new InstantCommand(()->ejector.setExtended(false)),
                        new InstantCommand(()->ejector.setSuction(sucking))
                ),
                new WaitDashboardVarCommand(0.5,"retract",this),
                new RunCommand(()->{})
        );
    }
    public ResetEjectorAndWaitForeverCommand(Ejector ejector) {
        this(ejector,false);
    }
}
