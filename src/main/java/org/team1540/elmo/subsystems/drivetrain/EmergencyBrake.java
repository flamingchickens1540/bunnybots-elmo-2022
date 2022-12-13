package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class EmergencyBrake extends CommandBase {
    DriveTrain drivetrain;

    public EmergencyBrake(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void initialize() {
        drivetrain.setBrake(true);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setBrake(false);
    }
}
