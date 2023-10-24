// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator extends SubsystemBase {
  private static final class Config{
    private static final int deviceId = 5;
  }
  /** Creates a new Elevator. */
  private CANSparkMax m_elevator = new CANSparkMax(Config.deviceId, MotorType.kBrushless);
  
  public Elevator() {
    
  }
  
  public void setSpeed(double speed) {
    m_elevator.set(speed);
  }

  // public void SetNeutralMode(IdleMode neutralMode) {
    
  // }
  public double getEncoder(){
    return m_elevator.getEncoder.getPosition();

  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
