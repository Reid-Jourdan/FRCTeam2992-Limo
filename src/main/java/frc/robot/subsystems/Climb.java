// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  /** Creates a new Climb. */
  private WPI_TalonFX leftClimbMotor;
  private WPI_TalonFX rightClimbMotor;



  public Climb() {
    leftClimbMotor = new WPI_TalonFX(40);
    leftClimbMotor.setInverted(false);
    leftClimbMotor.setNeutralMode(NeutralMode.Brake);
    addChild("leftClimbMotor", leftClimbMotor);

    
    rightClimbMotor = new WPI_TalonFX(41);
    rightClimbMotor.setInverted(false);
    rightClimbMotor.setNeutralMode(NeutralMode.Brake);
    addChild("rightClimbMotor", rightClimbMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setClimbSpeed(double speed){
    leftClimbMotor.set(ControlMode.PercentOutput, speed);
  }

  


}
