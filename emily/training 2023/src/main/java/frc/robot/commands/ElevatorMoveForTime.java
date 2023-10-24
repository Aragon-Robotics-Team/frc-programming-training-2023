// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorMoveForTime extends CommandBase {
  private static final class Config {
    public static final int kTopSwitchChannel = 0;
  }
  /** Creates a new ElevatorMoveForTime. */
  private Elevator m_elevator;
  private DigitalInput m_topSwitch = new DigitalInput(Config.kTopSwitchChannel);
  private double m_speed;
  private double m_timeInSeconds;
  private double m_startTime;
  private Timer m_timer;
  public ElevatorMoveForTime(Elevator elevator, double speed, double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevator = elevator;
    m_speed = speed;
    m_timeInSeconds = seconds;
    m_timer = new Timer();
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = m_timer.get();
    m_timer.start();
    m_timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if ((m_timer.get() - m_startTime) <= m_timeInSeconds) {
      m_elevator.setSpeed(m_speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((m_timer.get() - m_startTime) > m_timeInSeconds) {
      return true;
    }
    return m_topSwitch.get();
  }
}
