// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  private static final class Config{
    public static final double kTickPerRevolution = 2048;
    public static final double kMotorSpeed = 0.4;
    public static final double kCircumferenceOfWheel = Math.PI *12;
  }
  private Drivetrain m_drivetrain;
  private double m_goal;
  private double m_error;
  private double m_currentPosition;
  private double m_initialPosition;
  
  /** Creates a new MoveForDistance. */
  public MoveForDistance(double distance, Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_goal = distance;
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialPosition = m_drivetrain.getRightTicks();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentPosition = m_drivetrain.getRightTicks() - m_initialPosition;
    m_error = m_goal - m_currentPosition;
    m_drivetrain.setLeftSpeed(Config.kMotorSpeed);
    m_drivetrain.setRightSpeed(Config.kMotorSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setLeftSpeed(0);
    m_drivetrain.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_error==0;

  
    
  }
}
