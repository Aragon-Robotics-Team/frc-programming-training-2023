// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorMoveForTime extends CommandBase {
  private static final class Config{
    private static final int kElevatorUpperLimitSwitchChannel = 0;
  }
  private Timer m_timer;
  private double m_produceSpeed;
  private double m_timeInSeconds;
  private Elevator m_elevator;
  private DigitalInput m_elvatorUpperLimitSwitch = new DigitalInput(Config.kElevatorUpperLimitSwitchChannel);
  /** Creates a new ElevatorMoveForTime. */
  public ElevatorMoveForTime(Elevator elevator, double produceSpeed, double timeInSeconds) {
    m_elevator = elevator;
    m_produceSpeed = produceSpeed;
    m_timeInSeconds = timeInSeconds;
    m_timer = new Timer();

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

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
