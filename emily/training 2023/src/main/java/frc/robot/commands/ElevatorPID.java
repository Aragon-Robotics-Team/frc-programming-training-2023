// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorPID extends CommandBase {
  /** Creates a new ElevatorPID. */
  private static final class Config {
    private static final double kP = 0.5;
    private static final double kI = 0;
    private static final double kD = 0;
  }
  private PIDController m_pid = new PIDController(Config.kP, Config.kI, Config.kD);
  private Elevator m_elevator = new Elevator();
  private double m_goal;
  private double m_initialPosition;

  public ElevatorPID(Elevator elevator, double goal) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevator = elevator;
    m_goal = goal;
    
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
