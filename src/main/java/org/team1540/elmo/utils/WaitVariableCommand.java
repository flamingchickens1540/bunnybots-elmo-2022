// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.elmo.utils;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

/**
 * A command that does nothing but takes a specified amount of time to finish. Useful for
 * CommandGroups. Can also be subclassed to make a command with an internal timer.
 *
 * <p>This class is provided by the NewCommands VendorDep
 */
public class WaitVariableCommand extends CommandBase {
    protected Timer m_timer = new Timer();
    private double m_duration;
    DoubleSupplier secondsSupplier;

    /**
     * Creates a new WaitCommand. This command will do nothing, and end after the specified duration.
     *
     * @param secondsSupplier the supplier of the wait time, in seconds
     */
    public WaitVariableCommand(DoubleSupplier secondsSupplier) {
        this.secondsSupplier = secondsSupplier;
        double seconds = secondsSupplier.getAsDouble();
        m_duration = seconds;
        SendableRegistry.setName(this, getName() + ": " + seconds + " seconds");
    }

    @Override
    public void initialize() {
        double seconds = secondsSupplier.getAsDouble();
        m_duration = seconds;
        SendableRegistry.setName(this, getName() + ": " + seconds + " seconds");
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void end(boolean interrupted) {
        m_timer.stop();
    }

    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(m_duration);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
}
