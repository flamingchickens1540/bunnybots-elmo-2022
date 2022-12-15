package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveForwardRampedForever extends CommandBase {
    private DriveTrain driveTrain;
    private double motorPercent;

    public DriveForwardRampedForever(double motorPercent, DriveTrain driveTrain) {
        addRequirements(driveTrain);
        this.driveTrain = driveTrain;
        this.motorPercent = motorPercent;
    }

    @Override
    public void execute() {
        driveTrain.setPercentRamped(motorPercent,motorPercent);
    }
}
