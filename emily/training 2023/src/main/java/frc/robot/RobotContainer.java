// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArcadeElevator;
import frc.robot.commands.ElevatorMoveForTime;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private static final class Config {
    public static final int kJoystickPort = 1;
    public static final int kElevatorButton = 1;
  }
  // The robot's subsystems and commands are defined here...
  private Joystick m_joystick = new Joystick(Config.kJoystickPort);
  private Drivetrain m_drivetrain = new Drivetrain();
  private Elevator m_elevator = new Elevator();
  private ArcadeElevator m_arcadeElevator = new ArcadeElevator(m_joystick, m_elevator);
  private ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_joystick, m_drivetrain);
  private ElevatorMoveForTime m_elevatorMoveForTime = new ElevatorMoveForTime(m_elevator, 0.25, 4);
  private JoystickButton m_elevatorMoveForTimeButton = new JoystickButton(m_joystick, Config.kElevatorButton);
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
    m_elevatorMoveForTimeButton.onTrue(m_elevatorMoveForTime);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
  public Command getTeleopCommand() {
    m_drivetrain.setDefaultCommand(m_arcadeDrive);
    m_arcadeElevator.schedule();
    return null;
  }
}
