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
        

        double leftPercent = -spinPercent + forwardPercent;
        double rightPercent = spinPercent + forwardPercent;

        // Any calculated output is greater than 1, scale both coefficients down so that the max is 1
        double maxScalarPercent = Math.max(Math.abs(leftPercent),Math.abs(rightPercent));
        if(maxScalarPercent > 1) {
            double coefficient = 1 / maxScalarPercent;
            leftPercent *= coefficient;
            rightPercent *= coefficient;
        }

        driveTrain.setPercent(leftPercent, rightPercent);
    }
}
