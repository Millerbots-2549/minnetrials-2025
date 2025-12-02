// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
@SuppressWarnings("unused")
public class BlowEveryFuseAndBrickRoboRIOCommand extends Command {
  private final PowerDistribution pdp = new PowerDistribution();
  private final long thread_id;

  /** Creates a new BlowEveryFuseAndBrickRoboRIOCommand. */
  public BlowEveryFuseAndBrickRoboRIOCommand() {
    thread_id = Robot.getMainThreadId();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pdp.resetTotalEnergy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
