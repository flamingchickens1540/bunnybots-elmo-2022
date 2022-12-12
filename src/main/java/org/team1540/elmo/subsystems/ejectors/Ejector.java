package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class Ejector extends SubsystemBase {

    private final Solenoid suction;
    private final Solenoid eject;

    public Ejector(Solenoid suction, Solenoid eject) {
        this.suction = suction;
        this.eject = eject;
        SmartDashboard.putNumber(getName() + "/extendSecs",1);

        setDefaultCommand(new ResetEjectorAndWaitForeverCommand(this,false)); // reset ejectors and then wait
    }

    public void setSuction(boolean sucking) {
        suction.set(sucking);
    }
    public void setExtended(boolean extended) {
        eject.set(extended);
    }
    public void extend() {
        eject.setPulseDuration(SmartDashboard.getNumber(getName() + "/extendSecs",1));
        eject.startPulse();
    }

    public static class InnerEjector extends Ejector {
        public InnerEjector() {
            super(
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_INNER_CHANNEL),
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.DEPLOY_INNER_CHANNEL)
            );
        }
    }
    public static class OuterEjector extends Ejector {
        public OuterEjector() {
            super(
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_OUTER_CHANNEL),
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.DEPLOY_OUTER_CHANNEL)
            );
        }
    }
    public static class UpperEjector extends Ejector {
        public UpperEjector() {
            super(
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_UPPER_CHANNEL),
                    new Solenoid(Constants.PNEUMATICS_TYPE,Constants.DEPLOY_UPPER_CHANNEL)
            );
        }
    }
}
