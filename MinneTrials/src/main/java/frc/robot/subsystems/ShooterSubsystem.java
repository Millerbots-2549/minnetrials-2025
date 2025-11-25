// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {
  public TalonFX shooterMotor = new TalonFX(10);

  private TrapezoidProfile.Constraints shooterConstraints = new TrapezoidProfile.Constraints(5000, 6000);
  private ProfiledPIDController shooterPID = new ProfiledPIDController(0.1, 0.0, 0.0, shooterConstraints);

  private double setpoint = 0;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {

  }

  @Override
  public void periodic() {
    double output = shooterPID.calculate(shooterMotor.getVelocity().getValueAsDouble(), setpoint);
    shooterMotor.set(output);
  }

  public void runSpeed(double speed) {
    setpoint = speed;
  }
}
