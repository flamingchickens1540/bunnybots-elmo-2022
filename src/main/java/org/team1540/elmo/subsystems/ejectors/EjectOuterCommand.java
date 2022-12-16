package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectOuterCommand extends SequentialCommandGroup {

    public EjectOuterCommand(Ejector.OuterEjector outerEjector) {
        addRequirements(outerEjector);

        addCommands(
                new InstantCommand(()->outerEjector.setSuction(false)),
//                new WaitDashboardVarCommand(0.1,"unsuck",this), // wait for suction to fully unsucc.
                new WaitCommand(0.3),
                new InstantCommand(()->outerEjector.extend(0.5)),
                new WaitCommand(0.5)
        );
    }

}
