// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  XboxController controller = new XboxController(0);
  public RobotContainer() {
    // Configure the button bindings
    new TankDrive(new DriveTrain(), controller);
    configureButtonBindings();
  }
  
  private void configureButtonBindings() {

  }
}
