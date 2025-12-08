// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ButterSubsystem extends SubsystemBase {
    private final SparkMax top = new SparkMax(8, MotorType.kBrushless);
    private final SparkMax bottom = new SparkMax(21, MotorType.kBrushless);

  /** Creates a new ButterSubsystem. */
  public ButterSubsystem() {
  }

  public void runSpeed(double speed) {
      top.set(-speed);
      bottom.set(speed);
  }
  

  

  
}
