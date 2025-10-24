// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

/** Add your docs here. */
public class DriveSubsystem extends SubsystemBase {
    private final WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(DriveConstants.FRONT_LEFT_ID);
    private final WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(DriveConstants.FRONT_RIGHT_ID);
    private final WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(DriveConstants.BACK_LEFT_ID);
    private final WPI_TalonSRX backRightMotor = new WPI_TalonSRX(DriveConstants.BACK_RIGHT_ID);

    @SuppressWarnings("removal")
    private final MotorControllerGroup leftMotors = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
    @SuppressWarnings("removal")
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

    private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(DriveConstants.TRACK_WIDTH);
    private final DifferentialDrivePoseEstimator odometry = new DifferentialDrivePoseEstimator(kinematics, null, 0, 0, null);

    public DriveSubsystem() {
        RobotConfig config = null;
        try {
            config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AutoBuilder.configure(
            this::getPose,
            this::resetPose,
            this::getSpeeds,
            (speeds, feedforwards) -> drive(speeds),
            new PPLTVController(0.02),
            config,
            () -> {
                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
            },
            this
        ); 
    }

    /**
     * Drive the robot using two joysticks; one for driving forward/back, and one for turning.
     * @param forward The speed to move forward [-1.0, 1.0]
     * @param turn The turning speed [-1.0, 1.0]
     */
    public void arcadeDrive(double forward, double turn) {
        drive.arcadeDrive(forward, turn);
    }

    /**
     * Drive the robot like a tank
     * @param left Speed for the left side [-1.0, 1.0]
     * @param right Speed for the right side [-1.0, 1.0]
     */
    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    public void drive(ChassisSpeeds speeds) {
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
        wheelSpeeds.desaturate(0.5);

        drive.tankDrive(wheelSpeeds.leftMetersPerSecond, wheelSpeeds.rightMetersPerSecond);
    }

    public Pose2d getPose() {
        return odometry.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose) {
        odometry.resetPose(pose);
    }

    @SuppressWarnings("removal")
    public ChassisSpeeds getSpeeds() {
        return kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(leftMotors.get(), rightMotors.get()));
    }
}
