package org.team1540.elmo.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import org.team1540.elmo.subsystems.ejectors.Ejectors;

public class WaitDashboardVarCommand extends WaitVariableCommand {
    public WaitDashboardVarCommand(Command command, String processName, Double defaultValue) {
        super(()->SmartDashboard.getNumber(command.getName() + "/" + processName + "-wait",defaultValue));
        SmartDashboard.putNumber(command.getName() + "/" + processName + "-wait",defaultValue);
    }
}
