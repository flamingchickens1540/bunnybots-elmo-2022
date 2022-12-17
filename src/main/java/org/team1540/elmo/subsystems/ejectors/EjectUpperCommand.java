package org.team1540.elmo.subsystems.ejectors;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectUpperCommand extends SequentialCommandGroup {
    public EjectUpperCommand(Ejector.UpperEjector upperEjector) {
        addCommands(
            new InstantCommand(()->upperEjector.setSuction(true)),
            new InstantCommand(()->upperEjector.extend(2)),
                new WaitCommand(2),
                new InstantCommand(()->upperEjector.setSuction(false))
        );
    }
}
