package org.team1540.elmo.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class WaitDashboardVarCommand extends WaitVariableCommand {
    public WaitDashboardVarCommand(Double defaultValue, String processName, Command command) {
        super(()->SmartDashboard.getNumber(getDashboardKey(command,processName),defaultValue));
        String smartDashboardKey = getDashboardKey(command, processName);
        SmartDashboard.putNumber(smartDashboardKey,SmartDashboard.getNumber(smartDashboardKey,defaultValue)); // put key if it doesn't already exist
    }

    private static String getDashboardKey(Command command, String processName) {
        return command==null ? "All_Commands" : command.getName() + "/" + processName + "-wait";
    }
}
