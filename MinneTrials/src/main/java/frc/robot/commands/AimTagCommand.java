package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AimTagCommand extends Command {

    private final DriveSubsystem drive;
    private final PIDController anglepid;
    private final PIDController distancepid;

    public AimTagCommand(DriveSubsystem drive) {
        this.drive = drive;
        this.anglepid = new PIDController(VisionConstants.anglekP, VisionConstants.anglekI, VisionConstants.anglekD);
        this.distancepid = new PIDController(VisionConstants.distancekP, VisionConstants.distancekI, VisionConstants.distancekD);
        anglepid.setTolerance(VisionConstants.kToleranceDegrees);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        anglepid.reset();
        distancepid.reset();

        if (DriverStation.getAlliance().isPresent()) {
            if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red) {
                LimelightHelpers.SetFiducialIDFiltersOverride(VisionConstants.LimelightName, new int[]{6, 7});
            } else if (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue) {
                LimelightHelpers.SetFiducialIDFiltersOverride(VisionConstants.LimelightName, new int[]{4, 5});
            }
        }
    }

    @Override
    public void execute() {
        double tx = LimelightHelpers.getTX(VisionConstants.LimelightName);
        double angleoutput = anglepid.calculate(tx, VisionConstants.TARGET_YAW);

        double ty = LimelightHelpers.getTY(VisionConstants.LimelightName);
        double distanceoutput = distancepid.calculate(ty, VisionConstants.TARGET_DISTANCE);
        

        // Rotate robot to aim at target
        System.out.println(LimelightHelpers.getTX(VisionConstants.LimelightName));
        drive.arcadeDrive(angleoutput, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
        
    }

    @Override
    public boolean isFinished() {
        return anglepid.atSetpoint();
    }
}
