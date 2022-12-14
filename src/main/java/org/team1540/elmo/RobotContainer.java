// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.elmo;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import org.team1540.elmo.subsystems.drivetrain.DriveForwardAndTurn;
import org.team1540.elmo.subsystems.drivetrain.DriveTrain;
import org.team1540.elmo.subsystems.drivetrain.TankDrive;
import org.team1540.elmo.subsystems.ejectors.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // Subsystem
    private final Ejector.OuterEjector outerEjector = new Ejector.OuterEjector();
    private final Ejector.InnerEjector innerEjector = new Ejector.InnerEjector();
    private final Ejector.UpperEjector upperEjector = new Ejector.UpperEjector();
    private final DriveTrain driveTrain = new DriveTrain();

    // Misc Components
    private final Compressor compressor = new Compressor(Constants.COMPRESSOR, PneumaticsModuleType.CTREPCM);

    // Control Devices
    private XboxController driver = new XboxController(Constants.DRIVER_CONTROLLER_PORT);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
        setupMiscComponents();
    }
    

    public void setupMiscComponents() {
        compressor.enableDigital();
    }
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
        Trigger eitherBumper = new JoystickButton(driver,XboxController.Button.kLeftBumper.value).or(new JoystickButton(driver,XboxController.Button.kRightBumper.value));
        Trigger neitherBumper = eitherBumper.negate();


        //////////////////// SUCTION /////////////////////
        // left side
        new JoystickButton(driver,XboxController.Button.kX.value).and(neitherBumper).whenActive(
                new EjectInnerCommand(innerEjector)
        );
        // right side
        new JoystickButton(driver,XboxController.Button.kB.value).and(neitherBumper).whenActive(
                new EjectOuterCommand(outerEjector)
        );
        // upper
        new JoystickButton(driver,XboxController.Button.kY.value).and(neitherBumper).whenActive(
                new EjectUpperCommand(upperEjector)
        );

        // toggle individual suction when either bumper and X:inner / B:outer / Y:upper are pressed
        new JoystickButton(driver,XboxController.Button.kX.value).and(eitherBumper).toggleWhenActive(
            new ResetEjectorAndWaitForeverCommand(innerEjector,true)
        );
        new JoystickButton(driver,XboxController.Button.kB.value).and(eitherBumper).toggleWhenActive(
            new ResetEjectorAndWaitForeverCommand(outerEjector,true)
        );
        new JoystickButton(driver,XboxController.Button.kY.value).and(eitherBumper).toggleWhenActive(
            new ResetEjectorAndWaitForeverCommand(upperEjector,true)
        );

        //////////////////// AUTO DRIVE /////////////////////
        Trigger bothBumpers = new JoystickButton(driver,XboxController.Button.kLeftBumper.value).and(new JoystickButton(driver,XboxController.Button.kRightBumper.value));
        Trigger leftJoy = new JoystickButton(driver,XboxController.Button.kLeftStick.value);
        Trigger rightJoy = new JoystickButton(driver,XboxController.Button.kRightStick.value);
        Trigger bothJoys = leftJoy.and(rightJoy);
        Trigger eitherJoy = leftJoy.or(rightJoy);
        // reset gyro
        bothBumpers.whenActive(
            new InstantCommand(driveTrain::resetGyro)
        );
        rightJoy.and(bothJoys.negate()).whileActiveContinuous(
            new DriveForwardAndTurn(45, .5, .3, driveTrain)
        );
        leftJoy.and(bothJoys.negate()).whileActiveContinuous(
            new DriveForwardAndTurn(0, .5, .3, driveTrain)
        );

        driveTrain.setDefaultCommand(new TankDrive(driveTrain, driver));
        // driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, driver));


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
