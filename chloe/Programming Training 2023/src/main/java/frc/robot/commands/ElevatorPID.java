// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;



public class ElevatorPID extends CommandBase {
  /** Creates a new ElevatorPID. */
  public static final class Config{
    public static final double kP = 0.5;
    public static final double kI = 0;
    public static final double kD = 0;
  }
  
  private PIDController m_pid = new PIDController(Config.kP, Config.kI, Config.kD);
  private Elevator m_elevator;
  private double m_goal;

  public ElevatorPID(double distance,Elevator elevator) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_goal = distance; 
    m_elevator = elevator; 
    addRequirements(m_elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevator.setSpeed(m_pid.calculate(m_elevator.getEncoder(), m_goal));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_goal <= Math.abs(0.005);
    
  }
}
