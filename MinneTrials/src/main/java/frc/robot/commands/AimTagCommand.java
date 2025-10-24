package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AimTagCommand extends Command {

  private final DriveSubsystem drive;
  private final PIDController pid;

  public AimTagCommand(DriveSubsystem drive) {
    this.drive = drive;
    this.pid = new PIDController(VisionConstants.kP, VisionConstants.kI, VisionConstants.kD);
    pid.setTolerance(VisionConstants.kToleranceDegrees);
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    pid.reset();
  }

  @Override
  public void execute() {
    double tx = LimelightHelpers.getTX("");
    double output = pid.calculate(tx, VisionConstants.TARGET_YAW);

    // Rotate robot to aim at target
    drive.arcadeDrive(0, -output);
  }

  @Override
  public void end(boolean interrupted) {
    drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}
