package org.team1540.elmo.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class WaitDashboardVarCommand extends WaitVariableCommand {
    public WaitDashboardVarCommand(Double defaultValue, String processName, Command command) {
        super(()->SmartDashboard.getNumber(command.getName() + "/" + processName + "-wait",defaultValue));
        SmartDashboard.putNumber(command.getName() + "/" + processName + "-wait",defaultValue);
    }
}
