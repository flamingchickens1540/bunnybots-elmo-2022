package org.team1540.elmo.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.elmo.ejectors.BunnyEjectors;

public class WaitDashboardVarCommand extends WaitVariableCommand {
    public WaitDashboardVarCommand(BunnyEjectors ejectors, String processName, Double defaultValue) {
        super(()->SmartDashboard.getNumber(ejectors.getName() + "/" + processName + "-duration",defaultValue));
        SmartDashboard.putNumber(ejectors.getName() + "/" + processName + "-duration",defaultValue);
    }
}
