// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AimTagCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommands;
import frc.robot.subsystems.ButterSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

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
  private final CommandXboxController m_driverController;
  private final CommandXboxController m_operatorController;
  private final CommandXboxController m_godController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);
    m_godController = new CommandXboxController(2);
    
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
      driveSubsystem, () -> {
        if(DriverStation.isJoystickConnected(2)) {
          return m_godController.getRightX() + m_driverController.getRightX();
        } else {
          return m_driverController.getRightX();
        }
      }, () -> {
        if(DriverStation.isJoystickConnected(2)) {
          return m_godController.getLeftY() + m_driverController.getLeftY();
        } else {
          return m_driverController.getLeftY();
        }
      }));
      m_driverController.a().whileTrue(aimCommand);

    
    shooterSubsystem.setDefaultCommand(new RunCommand(() -> shooterSubsystem.runSpeed(0), shooterSubsystem));
    butter.setDefaultCommand(new RunCommand(() -> butter.runSpeed(0), butter));
    intalke.setDefaultCommand(new RunCommand(() -> intalke.runSpeed(0), intalke));

    


    m_operatorController.x().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(0.30)));
    m_operatorController.y().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(-0.2)));
    
    
    m_operatorController.leftBumper().whileTrue(new RunCommand(() -> intalke.runSpeed(-1)));
    m_operatorController.rightBumper().whileTrue(new RunCommand(() -> intalke.runSpeed(1)));
    m_operatorController.a().whileTrue(new RunCommand(() -> butter.runSpeed(1)));
    m_operatorController.b().whileTrue(new RunCommand(() -> butter.runSpeed(-1)));

    // God Controller
    m_godController.x().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(0.30)));
    m_godController.y().whileTrue(new RunCommand(() -> shooterSubsystem.runSpeed(-0.2)));
    
    m_godController.leftBumper().whileTrue(new RunCommand(() -> intalke.runSpeed(-1)));
    m_godController.rightBumper().whileTrue(new RunCommand(() -> intalke.runSpeed(1)));
    m_godController.a().whileTrue(new RunCommand(() -> butter.runSpeed(1)));
    m_godController.b().whileTrue(new RunCommand(() -> butter.runSpeed(-1)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return Autos.ShooterAndIntakeTypeShii(driveSubsystem, shooterSubsystem, intalke);
  }
}
