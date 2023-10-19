// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  private static class Config{
    public static int motorID = 5;
  }
  private CANSparkMax m_elevatorMotor = new CANSparkMax(Config.motorID, MotorType.kBrushless);
  public Elevator() {}

  public double getDistance(){
    return m_elevatorMotor.getEncoder().getPosition();
  }

  public void setSpeed(double speed){
    m_elevatorMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
