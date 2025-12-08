// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** Add your docs here. */
public class Autos {
    public static final Command LeaveAuto(DriveSubsystem drive) {
        return DriveCommands.joystickDrive(drive, () -> 0.5, () -> 0.0).withTimeout(5);
    }

    public static final Command ShooterAndIntakeTypeShii(DriveSubsystem drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
        // Drive forwards
        return new SequentialCommandGroup(
            new RunCommand(() -> drive.arcadeDrive(0.0, -0.5), drive).withTimeout(2),
            // Spin up shooter
            new RunCommand(() -> shooter.runSpeed(0.3), shooter).withTimeout(1.0),
            // Run intake and shooter at same time
            new RunCommand(() -> intake.runSpeed(-1), intake).alongWith(
                new RunCommand(() -> shooter.runSpeed(0.3), shooter)
            ).withTimeout(5.0));
    }
}
