package org.team1540.elmo.subsystems.drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class DriveForwardAndTurn extends PIDCommand {
    public DriveForwardAndTurn(double setPoint, double rotationSpeed, double driveSpeed, DriveTrain driveTrain) {
        super(new PIDController(rotationSpeed, rotationSpeed/7, 0),
            ()->driveTrain.getAngle()%360,
            ()->setPoint,
            (double output)->{ // output from -180 to 180
                // put output to smartdashboard
                SmartDashboard.putNumber("DriveForwardAndTurn/PID-Output", output);
                
                // void useOutput(double output) {
                output=output/180;
                double leftOutput = driveSpeed;
                double rightOutput = driveSpeed;
                if(output > 0) {
                    rightOutput -= output;
                } else {
                    leftOutput -= output;
                }
                driveTrain.setPercent(leftOutput, rightOutput);
                // }
            },
            driveTrain);
            this.m_controller.enableContinuousInput(0, 360);
    }
}
