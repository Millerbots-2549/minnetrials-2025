// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AimTagCommand;
import frc.robot.commands.DriveCommands;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ButterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final AimTagCommand aimCommand = new AimTagCommand(driveSubsystem);
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem intalke = new IntakeSubsystem();
  private final ButterSubsystem butter = new ButterSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    driveSubsystem.setDefaultCommand(DriveCommands.joystickDrive(
      driveSubsystem, () -> m_driverController.getRightX(), () -> m_driverController.getLeftY()));
    
    shooterSubsystem.setDefaultCommand(new RunCommand(() -> shooterSubsystem.runSpeed(0), shooterSubsystem));
    butter.setDefaultCommand(new RunCommand(() -> butter.runSpeed(0), butter));
    intalke.setDefaultCommand(new RunCommand(() -> intalke.runSpeed(0), intalke));

    m_driverController.a().whileTrue(aimCommand);


    m_operatorController.leftTrigger().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(67)));
    m_operatorController.leftBumper().whileTrue(new RunCommand(() -> intalke.runSpeed(-67)));
    m_operatorController.rightBumper().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(-67)));
    m_operatorController.a().whileTrue(new RunCommand(() -> butter.runSpeed(67)));
    m_operatorController.b().whileTrue(new RunCommand(() -> butter.runSpeed(-67)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return Commands.none();
  }
}
