// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.elmo;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Motors
    public static final int LEFT_FRONT_MOTOR_CAN_ID = 1;
    public static final int LEFT_BACK_MOTOR_CAN_ID = 2;
    public static final int RIGHT_FRONT_MOTOR_CAN_ID = 3;
    public static final int RIGHT_BACK_MOTOR_CAN_ID = 4;

    // CONTROL
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int COPILOT_CONTROLLER_PORT = 1;

    // SOLENOIDS
    // Arm
    public static final PneumaticsModuleType PNEUMATICS_TYPE = PneumaticsModuleType.CTREPCM;
    public static final int DEPLOY_UPPER_CHANNEL = 3;
//    public static final int ARM_REVERSE_CHANNEL = 1; // ?????
    public static final int SUCTION_UPPER_CHANNEL = 6;
    // BunnySucc :[]
    public static final int SUCTION_INNER_CHANNEL = 4;
    public static final int SUCTION_OUTER_CHANNEL = 5;
    public static final int DEPLOY_INNER_CHANNEL = 1;
    public static final int DEPLOY_OUTER_CHANNEL = 2;

    // COMPRESSOR
    public static final int COMPRESSOR = 0;
}
