// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveForDistance extends CommandBase {
  private static final class Config{
    public static final double kMotorSpeed = 0.4;
    public static final double kTicksPerRevolution = 2048;
    public static final double kWheelCircumferenceInches = 6*Math.PI;
  }

  private double m_goal;
  private Drivetrain m_drivetrain;
  private double m_error;
  private double m_startPosition;

  public MoveForDistance(double distance, Drivetrain drivetrain){
    m_goal = distance;
    m_error = distance;
    m_drivetrain = drivetrain;
  }

  
  @Override
  public void initialize() {
    m_startPosition = m_drivetrain.getRightEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_error = m_goal - (m_drivetrain.getRightEncoderPosition() - m_startPosition);
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
    return (m_error == 0);
  }
}
