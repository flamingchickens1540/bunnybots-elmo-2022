// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.elmo;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.team1540.elmo.subsystems.ejectors.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final Ejectors.OuterEjector outerEjector = new Ejectors.OuterEjector();
    private final Ejectors.InnerEjector innerEjector = new Ejectors.InnerEjector();
    private final Ejectors.UpperEjector upperEjector = new Ejectors.UpperEjector();

    private XboxController driver = new XboxController(Constants.DRIVER_CONTROLLER_PORT);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
//        new JoystickButton(driver,XboxController.Button.kA.value).whenPressed(
//                new EjectLower(innerEjector,outerEjector)
//        );
        // left side
        new JoystickButton(driver,XboxController.Button.kX.value).whenPressed(
                new EjectInnerCommand(innerEjector)
        );
        // right side
        new JoystickButton(driver,XboxController.Button.kA.value).whenPressed(
                new EjectOuterCommand(outerEjector)
        );
        // upper
        new JoystickButton(driver,XboxController.Button.kY.value).whenPressed(
                new EjectUpperCommand(upperEjector)
        );
        // retract all
        new JoystickButton(driver,XboxController.Button.kB.value).whenPressed(
                new ResetEjectorCommand(upperEjector).alongWith(
                new ResetEjectorCommand(outerEjector).alongWith(
                new ResetEjectorCommand(innerEjector)))
        );

        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
