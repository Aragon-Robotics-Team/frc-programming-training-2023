// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;


public class ElevatorPie extends CommandBase {
  private static final class Config{
    static final private double setPoint = 5; // real world distance units
    static final private double kP = 0.1;
    static final private double kI = 0;
    static final private double kD = 0;
  }
  private  Elevator m_elevator;
  private PIDController m_pid  = new PIDController(Config.kP, Config.kI, Config.kD);
  private double m_goal;
  /** Creates a new ElevatorPie. */
  public ElevatorPie(Elevator elevator) {
    m_elevator = elevator;
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pid.setTolerance(5, 10);
    m_goal = m_elevator.getConversionFactor() * Config.setPoint;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevator.setSpeed(m_pid.calculate(m_elevator.getPosition(), m_goal));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.setSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_pid.atSetpoint();
  }
}
