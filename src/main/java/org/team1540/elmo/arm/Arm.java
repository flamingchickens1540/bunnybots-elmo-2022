package org.team1540.elmo.arm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class Arm extends SubsystemBase {
    private Solenoid suction = new Solenoid(Constants.PNEUMATICS_TYPE,Constants.SUCTION_UPPER_CHANNEL);
    private DoubleSolenoid arm = new DoubleSolenoid(Constants.PNEUMATICS_TYPE,Constants.ARM_FORWARD_CHANNEL,Constants.ARM_REVERSE_CHANNEL);

    public void setSuction(boolean active) {
        suction.set(active);
    }

    public void setArm(boolean extended) {
        arm.set(extended ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }
}
