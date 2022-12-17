package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    public void initialize() {
        Shuffleboard.selectTab("Testies");
//        Shuffleboard.getTab("Testies").addNumber("ArcadeDrive/Spin",0);
    }

    @Override
    public void execute() {
        double spinPercent = -driver.getLeftX()/1.8;
        double forwardPercent = driver.getLeftY();

        // all shit handled in drivetrain functions
//        driveTrain.setSpeedAndSpin(forwardPercent, spinPercent);
        SmartDashboard.putNumber("ArcadeDrive/Spin",spinPercent);

        driveTrain.setSpeedAndSpinRamped(forwardPercent, spinPercent);
    }
}
