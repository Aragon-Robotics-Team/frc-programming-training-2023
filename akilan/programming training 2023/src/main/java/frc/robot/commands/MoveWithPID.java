// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class MoveWithPID extends CommandBase {
  private static final class Config{
    public static final double kP = 0.1;
    public static final double kI = 0.000001;
    public static final double kD = 0.0000002; 
    public static final double kMotorSpeed = 0.4;
  }

  private PIDController m_PID = new PIDController(Config.kP, Config.kI, Config.kD);
  private Elevator m_elevator;
  private double m_distance;
  private double m_startPosition;

  public MoveWithPID(Elevator elevator, double distance) {
    m_elevator = elevator;
    m_distance = distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startPosition = m_elevator.getDistance();
    SmartDashboard.putNumber("Start Position", m_startPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevator.setSpeed(m_PID.calculate((m_elevator.getDistance() - m_startPosition), m_distance));
    SmartDashboard.putNumber("Motor Speed",m_PID.calculate((m_elevator.getDistance() - m_startPosition), m_distance));
    SmartDashboard.putNumber("Position", m_elevator.getDistance());
    SmartDashboard.putNumber("Error", m_distance - m_elevator.getDistance() + m_startPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.setSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs((m_distance - m_elevator.getDistance() + m_startPosition)) < 0.00005);
  }
}
