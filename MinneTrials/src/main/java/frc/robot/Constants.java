// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

  public static class DriveConstants {
    public static final int FRONT_LEFT_ID = 1;
    public static final int FRONT_RIGHT_ID = 2;
    public static final int BACK_LEFT_ID = 3;
    public static final int BACK_RIGHT_ID = 4;

    public static final double TRACK_WIDTH = 1.0;
  }

  public static class VisionConstants {
    public static final double anglekP = 1;
    public static final double anglekI = 0.0;
    public static final double anglekD = 0.0;
    public static final double kToleranceDegrees = 1.0;

    public static final double distancekP = 0.0
    ;
    public static final double distancekI = 0.0;
    public static final double distancekD = 0.0;
    public static final double kToleranceMeters = 0.1;

    public static final double TARGET_YAW = 0.5;
    public static final double TARGET_DISTANCE = 0.0;
    public static final String LimelightName = "test";

  }

  public static class LoggerConstants {
    public static final Mode simMode = Mode.SIM;
    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

    public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }
  public static final String MAVEN_GROUP = "";
  public static final String MAVEN_NAME = "AdvantageKit_SkeletonTemplate";
  public static final String VERSION = "unspecified";
  public static final int GIT_REVISION = -1;
  public static final String GIT_SHA = "UNKNOWN";
  public static final String GIT_DATE = "UNKNOWN";
  public static final String GIT_BRANCH = "UNKNOWN";
  public static final String BUILD_DATE = "2025-10-24 23:21:58 EDT";
  public static final long BUILD_UNIX_TIME = 1761362518576L;
  public static final int DIRTY = 129;

  private LoggerConstants() {}
  }
}
