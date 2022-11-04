// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Drivetrain.TankDrive;

public class RobotContainer {

  public final RobotControl control = new RobotControl();

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    control.bindDrivetrain(new TankDrive());
  }

  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
