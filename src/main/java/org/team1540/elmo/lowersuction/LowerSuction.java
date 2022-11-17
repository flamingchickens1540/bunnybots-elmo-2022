package org.team1540.elmo.lowersuction;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class LowerSuction extends SubsystemBase {

    private Solenoid suctionInner = new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_INNER_CHANNEL);
    private Solenoid suctionOuter = new Solenoid(Constants.PNEUMATICS_TYPE,Constants.DEPLOY_OUTER_CHANNEL);
    private Solenoid ejectInner = new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_OUTER_CHANNEL);
    private Solenoid ejectOuter = new Solenoid(Constants.PNEUMATICS_TYPE,Constants.DEPLOY_INNER_CHANNEL);
    private Compressor compressor = new Compressor(Constants.PNEUMATICS_TYPE); // TODO: test command waits based on compressor voltage and not time

    public LowerSuction() {
        SmartDashboard.putNumber("LowerSuction/ejectSeconds",1);
    }

    public void setSuctionInner(boolean sucking) {
        suctionInner.set(sucking);
    }
    public void setSuctionOuter(boolean sucking) {
        suctionOuter.set(sucking);
    }
    public void setEjectInner(boolean extended) {
        ejectInner.set(extended);
    }
    public void setEjectOuter(boolean extended) {
        ejectOuter.set(extended);
    }
    public void ejectInner() {
        ejectInner.setPulseDuration(SmartDashboard.getNumber("LowerSuction/ejectSeconds",1));
        ejectInner.startPulse();
    }
    public void ejectOuter() {
        ejectOuter.setPulseDuration(SmartDashboard.getNumber("LowerSuction/ejectSeconds",1));
        ejectOuter.startPulse();
    }
}
