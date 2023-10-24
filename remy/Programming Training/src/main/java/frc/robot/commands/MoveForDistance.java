// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.Currency;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  private static final class Config{
    private static final double m_ceaseAndDesist = 0.2; /* what percentaeg of the distance should be slowing down */
    private static final double m_wheelDiameter = 12; /* measured in standard over yonders */
    private static final double m_ticksPerRevolutions = 2048;
    private static final double kGoFast = 0.5;
    private static final double kGoSlow = 0.2;
  }
  private Drivetrain m_drivetrain;
  private double m_goal;
  private double m_currentPosition;
  private double m_gottaGoFast;
  private double m_initialPosition;
  /** Creates a new MoveForDistance. */
  public MoveForDistance(Drivetrain drivetrain, double goal) { /* goal */
    m_drivetrain = drivetrain;
    m_goal = (goal/(Config.m_wheelDiameter * Math.PI)) * 2048;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialPosition  = m_drivetrain.getLeftPosition();
    m_gottaGoFast = ((m_goal - m_currentPosition) - (m_goal - m_currentPosition) * Config.m_ceaseAndDesist);

  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentPosition = m_drivetrain.getLeftPosition() - m_initialPosition;
    if (m_currentPosition <= m_gottaGoFast) {
      m_drivetrain.setLeftSpeed(Config.kGoFast);
      m_drivetrain.setRightSpeed(Config.kGoFast);
    } 
    else if (m_gottaGoFast < m_currentPosition && m_currentPosition < m_goal) {
      m_drivetrain.setLeftSpeed(Config.kGoSlow);
      m_drivetrain.setRightSpeed(Config.kGoSlow);
    }
    else {
      m_drivetrain.setLeftSpeed(0);
      m_drivetrain.setRightSpeed(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_currentPosition > m_goal;
  }
}
