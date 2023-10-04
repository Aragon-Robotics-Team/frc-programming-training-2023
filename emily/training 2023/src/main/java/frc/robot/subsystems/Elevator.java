// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  private static final class Config{
    private static final int deviceId = 5;
  }
  /** Creates a new Elevator.
   */
  private TalonSRX m_elevator = new TalonSRX(Config.deviceId);
  public Elevator() {}
  
  public void SetSpeed(double speed) {
    m_elevator.set(ControlMode.PercentOutput, speed);
  }
  // public void SetNeutralMode(IdleMode neutralMode) {

  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
