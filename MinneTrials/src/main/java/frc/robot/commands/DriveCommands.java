// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveSubsystem;

/** Add your docs here. */
public class DriveCommands {
    public static Command joystickDrive(DriveSubsystem drive, DoubleSupplier leftY, DoubleSupplier rightX) {
        return new RunCommand(() -> drive.arcadeDrive(leftY.getAsDouble(), rightX.getAsDouble()), drive);
    }
}
