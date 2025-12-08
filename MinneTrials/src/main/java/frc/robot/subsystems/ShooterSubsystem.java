// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  public TalonFX shooterMotor = new TalonFX(10);

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {}

  public void runSpeed(double speed) {
    shooterMotor.set(speed);
  }
}
