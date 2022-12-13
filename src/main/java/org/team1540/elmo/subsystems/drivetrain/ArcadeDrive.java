package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {

    DriveTrain driveTrain;
    XboxController driver;

    public ArcadeDrive(DriveTrain driveTrain, XboxController driver) {
        this.driveTrain = driveTrain;
        this.driver = driver;

        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        double spinPercent = driver.getRightX();
        double forwardPercent = driver.getLeftY();
        
        double totalThrottle = Math.abs(spinPercent) + Math.abs(forwardPercent);

        if(totalThrottle > 1) {
            double coefficient = 1 / totalThrottle;

            spinPercent *= coefficient;
            forwardPercent *= coefficient;
        }

        driveTrain.setPercent(forwardPercent + spinPercent, forwardPercent - spinPercent);
    }
}
